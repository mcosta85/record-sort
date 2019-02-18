(ns record-sort.sort)

(defn- parse-date
  "Converts the date string to a timestamp."
  [date]
  (.getTime
   (.parse (java.text.SimpleDateFormat. "mm/dd/yyyy") date)))

(defn- gender-sort
  [records]
  (sort-by (juxt :gender :last-name :first-name) records))

(defn- last-name-sort
  [records]
  (reverse (sort-by (juxt :last-name :first-name) records)))

(defn- dob-sort
  [records]
  (let [dob-comp (comparator (fn [x y]
                               (< (parse-date x) (parse-date y))))]
  (sort-by :dob dob-comp records)))

(def ^:private sort-fns
  {:gender gender-sort
   :last-name last-name-sort
   :dob dob-sort})

(defn sort-records
  "If invalid sort is provided, records are returned unsorted."
  [sort-key records]
  (let [sort-fn (sort-key sort-fns)]
    (if sort-fn
      (sort-fn records)
      records)))

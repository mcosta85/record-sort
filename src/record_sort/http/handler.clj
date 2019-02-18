(ns record-sort.http.handler
  (:require [record-sort.core :as sort]
            [ring.util.http-response :refer :all]))

(defn add-record!
  "Adds the given record to the data store."
  [data-store record]
  (let [rec (slurp (:body record))]
    (swap! data-store conj rec)
    (accepted)))

(defn sort-by-gender
  "Returns all records stored in the data-store, sorted by gender."
  [data-store]
  (ok
   {:sorted-by "gender"
    :records (sort/sort-records :gender @data-store)}))

(defn sort-by-dob
  "Returns all records stored in the data-store, sorted by birth date."
  [data-store]
  (ok
   {:sorted-by "date of birth"
    :records (sort/sort-records :dob @data-store)}))

(defn sort-by-name
  "Returns all records stored in the data-store, sorted by name."
  [data-store]
  (ok
   {:sorted-by "last name"
    :records (sort/sort-records :last-name @data-store)}))

(ns record-sort-core.parse
  (:require [clojure.string :as str]))

(def ^:private delimiter-regex
  "Match on the following pattern;

  \\s? - Zero or one whitespace characters
  [|,]? - Zero or one pipe or comma characters
  \\s+ Exactly one whitespace character"

  #"\s?[|,]?\s+")

(defn- valid-date?
  [date]
  (try
    (.parse (java.text.SimpleDateFormat. "MM/dd/yyyy") date)

    (catch java.text.ParseException pe
      false)))

(defn- split-input
  "Splits the input records on a regex which should match any delimiter used.
  Returns a seq of record fields."
  [input]
  {:pre [(string? input)]
   :post [(= 5 (count %))
          (valid-date? (get % 4))]}
  (str/split input delimiter-regex))

(defn- format-output
  "Creates a map representation of the record."
  [[lname fname gender color dob]]
  {:last-name lname
   :first-name fname
   :gender gender
   :favorite-color color
   :dob dob})

(defn parse-input
  "Splits the input and parses the date field."
  [input]
  (-> input
      split-input
      format-output))

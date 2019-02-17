(ns record-sort.parse
  (:require [clojure.string :as str]))

(def ^:private delimiter-regex
  "Match on the following pattern;

  \\s? - Zero or one whitespace characters
  [|,]? - Zero or one pipe or comma characters
  \\s+ Exactly one whitespace character"

  #"\s?[|,]?\s+")

(defn- split-input
  "Splits the input records on a regex which should match any delimiter used.
  Returns a seq of record fields."
  [input]
  {:pre [(string? input)]
   :post [(= 5 (count %))]}
  (str/split input delimiter-regex))

(defn- parse-date
  "Converts the date string to a timestamp."
  [date]
  (.getTime
   (.parse (java.text.SimpleDateFormat. "MM/dd/yyyy") date)))

(defn parse-input
  "Splits the input and parses the date field."
  [input]
  (-> input
      split-input
      (update 4 parse-date)))

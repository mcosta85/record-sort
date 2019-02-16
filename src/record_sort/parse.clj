(ns record-sort.parse
  (:require [clojure.string :as str]))

(def ^:private delimiter-regex
  "Match on the following pattern;

  \\s? - Zero or one whitespace characters
  [|,]? - Zero or one pipe or comma characters
  \\s+ Exactly one whitespace character"

  #"\s?[|,]?\s+")

(defn parse-input
  "Splits the input records on a regex which should match any delimiter used.
  Returns a seq of record fields."
  [input]
  {:pre [(string? input)]}
  (str/split input delimiter-regex))


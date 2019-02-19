(ns record-sort-core.core
  (:require [record-sort-core.parse :as parse]
            [record-sort-core.sort :as sort]))

(defn sort-records
  [sort-key records]
  (->> records
       (map parse/parse-input)
       (sort/sort-records sort-key)))

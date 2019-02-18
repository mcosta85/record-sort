(ns record-sort.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [record-sort.parse :as parse]
            [record-sort.sort :as sort])
  (:gen-class))

(defn- format-record
  "Formats a record for display purposes."
  [record]
  (->> record
       vals
       (interpose ", ")
       str/join))

(defn- output-sorted-records
  [sort-key records]
  (println "*** Sorted by" (str/capitalize (name sort-key)) "***\n")
  (doall (map println records))
  (println "\n\n"))

(defn sort-records
  [sort-key records]
  (->> records
       (map parse/parse-input)
       (sort/sort-records sort-key)
       (map format-record)))

(defn- read-files
  [records]
  (->> records
       (map io/resource)
       (map slurp)
       (map #(str/split % #"\n"))
       flatten))

(defn -main
  [& records]
  (let [rows (read-files records)
        [gender dob lname] (map sort-records [:gender :dob :last-name] (repeat rows))]

    (output-sorted-records :gender gender)
    (output-sorted-records :dob dob)
    (output-sorted-records :last-name lname)))

(ns record-sort.sort-test
  (:require [clojure.test :refer [deftest is testing]]
            [record-sort.sort :as sort]))

(def ^:private mason
  {:last-name "Costa"
   :first-name "Mason"
   :gender "Male"
   :favorite-color "Green"
   :dob "12/14/1985"})

(def ^:private rich
  {:last-name "Hickey"
   :first-name "Rich"
   :gender "Male"
   :favorite-color "Blue"
   :dob "05/10/1975"})

(def ^:private ada
  {:last-name "Lovelace"
   :first-name "Ada"
   :gender "Female"
   :favorite-color "Brown"
   :dob "12/10/1815"})

(deftest record-sort-test
  (testing "Sort by gender"
    (let [sorted (sort/sort-records :gender [rich ada mason])]
      (is (= ada (first sorted)))
      (is (= mason (second sorted))
          "Costa should come before Hickey alphabetically ascending.")
      (is (= rich (last sorted)))))

  (testing "Sort by birth date"
    (let [sorted (sort/sort-records :dob [rich ada mason])]
      (is (= ada (first sorted)))
      (is (= rich (second sorted))
      (is (= mason (last sorted))))))

  (testing "Sort by last name"
    (let [sorted (sort/sort-records :last-name [rich ada mason])]
      (is (= ada (first sorted)))
      (is (= rich (second sorted))
          "Hickey should come before Costa alphabetically descending.")
      (is (= mason (last sorted)))))

  (testing "Sort by unsupported field"
    (let [sorted (sort/sort-records :first-name [rich ada mason])]
      (is (= rich (first sorted))
          "Rich is first because records come back unsorted.")
      (is (= mason (last sorted))
          "Mason is last as records are unsorted."))))

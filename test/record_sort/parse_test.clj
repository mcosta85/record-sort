(ns record-sort.parse-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is testing]]
            [record-sort.parse :as parse]))

(deftest parse-input-test
  (testing "parsing of variously delimited records"
    (let [comma-delimited "Last, First, Gender, FavColor, 01/01/2000"
          pipe-delimited (str/replace comma-delimited #"," " |")
          space-delimited (str/replace comma-delimited #"," "")

          comma-parsed (parse/parse-input comma-delimited)
          pipe-parsed (parse/parse-input pipe-delimited)
          space-parsed (parse/parse-input space-delimited)

          expected {:last-name "Last"
                    :first-name "First"
                    :gender "Gender"
                    :favorite-color "FavColor"
                    :dob "01/01/2000"}]

      (is (= expected comma-parsed pipe-parsed space-parsed))))

  (testing "Non-string input"
    (is (thrown? AssertionError (parse/parse-input 12))))

  (testing "Unexpected date format"
    (is (thrown? AssertionError
                 (parse/parse-input "Last First Gender Color 01012000"))))

  (testing "Incomplete record"
    (is (thrown? AssertionError (parse/parse-input "Last First")))))


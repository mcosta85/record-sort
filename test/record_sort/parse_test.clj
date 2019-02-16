(ns record-sort.parse-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is testing]]
            [record-sort.parse :as parse]))

(deftest parse-input-test
  (testing "parsing of variously delimited records"
    (let [comma-delimited "Last, First, Gender, FavColor, DOB"
          pipe-delimited (str/replace comma-delimited #"," " |")
          space-delimited (str/replace comma-delimited #"," "")
          expected ["Last" "First" "Gender" "FavColor" "DOB"]]
      (is (= expected (parse/parse-input comma-delimited)))
      (is (= expected (parse/parse-input pipe-delimited)))
      (is (= expected (parse/parse-input space-delimited)))))

  (testing "Non-string input"
    (is (thrown? AssertionError (parse/parse-input 12)))))


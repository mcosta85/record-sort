(ns record-sort-api.handler-test
  (:require [clojure.java.io :as io]
            [clojure.test :refer [deftest is testing]]
            [record-sort-api.handler :as handler]))

(def ^:private data-store
  (atom ["Costa Mason Male Green 12/14/1985"
         "Hopper Grace Female Black 12/9/1906"]))

(deftest sort-by-gender-test
  (testing "Response format"
    (let [resp (handler/sort-by-gender data-store)]
      (is (= 200 (:status resp)))
      (is (get-in resp [:body :records]))
      (is (= "gender" (get-in resp [:body :sorted-by]))))))

(deftest sort-by-dob-test
  (testing "Response format"
    (let [resp (handler/sort-by-dob data-store)]
      (is (= 200 (:status resp)))
      (is (get-in resp [:body :records]))
      (is (= "date of birth" (get-in resp [:body :sorted-by]))))))

(deftest sort-by-name-test
  (testing "Response format"
    (let [resp (handler/sort-by-name data-store)]
      (is (= 200 (:status resp)))
      (is (get-in resp [:body :records]))
      (is (= "last name" (get-in resp [:body :sorted-by]))))))

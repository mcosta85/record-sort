(ns record-sort-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [org.httpkit.server :refer [run-server]]
            [record-sort-core.core :as sort]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.http-response :refer :all]))

(defonce server (atom nil))
(def ^:private data-store (atom []))

(defn- add-record!
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


(defroutes approutes
  (POST "/records" req (add-record! data-store req))
  (GET "/records/gender" [] (sort-by-gender data-store))
  (GET "/records/birthdate" [] (sort-by-dob data-store))
  (GET "/records/name" [] (sort-by-name data-store))
  (route/not-found "<h1>Page not found</h1>"))

(def app
  (wrap-json-response approutes))

(defn start-server
  []
  (println "Starting server on port 8080...")
  (reset! server (run-server app {:port 8080})))

(defn stop-server
  []
  (@server :timeout 50)
  (reset! server nil))

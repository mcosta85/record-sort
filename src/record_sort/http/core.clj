(ns record-sort.http.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [org.httpkit.server :refer [run-server]]
            [record-sort.http.handler :as handler]
            [ring.middleware.json :refer [wrap-json-response]]))

(defonce server (atom nil))
(def ^:private data-store (atom []))

(defroutes approutes
  (POST "/records" req (handler/add-record! data-store req))
  (GET "/records/gender" [] (handler/sort-by-gender data-store))
  (GET "/records/birthdate" [] (handler/sort-by-dob data-store))
  (GET "/records/name" [] (handler/sort-by-name data-store))
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

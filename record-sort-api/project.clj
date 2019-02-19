(defproject record-sort-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [compojure "1.6.1"]
                 [http-kit "2.3.0"]
                 [metosin/ring-http-response "0.9.1"]
                 [ring/ring-json "0.4.0"]
                 [record-sort-core "0.1.0-SNAPSHOT"]]
  :plugins [[lein-ring "0.12.4"]]
  :ring {:handler record-sort-api.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})

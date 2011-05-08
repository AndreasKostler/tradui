(defproject tradui "1.0.0-SNAPSHOT"
  :description "FIXME: write"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [hiccup "0.3.4"]
                 [compojure "0.6.3"]]
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT"]
                     [lein-ring "0.4.0"]
                     [ring-serve "0.1.0"]]
  :ring {:handler tradui.core/app})

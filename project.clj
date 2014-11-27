(defproject clojure-exchange-2014 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[cider/cider-nrepl "0.7.0"]]
  :repl-options {:port 9876}
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.5"]]
                   :source-paths ["dev"]}}
  :dependencies [[eu.cassiel/tramway "1.0.0"]
                 [org.clojure/clojure "1.6.0"]]
  :jvm-opts ["-XX:+UseG1GC"])

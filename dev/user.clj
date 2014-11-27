(ns user
  (:require (eu.cassiel [tramway :as app])
            (eu.cassiel.tramway [auto :as a])
            [quil.core :as q]
            [clojure.core.async :as async]
            (clojure.tools.namespace [repl :as rr])))

(def system nil)

(defn init
  "Constructs the current development system."
  []
  (alter-var-root #'system
                  (constantly (app/create-app []
                                              :frame-rate 30))))

(defn start
  "Starts the current development system."
  []
  (alter-var-root #'system (fn [s] (app/start s
                                             ;; :display 0
                                             :size [960 540]
                                             :renderer :p3d
                                             :init #(do (q/color-mode :rgb 255)
                                                        (q/ortho)
                                                        #_ (q/no-cursor))))))

(defn stop
  "Shuts down and destroys the current development system."
  []
  (alter-var-root #'system
    (fn [s] (when s (app/stop s)))))

(defn go
  "Initializes the current development system and starts it running."
  []
  (init)
  (start))

(defn reset []
  (stop)
  (rr/refresh :after 'user/go))

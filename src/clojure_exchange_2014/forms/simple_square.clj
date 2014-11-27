(ns clojure-exchange-2014.forms.simple-square
  "Simple test - mouse click in a square. Currently, mouse events
   hacked via a channel."
  (:require (eu.cassiel [tramway :as m])
            (eu.cassiel.tramway [auto :as a])
            (eu.cassiel.tramway [forms :refer [FORM]]
                                [scene :as $])
            (eu.cassiel [twizzle :as tw])
            (eu.cassiel.twizzle [interpolators :as twi])
            [clojure.core.async :as async]))

(def simple-square
  ;; ch: channel which accepts automation state mappers.
  (let [ch (async/chan 10)]
    (reify FORM
      (init-form-state [self] nil)
      (automation-inits [self] nil)
      (automation-interps [self] nil)

      ;; Update also looks at our event channel.
      (update [self t struct-state auto-state]
        {:auto-state (async/alt!! ch ([f] (f auto-state))
                                  :default auto-state)})

      (nodes [self struct-state auto-state]
        (let [fill-grey (* 255 (or (tw/sample auto-state [:simple-square :fill]) 0))]
          [(->> ($/rect 0 0 0 200 200
                        :mouse-fn (fn [& {:keys [click-type x y]}]
                                    (println click-type x y)
                                    (let [norm-x (/ (+ x 100) 200)
                                          norm-y (/ (+ y 100) 200)]
                                      (async/>!! ch (-> a/void
                                                        (a/auto :ch [:scene :bg]
                                                                :in 0
                                                                :to [(* 255 norm-x) 0 0])
                                                        (a/auto :ch [:simple-square :fill]
                                                                :in 0
                                                                :to norm-y)))
                                      true)))
                ($/fill [fill-grey])
                ($/stroke [50])
                ($/with-translation [100 100 0])
                ($/with-rotation 1/16))])))))

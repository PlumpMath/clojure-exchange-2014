(reset)

(stop)
(start)

(-> a/void
    (a/auto :ch [:scene :bg]
            :in 5
            :to [255 255 0])
    (a/fire system))

(-> a/void
    (a/auto :ch [:scene :bg]
            :in 1
            :to [0 0 0])
    (a/fire system))

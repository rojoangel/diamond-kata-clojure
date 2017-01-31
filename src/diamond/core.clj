(ns diamond.core
  (:gen-class))

(defn create [ch]
  (repeat (+ 1 (* 2 (- (int ch) (int \A)))) "A"))
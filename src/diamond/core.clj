(ns diamond.core
  (:gen-class))

(defn- char->int [ch]
  (- (int ch) (int \A)))

(defn square-side [ch]
  (inc (* 2 (char->int ch))))

(defn create [ch]
  (repeat (square-side ch) (repeat (square-side ch) \A)))

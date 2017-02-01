(ns diamond.core
  (:gen-class))

(def upper-case-chars
  (let [A (int \A)
        Z (int \Z)]
    (map char (range A (inc Z)))))

(defn char->int [ch]
  (- (int ch) (int \A)))

(defn square-side [ch]
  (inc (* 2 (char->int ch))))

(defn create [ch]
  (repeat (square-side ch) (repeat (square-side ch) \A)))

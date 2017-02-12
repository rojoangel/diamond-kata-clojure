(ns diamond.core
  (:gen-class))

(defn- char->int [char]
  (let [char-int (int char)
        A-int (int \A)]
    (- char-int A-int)))

(defn square-side [char]
  (+ 1 (* (char->int char) 2)))

(defn create [char]
  (let [square-side (square-side char)]
    (repeat square-side (repeat square-side \A))))

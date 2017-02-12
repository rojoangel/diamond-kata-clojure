(ns diamond.core
  (:gen-class))

(def upper-case-chars
  (let [A (int \A)
        Z (int \Z)]
    (map char (range A Z))))

(defn- char->int [char]
  (let [char-int (int char)
        A-int (int \A)]
    (- char-int A-int)))

(defn square-side [char]
  (+ 1 (* (char->int char) 2)))

(defn- half-square-side [char]
  (inc (quot (square-side char) 2)))

(defn mirror [coll]
  (concat (reverse (rest coll)) coll))

(defn create [char]
  (let [square-side (square-side char)]
    (repeat square-side (mirror (take (half-square-side char) upper-case-chars)))))

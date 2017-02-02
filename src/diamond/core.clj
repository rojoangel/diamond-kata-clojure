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

(defn- mirror [coll]
  (concat (reverse (drop 1 coll)) coll))

(defn square-row [ch]
  (let [half-square-side (/ (inc (square-side ch)) 2)
        half-square-row (take half-square-side upper-case-chars)]
    (mirror half-square-row)))

(defn create [ch]
  (repeat (square-side ch) (square-row ch)))

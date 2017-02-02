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

(defn half-square-side [ch]
  (/ (inc (square-side ch)) 2))

(defn half-square-row [ch]
  (take (half-square-side ch) upper-case-chars))

(defn square-row [ch]
  (let [half-row (half-square-row ch)]
    (concat (reverse (drop 1 half-row)) half-row)))

(defn create [ch]
  (repeat (square-side ch) (square-row ch)))

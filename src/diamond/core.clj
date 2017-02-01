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
  (let [top-half (take (inc (char->int ch)) (map (partial repeat (square-side ch)) upper-case-chars))
        bottom-half (reverse (take (dec (count top-half)) top-half))]
    (concat top-half bottom-half)))

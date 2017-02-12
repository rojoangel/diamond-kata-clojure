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

(defn- mirror [coll]
  (concat (reverse (rest coll)) coll))

(defn- replace-other-chars-with-spaces [char coll]
  (map #(if (= char %) char \space) coll))

(defn- diamond-row [char]
  (mirror (take (half-square-side char) upper-case-chars)))

(defn create [char]
  (let [half-square-side (half-square-side char)]
    (mirror
      (reverse
        (map replace-other-chars-with-spaces upper-case-chars
             (repeat half-square-side (diamond-row char)))))))

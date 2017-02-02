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

(defn- half-square-side [ch]
  (quot (inc (square-side ch)) 2))

(defn- square-row [ch]
  (let [half-square-side (half-square-side ch)
        half-square-row (take half-square-side upper-case-chars)]
    (mirror half-square-row)))

(defn- replace-other-chars-with-spaces [ch row]
  (map #(if (= ch %) ch \space) row))

(defn create [ch]
  (mirror
    (reverse
      (map replace-other-chars-with-spaces upper-case-chars
           (repeat (half-square-side ch) (square-row ch))))))

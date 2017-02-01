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
  (let [top-right-half (take (inc (char->int ch)) (map (fn [line-char] (concat
                                                                         (repeat (char->int line-char) \space)
                                                                         (cons line-char nil)
                                                                         (repeat (- (quot (square-side ch) 2) (char->int line-char)) \space))) upper-case-chars))
        top-half (map (fn [line] (concat (reverse line) (drop 1 line))) top-right-half)
        bottom-half (reverse (take (dec (count top-half)) top-half))]
    (concat top-half bottom-half)))

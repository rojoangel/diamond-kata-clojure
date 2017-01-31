(ns diamond.core
  (:gen-class))

(defn- char->int [ch]
  (- (int ch) (int \A)))

(defn- line-count [ch]
  (inc (* 2 (char->int ch))))

(defn create [ch]
  (repeat (line-count ch) (repeat (line-count ch) \A)))

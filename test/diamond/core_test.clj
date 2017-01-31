(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]
            [clojure.string :as str]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]))

(def upper-case-chars
  (let [A (int \A)
        Z (int \Z)]
    (map char (range A Z))))

(defn number-of-lines [ch]
  (+ 1 (* 2 (- (int ch) (int \A)))))

(defspec produces-the-expected-number-of-lines
         100
         (prop/for-all [v (gen/elements upper-case-chars)]
                       (= (count (create v))
                          (number-of-lines v))))

(defspec the-length-of-each-line-is-equal-to-the-total-number-of-lines
         100
         (prop/for-all [v (gen/elements upper-case-chars)]
                       (every? #(= (count %) (number-of-lines v)) (create v))))

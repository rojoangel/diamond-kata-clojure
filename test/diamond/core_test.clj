(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.string :as str]))

(defn upper-case-chars []
  (let [A (int \A)
        Z (int \Z)]
    (map char (range A Z))))

(defspec produces-a-square                                  ;; the name of the test
         100                                                ;; the number of iterations for test.check to test
         (prop/for-all [char (gen/elements (upper-case-chars))]
                       (let [diamond (create char)
                             rows (count diamond)]
                         (every? #(= rows (count %)) diamond))))

(defn- char->int [char]
  (let [char-int (int char)
        A-int (int \A)]
    (- char-int A-int)))

(defn- square-side [char]
  (+ 1 (* (char->int char) 2)))

(defspec the-square-has-the-right-size                      ;; the name of the test
         100                                                ;; the number of iterations for test.check to test
         (prop/for-all [char (gen/elements (upper-case-chars))]
                       (let [diamond (create char)
                             square-side (square-side char)]
                         (= (count diamond) square-side))))

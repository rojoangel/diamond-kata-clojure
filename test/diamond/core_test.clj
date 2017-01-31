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

(defspec produces-a-square
         100
         (prop/for-all [v (gen/elements upper-case-chars)]
                       (every? #(= (count %) (square-side v)) (create v))))

(defspec produces-a-square-of-the-appropriate-size
         100
         (prop/for-all [v (gen/elements upper-case-chars)]
                       (= (count (create v))
                          (square-side v))))


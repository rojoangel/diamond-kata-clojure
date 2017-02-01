(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]
            [clojure.string :as str]
            [clojure.set :as set]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]))


(defn int->char [idx]
  (let [A (int \A)]
    (char (+ idx A))))


(defspec produces-a-square
         100
         (prop/for-all [v (gen/elements upper-case-chars)]
                       (every? #(= (count %) (square-side v)) (create v))))

(defspec produces-a-square-of-the-appropriate-size
         100
         (prop/for-all [v (gen/elements upper-case-chars)]
                       (= (count (create v))
                          (square-side v))))

(defspec single-letter-per-line
         100
         (prop/for-all [v (gen/elements upper-case-chars)]
                       (let [top-half (take (inc (char->int v)) (create v))]
                         (every? (fn [[idx line]] (set/subset? (set line) #{\space (int->char idx)})) (map-indexed vector top-half)))))

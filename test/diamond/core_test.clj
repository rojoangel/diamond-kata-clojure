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

(defn first-half [col]
  (let [size (quot (inc (count col)) 2)]
    (take size col)))

(defn second-half [col]
  (let [size (quot (count col) 2)]
    (drop size col)))

(defspec produces-a-square
         100
         (prop/for-all [ch (gen/elements upper-case-chars)]
                       (let [diamond (create ch)]
                         (every? #(= (count %) (square-side ch)) diamond))))

(defspec produces-a-square-of-the-appropriate-size
         100
         (prop/for-all [ch (gen/elements upper-case-chars)]
                       (let [diamond (create ch)]
                         (= (count diamond)
                            (square-side ch)))))

(defspec single-letter-per-line
         100
         (prop/for-all [ch (gen/elements upper-case-chars)]
                       (let [diamond (create ch)]
                         (every?
                           (fn [[idx line]] (set/subset? (set line) #{\space (int->char idx)}))
                           (map-indexed vector (first-half diamond))))))

(defspec is-vertically-symmetrical
         100
         (prop/for-all [ch (gen/elements upper-case-chars)]
                       (let [diamond (create ch)]
                         (= (first-half diamond)
                            (reverse (second-half diamond))))))

(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.string :as str]))

(defn- first-half [diamond]
  (let [size (inc (quot (count diamond) 2))]
    (take size diamond)))

(defn second-half [diamond]
  (let [size (inc (quot (count diamond) 2))]
    (take size diamond)))

(defn- int->char [idx]
  (let [A-idx (int \A)]
    (char (+ idx A-idx))))

(defspec produces-a-square                                  ;; the name of the test
         100                                                ;; the number of iterations for test.check to test
         (prop/for-all [char (gen/elements upper-case-chars)]
                       (let [diamond (create char)
                             rows (count diamond)]
                         (every? #(= rows (count %)) diamond))))

(defspec the-square-has-the-right-size                      ;; the name of the test
         100                                                ;; the number of iterations for test.check to test
         (prop/for-all [char (gen/elements upper-case-chars)]
                       (let [diamond (create char)
                             square-side (square-side char)]
                         (= (count diamond) square-side))))

;; start leveraging horizontal symmetry
(defspec each-row-contains-the-right-character              ;; the name of the test
         100                                                ;; the number of iterations for test.check to test
         (prop/for-all [char (gen/elements upper-case-chars)]
                       (let [diamond (create char)
                             top-diamond (first-half diamond)]
                         (every?
                           (fn [[idx line]] (some #(= (int->char idx) %) line))
                           (map-indexed vector top-diamond)))))

(defspec is-symmetrical-on-the-horizontal-plane             ;; the name of the test
         100                                                ;; the number of iterations for test.check to test
         (prop/for-all [char (gen/elements upper-case-chars)]
                       (let [diamond (create char)
                             top-diamond (first-half diamond)
                             bottom-diamond (second-half diamond)]
                         (= top-diamond (reverse bottom-diamond)))))

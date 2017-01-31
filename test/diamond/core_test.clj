(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]
            [clojure.string :as str]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]))

(defn upper-case-chars []
  (let [A (int \A)
        Z (int \Z)]
    (map char (range A Z))))

(defspec produces-some-lines
         100
         (prop/for-all [v (gen/elements (upper-case-chars))]
                       (> (count (create v)) 0)))

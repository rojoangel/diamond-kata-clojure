(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]
            [clojure.string :as str]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]))

(defspec produces-some-lines
         100
         (prop/for-all [v (gen/fmap str/upper-case gen/char-alpha)]
                       (> (count (create v)) 0)))
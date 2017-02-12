(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.string :as str]))

(defspec produces-some-lines                                ;; the name of the test
         100                                                ;; the number of iterations for test.check to test
         (prop/for-all [char (gen/fmap str/upper-case gen/char-alpha)]
                       (> (count (create char)) 0)))

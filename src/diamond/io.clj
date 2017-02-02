(ns diamond.io
  (:require [diamond.core :refer :all]
            [clojure.string :as str]))

(defn print [diamond]
  (println (str/replace (str/join "\n" (map #(apply str %) diamond)) #" " "-")))

(ns coercer.test.core
  (:import clojure.lang.Keyword)
  (:use clojure.test
        coercer.core))

(deftest coerce-test
  (are [t x y] (= (coerce x t) y)
    String "a" "a"
    String 1 "1"
    Integer "1" 1
    String :a "a"
    Keyword "a" :a))
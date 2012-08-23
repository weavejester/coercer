(ns coercer.test.core
  (:import [clojure.lang Keyword Symbol]
           [org.joda.time DateTime]
           [java.util Date])
  (:use clojure.test
        coercer.core
        [clj-time.core :only (date-time)]
        [clj-time.coerce :only (to-date)]))

(deftest coerce-test
  (testing "basic types"
    (are [t x y] (= (coerce x t) y)
      String "a" "a"
      String 1 "1"
      Integer "1" 1
      Long "1" (long 1)
      Double "1.0" 1.0
      String :a "a"
      Keyword "a" :a
      Symbol "a" 'a))
  (testing "dates and times"
    (let [dt (date-time 2012 04 16 10 36 23)]
      (are [t x y] (= (coerce x t) y)
        String   dt "2012-04-16T10:36:23.000Z"
        Date     dt (to-date dt)
        DateTime "2012-04-16T10:36:23.000Z" dt
        Long     dt 1334572583000
        DateTime 1334572583000 dt)))
  (testing "primitive arrays"
    (are [t x y] (= (seq (coerce x t)) (seq y))
      bytes "a" (.getBytes "a")
      byte-array "a" (.getBytes "a"))
    (are [t x y] (= (coerce x t) y)
      String (.getBytes "a") "a"
      Integer (.getBytes "1") 1)))

(ns coercer.core
  (:use [clj-time.coerce :only (from-date from-string to-date to-string)])
  (:import [clojure.lang Keyword Symbol]
           [org.joda.time DateTime]
           [java.util Date]))

(defmulti coerce
  "Multimethod to convert a value x to a type t."
  (fn [x t] [(type x) t]))

(defmethod coerce [String Integer] [s _]
  (try (Integer. s)
       (catch NumberFormatException _)))

(defmethod coerce [Object Integer] [x _]
  (int x))

(defmethod coerce [String Double] [s _]
  (try (Double. s)
       (catch NumberFormatException _)))

(defmethod coerce [Object Double] [x _]
  (double x))

(defmethod coerce [String Float] [s _]
  (try (Float. s)
       (catch NumberFormatException _)))

(defmethod coerce [Object Float] [x _]
  (float x))

(defmethod coerce [Keyword String] [k _]
  (name k))

(defmethod coerce [String Keyword] [s _]
  (keyword s))

(defmethod coerce [Object Keyword] [x _]
  (-> (coerce x String)
      (coerce Keyword)))

(defmethod coerce [String Symbol] [s _]
  (symbol s))

(defmethod coerce [Object Symbol] [x _]
  (-> (coerce x String)
      (coerce Keyword)))

(defmethod coerce [String DateTime] [s _]
  (from-string s))

(defmethod coerce [DateTime String] [dt _]
  (to-string dt))

(defmethod coerce [Date DateTime] [d _]
  (from-date d))

(defmethod coerce [DateTime Date] [dt _]
  (to-date dt))

(defmethod coerce [Date String] [d _]
  (-> (coerce d DateTime)
      (coerce String)))

(defmethod coerce [Object Date] [x _]
  (-> (coerce x DateTime)
      (coerce Date)))

(defmethod coerce [Object String] [x _]
  (.toString x))

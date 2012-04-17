(ns coercer.core
  (:require [clj-time.coerce :as time])
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

(defmethod coerce [String Long] [s _]
  (try (Long. s)
       (catch NumberFormatException _)))

(defmethod coerce [Object Long] [x _]
  (long x))

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
  (time/from-string s))

(defmethod coerce [DateTime String] [dt _]
  (time/to-string dt))

(defmethod coerce [DateTime Long] [dt _]
  (time/to-long dt))

(defmethod coerce [Long DateTime] [x _]
  (time/from-long x))

(defmethod coerce [Date DateTime] [d _]
  (time/from-date d))

(defmethod coerce [DateTime Date] [dt _]
  (time/to-date dt))

(defmethod coerce [Date String] [d _]
  (-> (coerce d DateTime)
      (coerce String)))

(defmethod coerce [Object Date] [x _]
  (-> (coerce x DateTime)
      (coerce Date)))

(defmethod coerce [Object String] [x _]
  (.toString x))

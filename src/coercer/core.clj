(ns coercer.core
  (:import clojure.lang.Keyword))

(defmulti coerce
  "Multimethod to convert a value x to a type t."
  (fn [x t] [(type x) t]))

(defmethod coerce [String Integer] [s _]
  (Integer. s))

(defmethod coerce [Keyword String] [k _]
  (name k))

(defmethod coerce [String Keyword] [s _]
  (keyword s))

(defmethod coerce [Object String] [x _]
  (.toString x))

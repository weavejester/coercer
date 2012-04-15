(ns coercer.core
  (:import [clojure.lang Keyword Symbol]))

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

(defmethod coerce [Object String] [x _]
  (.toString x))

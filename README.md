# Coercer

A library for converting data of one type into another type.

## Installation

Add the following dependency to your `project.clj` file:

    [coercer "0.1.0-SNAPSHOT"]

## Usage

Use the `coerce` multimethod to convert between types:

```clojure
(use 'coercer.core)

(coerce 1 String)      ; => "1"
(coerce "5.5" Double)  ; => 5.5
(coerce "foo" Keyword) ; => :foo
(coerce :abc String)   ; => "abc"
```

Currently `coerce` supports the following types by default:

* String
* Integer
* Float
* Double
* Keyword
* Symbol

## License

Copyright © 2012 James Reeves

Distributed under the Eclipse Public License, the same as Clojure.

(ns tradui.core
  (:use hiccup.core)
  (:require [tradui.util.regex :as regex]))

(declare *separator-tags*)
(declare *typeface-tags*)
(defn element-type [m]
  (cond ((:type m) *separator-tags*) :separator
        ((:type m) *typeface-tags*) :typeface
        :else (:type m)))

(defmulti emit (fn [m] (element-type m)))

;; I will need some flags to further specify the behaviour
;; e.g. creole within text emphasis needs to be parsed:
;; **bold and \/\/italics**
(def bold
  (regex/re-create [:bold
                    (regex/sl "**")
                    :body "(.+?)"
                    (regex/sl "**")]))

(def italics
  (regex/re-create [:italics
                    (regex/sl "//")
                    :body "(.+?)"
                    (regex/sl "//")]))

;; Closing (right-side) equal signs are optional
;; Left side takes precedence
;; Whitespace is allowed before left equal sign(s)
;; Only whitespace characters are allowed after the closing equal
;; signs
;; No line breaks within headings
;; No markup will be parsed within a heading
(def headings
  (regex/re-create [:heading
                    "[ \t]*"
                    :level "(=+)"
                    :body "( .+? )"
                    :closing "(=*[ \t]*)\n"]))

(def linebreak [:linebreak (regex/sl "\\")])
(def horizontal-line [:h-line (regex/sl "----")])
(def no-wiki [:no-wiki
              (regex/sl "{{{")
              :body "(.+?)"
              (regex/sl "}}}")])

(def link [:link
           (regex/sl "[[")
           :target "(.+?)"
           regex/ws*
           :text (str "[|]" regex/ws* "(.+?)" regex/ws*)
           (regex/sl "]]")])

(def image [:image
            (regex/sl "{{")
            :target (str "(.+?)" regex/ws*)
            :text (str (regex/sl "|") regex/ws* "(.+?)" regex/ws*)
            (regex/sl "}}")])



(ns tradui.extension.codeblock
  (:require [tradui.util.regex :as regex])
  (:use hiccup.core)
  (:use tradui.core))

(def codeblock
  (regex/re-create [:codeblock (regex/sl "<<")
                    "[ \t]*"
                    :lang "(.+?)"
                    (regex/sl "\n")
                    :body "(.+?)"xsxc
                    "[ \t]*"
                    ">>"]))

(defmethod emit :codeblock [m]
  (html [:pre {:name "code" :class (:lang m)} (:body m)]))




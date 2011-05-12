(ns tradui.extension.latexmath
  (:use [incanter.latex :only (latex)])
  (:use [incanter io core])
  (:require [tradui.util.regex :as regex])
  (:use [tradui.core])
  (:use [hiccup.core]))

(def latexmath [:latex
                (regex/sl "[[[")
                :target (str "(.+?)" regex/ws*)
                :text (str (regex/sl "|") regex/ws* "(.+?)" regex/ws*)
                (regex/sl "]]]")])

(defn create-image-file [filename latex-s]
  "Create a img-file from the latex-s in static"
  (let [[img-name extension] (seq (.split filename "\\."))
        img (latex latex-s :color (java.awt.Color/red)
                   :background (java.awt.Color/white)
                   :border [500 500 500 500])]
    (save img filename :extension extension)))

(defmethod emit :latexmath [m]
  (let [filename (:target m)]
    (create-image-file filename (:text m))
    (html [:img {:src (str "static/" filename)} filename])))

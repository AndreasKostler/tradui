(ns tradui.extension.latexmath)

(def latexmath [:latex
                (regex/sl "[[[")
                :target (str ("(.+?)" regex/ws*))
                :text (str (regex/sl "|") regex/ws* "(.+?)" regex/ws*)
                (regex/sl "]]]")])

(defn create-image-file [filename latex-s]
  "This does all the heavy lifting:
   * Create a img-file from the latex-s in static/"
  (let [[img-name extension] ((.split filename) "\.")]))

(defmethod emit :latexmath [m]
    (create-image-file img-name extension (:text m))
    (html [:img {:src (str "static\/" (target.m))} img-name]))

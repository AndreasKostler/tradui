(ns tradui.extension.typefaces
  (:use tradui.util.regex))

(def monospace
  (regex/re-create [:monospace
                    (regex/sl "##")
                    :body "(.+?)"
                    (regex/sl "##")]))

(def superscript
  (regex/re-create [:superscript
                    (regex/sl "^^")
                    :body "(.+?)"
                    (regex/sl "^^")]))

(def subscript
  (regex/re-create [:subscript
                    (regex/sl ",,")
                    :body "(.+?)"
                    (regex/sl ",,")]))

(def underline
  (regex/re-create [:underline
                    (regex/sl "__")
                    :body "(.+?)"
                    (regex/sl "__")]))

(def delete
  (regex/re-create [:delete
                    (regex/sl "~~")
                    :body "(.+?)"
                    (regex/sl "~~")]))

(ns tradui.adapter.hiccup
  (:use hiccup.core)
  (:use tradui.core))

(def *separator-tags*
  {:h-line :hr
   :linebreak :br})

(def *typeface-tags*
  {:bold :strong
   :italics :em})

(defmethod emit :separator [m]
  (html [((:type m) *separator-tags*)]))

(defmethod emit :typeface [m]
  (html [((:type m) *typeface-tags*) (:body m)]))

(defmethod emit :heading [m]
  (let [heading-levels #{:h1 :h2 :h3 :h4 :h5 :h6}
        level (count (:level m))]
    (if-let [tag (heading-levels (keyword (str "h" level)))]
      (html [tag (:body m)])
      ((:type m) m))))

(defmethod emit :link [m]
  (html [:a {:href (:target m)} (:text m)]))
(defmethod emit :image [m]
  (html [:img {:src (:target m)} (:text m)]))

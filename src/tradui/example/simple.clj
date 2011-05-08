(ns tradui.example.simple)

;; Simple webapp to demonstrate the usage of tradui. This can also be
;; used as a testbed for extensions

;; 1) Create simple webpage with form
;; 2) Read content of form
;; 3) Parse content and produce html by using tradui.adapter.hiccup
;; 4) Update webpage to display translated form content

;; Also demonstrate use of codeblock extension

(defn index-page []
  (html
   [:head
    [:title "Hello World"]]
   [:body
    [:h1 "Hello World"]]))

(defroutes main-routes
  (GET "/" [] (index-page))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> (handler/site main-routes)
      (wrap-base-url)))

(ns semantic-forms.core
  (:require [cheshire.core :as c]
            [clojure.java.io :as io]))


;; (defn get-types [coll]
;;   (->> coll
;;        (map #(get % "@type"))
;;        frequencies
;;        ))

;; ;; Treat the jsonld as simple json and filter on class
;; (with-open [s (io/reader schema-file)]
;;   (-> (c/parse-stream s)
;;       (get "@graph")
;;       get-types
;;       ))
;; (->
;;   schema-file
;;   io/reader
;;   (c/parse-stream true))

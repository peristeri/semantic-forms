(ns semantic-forms.db.submissions
  (:require [hugsql.core :as hugsql]

            [clojure.java.io :as io]
            [cheshire.core :as c]))

(hugsql/def-db-fns "semantic_forms/db/sql/submissions.sql")

(def db {:connection-uri "jdbc:sqlite:resources/db.sqlite"})

(comment

  (require '[cheshire.core :as c])
  (require '[clojure.java.io :as io])
  (create-submissions-table db)
  (def sample-json (with-open [s (io/reader (io/resource "schema.org.jsonld"))]
                     (-> (c/parse-stream s)
                         (get "@graph")
                         (first))))
  (insert-submission db {:form-type (get sample-json "@type") :form (c/generate-string sample-json)})
  (all-submissions db)
  )

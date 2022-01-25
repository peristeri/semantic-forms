(ns semantic-forms.controllers
  (:require [semantic-forms.db.submissions :as submissions]
            [clojure.java.io :as io]))

(def schema-file (io/resource "schema.org.jsonld"))

(defmulti schema-map "@type")

(defmethod schema-map "rdf:Property")

(defmethod schema-map "rdfs:Class")

(defn find-schema [form-type]
  (with-open [s (io/reader schema-file)]))

(defn all-submissions [req]
  (let [submissions (submissions/all-submissions (:db req))]
    (-> req
        (assoc-in [:params :submissions] submissions)
        (assoc :application/view "list"))))

(defn edit-submission [req]
  (let [db         (:db req)
        submission (when-let [id (get-in req [:path-params :id])]
                     (submission/submission-by-id db id))]
    (-> req
        (update :params assoc
                :submission submission)
        (assoc :application/view "form"))))

(defn edit-new-submission [req]
  (let [db          (:db req)
        form-schema (when-let [form-type (get-in req [:path-params :form-type])]
                      (find-schema form-type))]
    (-> req
        (update :params assoc
                :schema form-schema)
        (assoc :application/view "form"))))

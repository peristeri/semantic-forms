

-- :name create-submissions-table
-- :command :execute
-- :result :raw
CREATE TABLE submissions (
  submission_id INTEGER PRIMARY KEY AUTOINCREMENT,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  form_type TEXT NOT NULL,
  form TEXT NOT NULL
)

-- :name insert-submission :! :n
INSERT INTO submissions(form_type, form)
values (:form-type, :form)

-- :name all-submissions :? :*
SELECT * FROM submissions order by submission_id

-- :name submission-by-id :? :1
SELECT * FROM submissions where submission_id = :id

(ns leiningen.title
  (:use title.core))

(defn title [project path & args]
  (start-watching path #(print-results (eval-top-level path) path)))


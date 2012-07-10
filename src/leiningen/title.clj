(ns leiningen.title
  (:use title.core))

(defn title [project path & args]
  (println title path)
  (start-watching path #(print-results (eval-top-level path) path)))


(ns leiningen.title
  (:use title.core))

(defn title [project path & args]
  (title.core/-main path))


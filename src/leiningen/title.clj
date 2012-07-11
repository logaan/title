(ns leiningen.title
  (:use title.core))

(defn title [project]
  (title.core/-main "src"))


(ns title.watch
  (:require [clojure.java.io :as io])
  (:import java.util.Date))

(defn visible-files [path]
  (->> (file-seq (io/file path))
       (filter #(.isFile %))
       (filter #(re-matches #"[^\.].*" (.getName %)))))

(defn last-modified [files]
  (apply merge
    (map #(hash-map % (.lastModified %)) files)))

(defn modified-files [this-saw last-saw]
  (filter (fn [[f m]] (> m (last-saw f))) this-saw))

(defn watch [path task last-saw]
  (let [this-saw (last-modified (visible-files path))]
    (doall (map
             (fn [[file mod]] (task file))
             (modified-files this-saw last-saw)))
    (Thread/sleep 500)
    (recur path task this-saw)))

(defn start-watching [path task]
  (watch path task (last-modified (visible-files path))))


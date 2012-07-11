(ns title.watch
  (:require [clojure.java.io :as io])
  (:import java.util.Date))

(defn last-modified [path]
  (new Date (.lastModified (io/file path))))

(defn watch [path task last-saw]
  (let [this-saw (last-modified path)]
    (if (= 1 (compare this-saw last-saw))
      (try
        (task)
        (catch Exception e (.printStackTrace e))))
    (Thread/sleep 200)
    (recur path task this-saw)))

(defn start-watching [path task]
  (task)
  (watch path task (last-modified path)))

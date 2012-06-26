(ns title.core
  (:require [clojure.java.io :as io]))

(import java.util.Date)

; Core to watching
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

; Core to evaling
(defn eval-top-level [path]
  (let [file (slurp path)
        exprs (read-string (str "[" file "]"))]
    (map #(eval %) exprs)))

(defn print-results [results path]
  (println (str "-- " path " --"))
  (doall (map println results))
  (println))

(defn -main [path]
  (start-watching path #(print-results (eval-top-level path) path)))


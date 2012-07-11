(ns title.core
  (:use title.watch))

(defn eval-top-level [file]
  (let [contents (slurp file)
        exprs (read-string (str "[" contents "]"))]
    (map #(eval %) exprs)))

(defn print-results [results file]
  (println (str "[H[J" "-- " (.getPath file) " --"))
  (doall (map println results))
  (println))

(defn task [file]
  (try
    (print-results (eval-top-level file) file)
    (catch Exception e (.printStackTrace e))))

(defn -main [path]
  (start-watching path task))


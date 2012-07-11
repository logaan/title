(ns title.core
  (:use title.watch))

(defn eval-top-level [path]
  (let [file (slurp path)
        exprs (read-string (str "[" file "]"))]
    (map #(eval %) exprs)))

(defn print-results [results path]
  (println (str "[H[J" "-- " path " --"))
  (doall (map println results))
  (println))

(defn -main [path]
  (start-watching path #(print-results (eval-top-level path) path)))


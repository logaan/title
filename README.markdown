# title

title stands for Totally Instant Top Level Evaluator

It will watch your clojure files and give you a live view of what your file
evaluates to. Output looks like:

    -- src/title/core.clj --
    nil
    java.util.Date
    #'title.core/last-modified
    #'title.core/watch
    #'title.core/start-watching
    #'title.core/eval-top-level
    #'title.core/print-results
    #'title.core/-main

## Usage

To get it running:

* Checkout the project from github
* Run `lein deps`
* Run `lein run /path/to/your/file.clj`

## Todo

* Migrate to lein plugin
* Watch all files in src and test
* Whenever a file is changed
  * eval that file
  * display the results
  * remove-ns that namespace
    * prevents polution between runs
* Split core into two namespaces
* Add tests?

## License

Copyright (C) 2012 Logan Campbell

Distributed under the Eclipse Public License, the same as Clojure.

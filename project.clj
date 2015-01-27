(defproject tramboard-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"

  :dependencies [[cljs-ajax "0.3.4"]
                 [cljs-uuid "0.0.4"]
                 [clj-time "0.9.0"]
                 [compojure "1.3.1"]
                 [com.andrewmcveigh/cljs-time "0.3.0"]
                 [hiccup "1.0.0"]
                 [http-kit "2.1.18"]
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2511"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.om/om "0.8.1"]
                 [ring-json-response "0.2.0"]
                 [ring/ring-defaults "0.1.2"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [ring/ring-mock "0.2.0"]
                 [secretary "1.2.1"]]

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-git-deps "0.0.2-SNAPSHOT"]]

  :git-dependencies [["https://github.com/fterrier/om-autocomplete.git"]]

  :uberjar-name "tramboard-clj.jar"

  :cljsbuild {:builds {:app {:source-paths ["src" ".lein-git-deps/om-autocomplete/src/"]}}}

  :profiles {:dev {:source-paths ["env/dev/src"]
                   :dependencies [[figwheel "0.2.0-SNAPSHOT"]
                                  [omdev "0.1.3-SNAPSHOT"]
                                  [midje "1.6.3"]]
                   :plugins [[lein-figwheel "0.2.0-SNAPSHOT"]
                             [lein-ring "0.8.13"]
                             [lein-deps-tree "0.1.2"]
                             [lein-midje "3.1.3"]]
                   :aliases {"autotest" ["midje" ":autotest"]}
                   :ring {:handler tramboard-clj.core.handler/app}
                   :figwheel {:css-dirs ["resources/public/css"]}
                   :cljsbuild {:builds {:app {:source-paths ["env/dev/src"]
                                              :compiler {:output-to "resources/public/js/dev.js"
                                                         :output-dir "resources/public/out"
                                                         :optimizations :none
                                                         :pretty-print true
                                                         :source-map "resources/public/out.js.map"}}}}}

             :uberjar {:source-paths ["env/prod/src"]
                       :hooks [leiningen.cljsbuild]
                       :omit-source true
                       :aot :all
                       :cljsbuild {:builds {:app {:source-paths ["env/prod/src"]
                                                  :compiler {:output-to "resources/public/js/main.js"
                                                             :optimizations :advanced
                                                             :pretty-print false
                                                             :preamble ["react/react.min.js"]}}}}}})

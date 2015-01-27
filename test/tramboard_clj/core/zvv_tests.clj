(ns tramboard-clj.core.zvv-tests
  (:use midje.sweet)
  (:require [tramboard-clj.core.zvv :as zvv]))

(def fixtures
  {:central_2228 (slurp "fixtures/zvv_responses/central_2228.txt")})

(facts "transform zvv response"
       (fact "extracts station information under meta key"
             (zvv/transform-response (:central_2228 fixtures)) =>
             (contains {:meta (contains {:station_id "8588078" :station_name "Zürich, Central"})}))
       (fact "returns a list of departures"
             (zvv/transform-response (:central_2228 fixtures)) =>
             (contains {:departures (contains {:zvv_id "32108510"
                                               :name "31"
                                               :type "Bus-NF"
                                               :colors { :fg "#ffffff" :bg "#a5a2c6" }
                                               :to "Zürich, Hegibachplatz"
                                               :departure {
                                                           :scheduled "2015-01-27T22:38:00.000+01:00"
                                                           :realtime "2015-01-27T22:38:00.000+01:00"}})})))




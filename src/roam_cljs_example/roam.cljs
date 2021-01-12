(ns roam-cljs-example.roam
  (:require [applied-science.js-interop :as j]))

(defn q
  ([query]
   (let [serialised-query (pr-str query)]
     (-> (j/apply (j/get js/window :roamAlphaAPI)
                  :q
                  (to-array (into [serialised-query])))
         (js->clj :keywordize-keys true))))
  ([query & args]
   (let [serialised-query (pr-str query)]
     (-> (j/apply (j/get js/window :roamAlphaAPI)
                  :q
                  (to-array (into [serialised-query] args)))
         (js->clj :keywordize-keys true)))))

(comment

  (q '[:find ?e ?block-uid
       :where
       [?e :block/uid ?block-uid]])

  (q '[:find (pull ?e [*])
       :in $ ?block-uid
       :where
       [?e :block/uid ?block-uid]]
     "11-17-2020")

  )

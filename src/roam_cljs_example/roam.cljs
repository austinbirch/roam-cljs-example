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

  ,)

(defn create-block!
  " create a block with string `s` under the given `block-id` "
  [block-id s]
  (j/call (j/get js/window :roamAlphaAPI)
          :createBlock
          (clj->js {:action :createBlock
                    :location {:parent-uid block-id
                               :order      0}
                    :block    {:string s}})))

(comment
  (create-block! "Jjp5Z0b8D" "Hello, world!")
  ,)

(defn get-current-block!
  " return the block-id where the cursor currently is positioned
    (thank you David Vargas) "
  []
  (.slice (.-id (.-activeElement js/document)) -9))

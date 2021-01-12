(ns roam-cljs-example.core
  (:require [roam-cljs-example.roam :as roam]
            [reagent.core :as r]
            [reagent.dom]
            [applied-science.js-interop :as j]))

(enable-console-print!)

(defonce state (r/atom {:press-count 0}))

(defn counter
  []
  [:div {:style {:padding "1rem"
                 :background-color "#ddd"}}
   [:button {:on-click (fn []
                         (swap! state update :press-count inc))}
    "Press me"]
   [:div "Button pressed " (:press-count @state) " times."]])

(defn app
  []
  [:div {:style {:width "100%"
                 :background-color "#eee"}}
   [counter]])

(defn mount-app
  []
  (let [app-container (.getElementById js/document "roam-cljs-example-app")]
    (reagent.dom/render [#'app] app-container)))

(defn ensure-app-container
  []
  (when-not (.getElementById js/document "roam-cljs-example-app")
    (let [el (.createElement js/document "div")]
      (j/assoc! el :id "roam-cljs-example-app")
      (j/extend! (j/get el :style)
                 #js {:position "absolute"
                      :top "50px"
                      :left "50px"
                      :width "200px"
                      :height "70px"})
      (.appendChild (.-body js/document) el))))

(defn ^:dev/after-load after-load
  []
  (js/console.log "roam-cljs-example.core: after-load")
  (ensure-app-container)
  (mount-app))

(defn init
  []
  (js/console.log "roam-cljs-example.core: init")
  (ensure-app-container)
  (mount-app))


(comment

  (.getElementById js/document "roam-cljs-example-app")

  (init)

  (ensure-app-container)

  (mount-app)

  (roam/q '[:find ?e
            :where
            [?e :block/uid _]])

  (roam/q '[:find (pull ?e [:block/string])
            :where
            [?e :block/uid _]])

  )

A very rough template for starting a ClojureScript JS extension for Roam.

# setup

- run `yarn install` to get JavaScript dependencies
- run `shadow-cljs watch roam-cljs-example` to start building project
- create a new page to test on in Roam
- add a `{{[[roam/js]]}}` block to the test page, and *under* that block, add the following JavaScript snippet to load the ClojureScript output:

```javascript
var s = document.createElement("script");
s.src = "http://localhost:8081/js/roam-cljs-example.js";
s.id = "roamhill";
s.type = "text/javascript";
document.getElementsByTagName("head")[0].appendChild(s);
```

(Note that you may have to hide the left sidebar in Roam for the rendered control to be visible: "Ctrl-\")

You can connect to the shadow=cljs REPL session on port 9000 -- you start the ClojureScript REPL session by:

```clojure
(shadow/repl :roam-cljs-example)
```

# example test page

![roam-cljs-example test page in roam](./roam-cljs-example-test-page.png)

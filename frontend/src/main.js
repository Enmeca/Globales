// Initial configurations for run app
import Vue from 'vue'
Vue.config.productionTip = false

// Plugins and libraries

// Router
import Router from 'vue-router'
Vue.use(Router)
// BootstrapVue
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)

// Main component (Root)
import App from './App.vue'
// Router js
import router from './routes.js'

// Create and start app
new Vue({
  el: '#app',
  router,
  render: h => h(App),
})

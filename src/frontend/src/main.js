// Initial configurations for run app
import Vue from 'vue'
Vue.config.productionTip = false

// Plugins and libraries

// Vue Session
import Vuex from 'vuex'
Vue.use(Vuex)
import store from './store/store.js'

// Router
import Router from 'vue-router'
Vue.use(Router)
import router from './routes.js'

// Vue carousel
import VueCarousel from 'vue-carousel';
Vue.use(VueCarousel);


// BootstrapVue
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)

// Main component (Root)
import App from './App.vue'


// Create and start app
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App),
})

import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: null,
    },
    getters: {
        isLoggedIn: state => state.user != null,
    },
    mutations: {
        saveUser(state, user) {
            state.user = user
        },
        removeUser(state) {
            state.user = null
        },
    },
    //actions: {},
})
import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: null,
    },
    getters: {
        isLoggedIn: state => state.user != null,
        isLoggedInAdmin: state => state.user != null && state.user.isAdmin,
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
    plugins: [createPersistedState({
        storage: window.sessionStorage,
    })]
})
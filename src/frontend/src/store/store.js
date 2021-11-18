import Vue from 'vue'
import Vuex from 'vuex'
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: null,
        chats: [],
        stompClient: null,
    },
    getters: {
        isLoggedIn: state => state.user != null,
        isLoggedInAdmin: state => state.user != null && state.user.isAdmin,
    },
    mutations: {
        saveUser(state, user) {
            state.user = user
        },
        saveChats(state, chats) {
            state.chats = chats
        },
        removeUser(state) {
            state.user = null
        },
        removeChats(state) {
            state.chats = []
        },
    },
    actions: {
        async fetchChats(state, user_id) {
            const response = await fetch("/api/v1/matchedUsers/user/" + user_id);
            let chats = await response.json();
            for (let chat of chats) {
                const response = await fetch("/api/v1/chatMessages/chat/" + chat.id);
                chat.messages = await response.json();
            }
            state.commit("saveChats", chats);
        },
        connectWebSocketChat(state) {
            let socket = new SockJS('http://localhost:9191/api/v1/websocket');
            state.stompClient = Stomp.over(socket);
            state.stompClient.connect({},
                () => {
                    console.log("Connection succeeded");
                    state.stompClient.subscribe('/topic/public', () => { console.log("New message recived"); });
                },
                () => {
                    console.log("Connection error");
                },
            )
        }
    },
    plugins: [createPersistedState({
        storage: window.sessionStorage,
    })]
})
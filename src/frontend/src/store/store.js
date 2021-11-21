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
        socket: null,
    },
    getters: {
        isLoggedIn: state => state.user != null,
        isLoggedInAdmin: state => state.user != null && state.user.isAdmin,
        isOnline: state => state.user != null && state.stompClient != null,
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
        connectWebSocketChat(state, user_id) {
            state.socket = new SockJS('/websocket');
            state.stompClient = Stomp.over(state.socket);
            state.stompClient.connect({},
                () => {
                    console.log("Connection succeeded" + user_id);
                    state.stompClient.subscribe('/topic/public', () => { console.log("New message recived"); });
                    state.stompClient.send("/app/chat.register", {}, JSON.stringify({ id: user_id }))
                },
                () => {
                    console.log("Connection error");
                },
            );
        },
        sendMessage(state, message) {
            console.log("You try to send a message");
            console.log(message);
            console.log("Inside if");
            console.log(JSON.stringify(message));
            state.stompClient.send("/app/chat.send", {}, JSON.stringify(message));
            console.log("sended");
        },
    },
    plugins: [createPersistedState({
        storage: window.sessionStorage,
    })]
})
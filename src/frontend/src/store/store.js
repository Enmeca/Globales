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
        isOnline: state => state.user != null && state.stompClient != null,
    },
    mutations: {
        saveUser(state, user) {
            state.user = user
        },
        saveChats(state, chats) {
            state.chats = chats
        },
        saveStomClient(state, stompClient) {
            state.stompClient = stompClient
        },
        saveNotificationsMatch(state, notificationsMatch) {
            state.notificationsMatch = notificationsMatch
        },
        removeUser(state) {
            state.user = null
        },
        removeChats(state) {
            state.chats = []
        },
        removeStomClient(state) {
            state.stompClient = null
        },
        removeNotificationsMatch(state) {
            state.notificationsMatch = 0
        },
    },
    actions: {
        async fetchChats({ commit, state }) {
            const response = await fetch("/api/v1/matchedUsers/user/" + state.user.id);
            let chats = await response.json();
            for (let chat of chats) {
                const response = await fetch("/api/v1/chatMessages/chat/" + chat.id);
                chat.messages = await response.json();
            }
            commit("saveChats", chats);
        },
        connectWebSocketChat({ commit, dispatch, state }) {
            let socket = new SockJS('/websocket');
            let stompClient = Stomp.over(socket);
            stompClient.debug = () => { }; // disable debug messages
            stompClient.connect({},
                () => {
                    stompClient.subscribe('/topic/public', (message) => { dispatch("receiveMessage", JSON.parse(message.body)); });
                    stompClient.send("/app/chat.register", JSON.stringify({ userUid: { id: state.user.id } }));
                    commit("saveStomClient", stompClient);
                },
                () => {
                    console.log("Connection error");
                },
            );
        },
        sendMessage({ state }, message) {
            state.stompClient.send("/app/chat.send", JSON.stringify(message));
        },
        receiveMessage({ state }, message) {
            if (message.chatUid === null) return; // error message
            let chat = state.chats.find(chat => chat.id === message.chatUid.id);
            if (chat === undefined) return; // not for me
            chat.messages.push(message);
        },
        async updateReadMessages({ state }, chat_id) {
            let chatReaded = state.chats.find(c => c.id === chat_id);
            if (chatReaded !== undefined) {
                await fetch(`/api/v1/chatMessages/readMessages/${chat_id}/${state.user.id}`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                });
                const response = await fetch("/api/v1/chatMessages/chat/" + chat_id);
                chatReaded.messages = await response.json();
            }
        },
    },
    plugins: [createPersistedState({
        storage: window.sessionStorage,
    })]
})
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
        newMessages: 0
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
        saveNewMessages(state, newMessages) {
            state.newMessages = newMessages
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
        receiveMessage({ commit, state }, message) {
            if (message.chatUid === null) return; // error message
            let chat = state.chats.find(chat => chat.id === message.chatUid.id);
            if (chat === undefined) return; // not for me
            chat.messages.push(message);
            if (message.userUid.id === state.user.id) return; // I send message, dont notify
            commit("saveNewMessages", state.newMessages + 1); // Create new notification.
        },
    },
    plugins: [createPersistedState({
        storage: window.sessionStorage,
    })]
})
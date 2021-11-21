<template>
  <div id="chats-page">
    <center>
      <b-card class="bg-opacity-black">
        <b-row class="chats">
          <b-col sm="12" md="4" lg="4" class="p-0" v-show="showChats">
            <b-input-group>
              <b-input-group-prepend is-text>
                <b-icon-search />
              </b-input-group-prepend>
              <b-form-input v-model="searchField"> </b-form-input>
            </b-input-group>
            <transition-group name="bounceLeft">
              <ItemChat
                v-for="chat in filterChats"
                :key="chat.id"
                :handleClick="changeChat"
                :chatData="chat"
                :width="window.width"
                class="p-0 m-1"
              />
            </transition-group>
          </b-col>
          <b-col
            sm="12"
            md="8"
            lg="8"
            class="col-current-chats p-0"
            v-show="showCurrentChat"
          >
            <transition
              enter-active-class="animated fadeInRight"
              leave-active-class="animated fadeOutRight"
            >
              <b-container
                v-if="actualChat != -1"
                style="height: 100%; animation-duration: 0.5s"
                fluid
              >
                <b-container class="bg-info-chat bg-secondary text-white" fluid>
                  <b-row class="text-left">
                    <b-col cols="12" align-self="center">
                      <b-icon-arrow-left
                        v-if="!showChats"
                        class="back-icon mt-2 ml-2 mr-2"
                        @click="actualChat = -1"
                        scale="2"
                      >
                      </b-icon-arrow-left>

                      <b-avatar
                        variant="dark"
                        :text="userAbbreviatedName"
                        :src="
                          'http://localhost:9191/api/v1/userPhoto/photo/' +
                          chats[actualChat].user.id
                        "
                        :size="showChats ? '4em' : '3em'"
                        class="m-2"
                      ></b-avatar>
                      <strong>
                        <i>
                          {{ chats[actualChat].user.name }}
                          {{ chats[actualChat].user.lastName1 }}
                          {{ chats[actualChat].user.lastName2 }}
                        </i>
                      </strong>
                    </b-col>
                  </b-row>
                </b-container>

                <b-card class="bg-chat" style="height: 75%">
                  <b-container fluid>
                    <ChatMessages
                      v-for="message in chats[actualChat].messages"
                      :key="message.id"
                      :data="message"
                      :mine="message.userUid.id === user.id"
                    />
                  </b-container>
                </b-card>
                <b-container class="bg-info-send bg-secondary text-white" fluid>
                  <b-input-group class="p-1">
                    <b-form-input
                      v-model="contentMessage"
                      type="text"
                    ></b-form-input>
                    <b-input-group-append>
                      <b-button variant="primary" @click="sendMessage">
                        <b-icon-cursor-fill />
                      </b-button>
                    </b-input-group-append>
                  </b-input-group>
                </b-container>
              </b-container>
            </transition>
            <transition enter-active-class="animated fadeInRight">
              <b-container v-if="actualChat == -1" style="height: 100%" fluid>
                <b-card style="height: 100%"> A </b-card>
              </b-container>
            </transition>
          </b-col>
        </b-row>
      </b-card>
    </center>
  </div>
</template>

<script>
import ItemChat from "./ItemChat.vue";
import ChatMessages from "./ChatMessages.vue";

export default {
  components: {
    ChatMessages,
    ItemChat,
  },
  data() {
    return {
      window: {
        width: 0,
        height: 0,
      },
      searchField: "",
      chats: this.$store.state.chats,
      actualChat: -1,
      contentMessage: "",
    };
  },
  created() {
    window.addEventListener("resize", this.handleResize);
    this.handleResize();
  },
  destroyed() {
    window.removeEventListener("resize", this.handleResize);
  },
  methods: {
    handleResize() {
      this.window.width = window.innerWidth;
      this.window.height = window.innerHeight;
    },
    changeChat(id) {
      this.actualChat = -1;
      setTimeout(() => {
        this.actualChat = this.chats.findIndex((chat) => chat.id == id);
      }, 100);
    },
    sendMessage() {
      if (this.actualChat !== -1) {
        this.$store.dispatch("sendMessage", {
          id: 0,
          chatUid: { id: this.chats[this.actualChat].id },
          userUid: { id: this.user.id },
          message: this.contentMessage,
          dateSent: null,
          wasRead: 0,
        });
        this.contentMessage = "";
      }
    },
  },
  computed: {
    filterChats() {
      const criteria = this.searchField.trim().toLowerCase();
      if (criteria) {
        return this.chats.filter(
          (chat) =>
            chat.user.name.toLowerCase().indexOf(criteria) > -1 ||
            chat.user.lastName1.toLowerCase().indexOf(criteria) > -1 ||
            chat.user.lastName2.toLowerCase().indexOf(criteria) > -1
        );
      }
      return this.chats;
    },
    showCurrentChat() {
      return this.window.width >= 770 || this.actualChat != -1;
    },
    showChats() {
      return this.window.width >= 770 || this.actualChat == -1;
    },
    userAbbreviatedName() {
      return (
        this.chats[this.actualChat].user.name[0] +
        this.chats[this.actualChat].user.lastName1[0]
      );
    },
    user() {
      return this.$store.state.user;
    },
  },
};
</script>

<style scoped>
#chats-page,
center {
  height: 70%;
  margin-inline: 1vw;
  margin-block: 1vw;
}

.col-chats {
  height: 80vh;
  overflow-y: auto;
  overflow-x: hidden;
}

.col-current-chats {
  height: 80vh;
}
.card-of-chats {
  border-radius: 50px;
}
.bg-opacity-black {
  background-color: rgba(0, 0, 0, 0.6);
  min-height: 86vh !important;
}
.bg-info-chat {
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}
.bg-info-send {
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}

.bg-chat {
  background: url("../../assets/background-chats.jpg");
  border-radius: 0px;
  overflow-y: auto;
  overflow-x: hidden;
}

.back-icon:hover {
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  transform: scale(1.5);
}
</style>
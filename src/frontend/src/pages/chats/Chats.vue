<template>
  <div id="chats-page">
    <center>
      <b-card class="bg-transparent">
        <b-row class="chats">
          <b-col sm="12" md="4" lg="4" class="p-1" v-show="showChats">
            <b-input-group>
              <b-input-group-prepend is-text>
                <b-icon-search />
              </b-input-group-prepend>
              <b-form-input v-model="searchField"> </b-form-input>
            </b-input-group>
            <ItemChat
              v-for="chat in chats"
              :key="chat.id"
              class="p-0 mt-1"
              :userChat="temp.userChat"
              :lastMessage="temp.lastMessage"
              :cantNotReaded="temp.cantNotReaded"
              :width="window.width"
            />
          </b-col>
          <b-col sm="12" md="8" lg="8" class="p-1" v-show="showCurrentChat">
            <b-card style="height: 100%">
              <b-card-body v-if="actualChat == null">
                Chat actual
                {{ window.width }}
                X
                {{ window.height }}
              </b-card-body>
              <b-card-body v-else> Welcome to page chat </b-card-body>
            </b-card>
          </b-col>
        </b-row>
      </b-card>
    </center>
  </div>
</template>

<script>
import ItemChat from "./ItemChat.vue";

export default {
  components: {
    ItemChat,
  },
  data() {
    return {
      window: {
        width: 0,
        height: 0,
      },
      searchField: "",
      chats: [
        { user: "aja", id: 1 },
        { user: "aja", id: 2 },
        { user: "aja", id: 3 },
        { user: "aja", id: 4 },
        { user: "aja", id: 5 },
        { user: "aja", id: 6 },
        { user: "aja", id: 7 },
      ],
      actualChat: null, //{ user: "aja", id: 1 },
      temp: {
        userChat: {
          name: "Luis David",
          lastName1: "Villalobos",
          lastName2: "Gonzalez",
        },
        lastMessage: {
          message: "example of lastMessage large",
          date: "10/22/2021 07:12 pm",
          readed: true,
        },
        cantNotReaded: 999,
      },
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
  },
  computed: {
    showCurrentChat() {
      return this.window.width >= 768 || this.actualChat != null;
    },
    showChats() {
      return this.window.width >= 768 || this.actualChat == null;
    },
  },
};
</script>

<style scoped>
#chats-page,
center {
  margin: 0.5vw;
}
</style>
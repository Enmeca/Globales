<template>
  <div id="chats-page">
    <center>
      <b-card class="card-of-chats bg-opacity-black p-2">
        <b-row class="chats" style="height: 100%">
          <b-col sm="12" md="4" lg="3" class="col-chats p-1" v-show="showChats">
            <b-input-group>
              <b-input-group-prepend is-text>
                <b-icon-search />
              </b-input-group-prepend>
              <b-form-input v-model="searchField"> </b-form-input>
            </b-input-group>
            <transition-group name="bounceLeft" tag="ul" class="pl-0">
              <ItemChat
                v-for="chat in filterChats"
                :key="chat.id"
                class="p-0 mt-1"
                :userChat="chat.userChat"
                :lastMessage="chat.lastMessage"
                :cantNotReaded="chat.cantNotReaded"
                :width="window.width"
              />
            </transition-group>
            <b-card v-if="filterChats.length == 0" class="mt-3">
              <p>
                Ningún chat coincide con el criterio de busqueda
                <b-icon-emoji-frown />
              </p>
            </b-card>
          </b-col>
          <b-col
            sm="12"
            md="8"
            lg="9"
            class="col-current-chats p-1"
            v-show="showCurrentChat"
          >
            <b-card style="height: 100%">
              <b-card-body v-if="actualChat">
                Welcome to page chat
              </b-card-body>
              <b-card-body v-else>
                Chat actual
                {{ window.width }}
                X
                {{ window.height }}
              </b-card-body>
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
        {
          id: "1",
          userChat: {
            id: "117540697",
            name: "John",
            lastName1: "Dereh",
            lastName2: "dereh",
          },
          lastMessage: {
            message: "example of lastMessage large",
            date: "10/22/2021 07:12 pm",
            readed: true,
          },
          cantNotReaded: 999,
        },
        {
          id: "2",
          userChat: {
            id: "615283905",
            name: "Y",
            lastName1: "Dereh",
            lastName2: "dereh",
          },
          lastMessage: {
            message: "example of lastMessage large",
            date: "10/29/2021 09:10 am",
            readed: true,
          },
          cantNotReaded: 2,
        },
        {
          id: "3",
          userChat: {
            id: "615283905",
            name: "G",
            lastName1: "Dereh",
            lastName2: "dereh",
          },
          lastMessage: {
            message: "example of lastMessage large",
            date: "10/29/2021 09:10 am",
            readed: true,
          },
          cantNotReaded: 2,
        },
        {
          id: "4",
          userChat: {
            id: "615283905",
            name: "V",
            lastName1: "Dereh",
            lastName2: "dereh",
          },
          lastMessage: {
            message: "example of lastMessage large",
            date: "10/29/2021 09:10 am",
            readed: true,
          },
          cantNotReaded: 2,
        },
        {
          id: "5",
          userChat: {
            id: "615283905",
            name: "X",
            lastName1: "Dereh",
            lastName2: "dereh",
          },
          lastMessage: {
            message: "example of lastMessage large",
            date: "10/29/2021 09:10 am",
            readed: true,
          },
          cantNotReaded: 2,
        },
        {
          id: "6",
          userChat: {
            id: "615283905",
            name: "Z",
            lastName1: "Dereh",
            lastName2: "dereh",
          },
          lastMessage: {
            message: "example of lastMessage large",
            date: "10/29/2021 09:10 am",
            readed: true,
          },
          cantNotReaded: 2,
        },
      ],
      actualChat: null, //{ user: "aja", id: 1 },
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
    filterChats() {
      const criteria = this.searchField.trim().toLowerCase();
      if (criteria) {
        return this.chats.filter(
          (chat) =>
            chat.userChat.name.toLowerCase().indexOf(criteria) > -1 ||
            chat.userChat.lastName1.toLowerCase().indexOf(criteria) > -1 ||
            chat.userChat.lastName2.toLowerCase().indexOf(criteria) > -1
        );
      }
      return this.chats;
    },
  },
};
</script>

<style scoped>
#chats-page,
center {
  margin: 0.5vw;
}

.card-chats {
  height: 80vh;
  overflow: hidden;
}

.col-chats {
  height: 80vh;
  overflow-y: auto;
  overflow-x: hidden;
}

.col-current-chats {
  height: 80vh;
  overflow-y: auto;
  overflow-x: hidden;
}

.bg-opacity-black {
  background-color: rgba(0, 0, 0, 0.6);
}
</style>
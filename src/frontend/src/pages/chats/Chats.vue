<template>
  <div id="chats-page">
    <center>
      <b-card class="main-card">
        <b-row class="chats">
          <b-col sm="12" md="4" lg="4" class="p-1" v-show="showChats">
            <b-input-group>
              <b-input-group-prepend is-text>
                <b-icon-search />
              </b-input-group-prepend>
              <b-form-input v-model="searchField"> </b-form-input>
            </b-input-group>
            <b-card v-for="chat in chats" :key="chat.id" class="p-0 mt-1">
              <b-row>
                <b-col cols="4">
                  <b-avatar
                    v-if="true"
                    variant="info"
                    text="PS"
                    size="lg"
                  ></b-avatar>
                  <b-avatar
                    v-else
                    variant="dark"
                    icon="sunglasses"
                    size="lg"
                  ></b-avatar>
                </b-col>
                <b-col cols="8">
                  <b-row>
                    <b-col> persona </b-col>
                    <b-col> Fecha </b-col>
                  </b-row>
                  <b-row>
                    <b-col> Mensaje </b-col>
                    <b-col> cant </b-col>
                  </b-row>
                </b-col>
              </b-row>
            </b-card>
          </b-col>
          <b-col sm="12" md="8" lg="8" class="p-1" v-show="showCurrentChat">
            <b-card style="height: 100%">
              <b-card-body v-if="actualChat != null">
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
export default {
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
      return this.window.width >= 770 || this.actualChat != null;
    },
    showChats() {
      return this.window.width >= 770 || this.actualChat == null;
    },
  },
};
</script>

<style scoped>
#chats-page,
center {
  height: 70%;
  margin-inline: 1vw;
  margin-block: 2vw;
}
.main-card {
  background-color: rgba(0, 0, 0, 0.6);
}
</style>
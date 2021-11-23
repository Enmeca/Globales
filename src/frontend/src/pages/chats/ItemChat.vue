<template>
  <div class="chat bg-light p-2 card-rounded" @click="handleClick(chatData.id)">
    <b-row class="text-muted">
      <b-col cols="5" align-self="center">
        <b-avatar
          variant="secondary"
          :text="userAbbreviatedName"
          :src="'http://localhost:9191/api/v1/userPhoto/photo/' + getIdUserChat"
          size="6em"
        ></b-avatar>
      </b-col>
      <b-col cols="7">
        <b-row v-if="this.chatData.messages.length > 0" align-h="between">
          <b-col cols="12" class="text-right">
            {{ getDateLastMessage }}
          </b-col>
          <b-col cols="12" class="text-left"> {{ getNameUser }} </b-col>
          <b-col cols="12" class="text-left">
            <b-icon-check2
              variant="info"
              v-if="
                !this.chatData.messages[this.chatData.messages.length - 1]
                  .wasRead
              "
            />
            <b-icon-check2-all variant="info" v-else />
            {{ getLastMessage }}
          </b-col>
          <b-col cols="12" class="text-right">
            <b-badge
              v-if="cantNotReaded > 0"
              variant="secondary"
              class="cant-not-read"
            >
              {{ cantNotReaded }}
            </b-badge>
          </b-col>
        </b-row>
        <b-row v-else align-h="between">
          <b-col cols="12" class="text-left"> {{ getNameUser }} </b-col>
          <b-col cols="12" class="text-left mt-3">
            Inicia un chat con tu nuevo match
            <b-icon-emoji-laughing-fill />
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  props: {
    chatData: {
      type: Object,
      required: true,
    },
    width: {
      type: Number,
      required: true,
    },
    handleClick: {
      type: Function,
      required: true,
    },
  },
  computed: {
    userAbbreviatedName() {
      if (this.$store.state.user.id !== this.chatData.user.id) {
        return this.chatData.user.name[0] + this.chatData.user.lastName1[0];
      } else {
        return (
          this.chatData.matchedUser.name[0] +
          this.chatData.matchedUser.lastName1[0]
        );
      }
    },
    getIdUserChat() {
      if (this.$store.state.user.id !== this.chatData.user.id) {
        return this.chatData.user.id;
      } else {
        return this.chatData.matchedUser.id;
      }
    },
    getDateLastMessage() {
      let today = new Date();
      let date = new Date(
        this.chatData.messages[this.chatData.messages.length - 1].dateSent
      );
      if (
        today.getFullYear() == date.getFullYear() &&
        today.getMonth() == date.getMonth() &&
        today.getDate() == date.getDate()
      ) {
        return date.toLocaleString().split(",")[1];
      }
      return date.toLocaleString().split(",")[0];
    },
    getNameUser() {
      let user =
        this.$store.state.user.id !== this.chatData.user.id
          ? this.chatData.user
          : this.chatData.matchedUser;
      let fullName =
        user.name.split(" ")[0] +
        " " +
        user.lastName1 +
        " " +
        user.lastName2[0];

      if (this.width > 800) {
        return fullName;
      }
      return fullName.slice(0, 15) + "...";
    },
    getLastMessage() {
      if (
        this.chatData.messages[this.chatData.messages.length - 1].message
          .length > 20
      ) {
        return (
          this.chatData.messages[
            this.chatData.messages.length - 1
          ].message.slice(0, 20) + "..."
        );
      }
      return this.chatData.messages[this.chatData.messages.length - 1].message;
    },
    cantNotReaded() {
      return this.chatData.messages.filter(
        (message) =>
          message.userUid.id !== this.$store.state.user.id && !message.wasRead
      ).length;
    },
  },
};
</script>

<style scoped>
.cant-not-read {
  border-radius: 100px !important;
  font-size: 15px;
}
.card-rounded {
  border-radius: 10px !important;
}
.chat:hover {
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  transform: scale(1.04);
}
</style>
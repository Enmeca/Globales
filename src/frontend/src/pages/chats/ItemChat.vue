<template>
  <div class="bg-light p-2 card-rounded">
    <b-row class="text-muted">
      <b-col cols="5" align-self="center">
        <b-avatar
          variant="secondary"
          :text="userAbbreviatedName"
          :src="'http://localhost:9191/api/v1/userPhoto/photo/' + userChat.id"
          size="6em"
        ></b-avatar>
      </b-col>
      <b-col cols="7">
        <b-row align-h="between">
          <b-col cols="12" class="text-right">
            {{ getDateLastMessage }}
          </b-col>
          <b-col cols="12" class="text-left"> {{ getNameUser }} </b-col>
          <b-col cols="12" class="text-left">
            <b-icon-check2 variant="info" v-if="!lastMessage.readed" />
            <b-icon-check2-all variant="info" v-else />
            {{ getLastMessage }}
          </b-col>
          <b-col cols="12" class="text-right">
            <b-badge variant="secondary" class="cant-not-read">
              {{ getCantNotReaded }}
            </b-badge>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  props: {
    userChat: {
      type: Object,
      required: true,
    },
    lastMessage: {
      type: Object,
      required: true,
    },
    cantNotReaded: {
      type: Number,
      required: true,
    },
    width: {
      type: Number,
      required: true,
    },
  },
  computed: {
    userAbbreviatedName() {
      return this.userChat.name[0] + this.userChat.lastName1[0];
    },
    getDateLastMessage() {
      //let today = new Date();
      let date = new Date(this.lastMessage.date);
      //if(today.getFullYear() == date.getFullYear()){

      //}
      return date.toLocaleString().split(",")[0];
    },
    getCantNotReaded() {
      if (this.cantNotReaded > 0) {
        return String(this.cantNotReaded);
      }
      return " ";
    },
    getNameUser() {
      let fullName =
        this.userChat.name.split(" ")[0] +
        " " +
        this.userChat.lastName1 +
        " " +
        this.userChat.lastName2[0];
      if (this.width > 800) {
        return fullName;
      }
      return fullName.slice(0, 15) + "...";
    },
    getLastMessage() {
      if (this.lastMessage.message.length > 20) {
        return this.lastMessage.message.slice(0, 20) + "...";
      }
      return this.lastMessage.message;
    },
  },
};
</script>

<style scoped>
.cant-not-read {
  border-radius: 100px !important;
}
.card-rounded {
  border-radius: 10px !important;
}
</style>
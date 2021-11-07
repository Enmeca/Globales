<template>
  <div class="chat bg-light p-2 card-rounded" @click="handleClick(chatData.id)">
    <b-row class="text-muted">
      <b-col cols="5" align-self="center">
        <b-avatar
          variant="secondary"
          :text="userAbbreviatedName"
          :src="
            'http://localhost:9191/api/v1/userPhoto/photo/' +
            this.chatData.user.id
          "
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
            <b-icon-check2
              variant="info"
              v-if="!this.chatData.messages[0].readed"
            />
            <b-icon-check2-all variant="info" v-else />
            {{ getLastMessage }}
          </b-col>
          <b-col cols="12" class="text-right">
            <b-badge
              v-if="this.chatData.cantNotReaded > 0"
              variant="secondary"
              class="cant-not-read"
            >
              {{ this.chatData.cantNotReaded }}
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
      return this.chatData.user.name[0] + this.chatData.user.lastName1[0];
    },
    getDateLastMessage() {
      //let today = new Date();
      let date = new Date(this.chatData.messages[0].date);
      //if(today.getFullYear() == date.getFullYear()){

      //}
      return date.toLocaleString().split(",")[0];
    },
    getNameUser() {
      let fullName =
        this.chatData.user.name.split(" ")[0] +
        " " +
        this.chatData.user.lastName1 +
        " " +
        this.chatData.user.lastName2[0];
      if (this.width > 800) {
        return fullName;
      }
      return fullName.slice(0, 15) + "...";
    },
    getLastMessage() {
      if (this.chatData.messages[0].message.length > 20) {
        return this.chatData.messages[0].message.slice(0, 20) + "...";
      }
      return this.chatData.messages[0].message;
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
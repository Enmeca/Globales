<template>
  <b-row class="mb-2">
    <b-col
      sm="7"
      md="5"
      lg="5"
      :class="'message ' + (mine ? 'ml-auto bg-info' : 'mr-auto bg-secondary')"
    >
      <b-row align-h="end">
        <b-col cols="12" :class="mine ? 'text-right' : 'text-left'">
          {{ data.message }}
          asdasdasdasdasdasdasdasdasdasdasasd das d"
        </b-col>
        <b-col cols="5">
          {{ getDateMessage }}
          <b-icon-check2 variant="light" v-if="data.readed" />
          <b-icon-check2-all variant="light" v-else />
        </b-col>
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
export default {
  props: {
    /*  -UID: Int / PK
-Chat: Int / FK a MatchedUsers
-User: String / FK
-Message: String
- Leido: Boolean
-TimeSent: Date (Time)*/

    data: {
      type: Object,
      required: true,
    },
    mine: {
      type: Boolean,
      required: true,
    },
  },
  computed: {
    getDateMessage() {
      //let today = new Date();
      let date = new Date(this.data.date);
      //if(today.getFullYear() == date.getFullYear()){

      //}
      return date.toLocaleString().split(",")[0];
    },
  },
};
</script>

<style scoped>
.message {
  padding: 10px;
  border-radius: 15px;
  color: white;
}
</style>
<template>
  <b-row class="mb-2">
    <b-col
      sm="7"
      md="5"
      lg="4"
      :class="
        'message ' + (mine ? 'ml-auto bg-primary' : 'mr-auto bg-secondary')
      "
    >
      <b-row align-h="end">
        <b-col cols="12" :class="mine ? 'text-right' : 'text-left'">
          {{ data.message }}
        </b-col>
        <b-col cols="5" class="text-right">
          {{ getDateMessage }}
          <b-icon-check2 variant="light" v-if="!data.wasRead" />
          <b-icon-check2-all variant="light" v-else />
        </b-col>
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
export default {
  props: {
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
      let today = new Date();
      let date = new Date(this.data.dateSent);
      if (
        today.getFullYear() == date.getFullYear() &&
        today.getMonth() == date.getMonth() &&
        today.getDate() == date.getDate()
      ) {
        return date.toLocaleString().split(",")[1];
      }
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
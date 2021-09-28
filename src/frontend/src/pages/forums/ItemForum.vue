<template>
  <b-card class="forum">
    <strong>
      {{ data.titulo }}
    </strong>
  </b-card>
</template>

<script>
export default {
  props: {
    data: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
.forum:hover {
  background-color: rgb(235, 235, 235);
}
</style>
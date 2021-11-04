<template>
  <b-card class="main-card">
    <b-card-body class="text-left p-0 m-0">
      <h4 class="text-center">Reporte #{{ data.comment.id }}</h4>
      <p><strong>Comentario:</strong> {{ this.data.comment.description }}</p>
      <p>
        <strong>Autor: </strong>{{ getFullAutorNameUser }} -
        {{ this.data.comment.authorId.id }}
      </p>
      <p>
        <strong>Enviado por: </strong>{{ getFullSendNameUser }} -
        {{ this.data.user.id }}
      </p>
      <p><strong>Motivo: </strong>{{ this.data.motive }}</p>
      <b-row align-h="center">
        <b-button
          :href="`#/forums/${this.data.comment.forumId.id}`"
          variant="primary"
          class="m-1"
        >
          <b-icon-eye-fill />
        </b-button>

        <b-button @click="omitReport" variant="danger" class="m-1">
          <b-icon-trash-fill /> reporte
        </b-button>
        <b-button @click="deleteComment" variant="secondary" class="m-1"
          ><b-icon-trash-fill /> reporte y comentario</b-button
        >
      </b-row>
    </b-card-body>
  </b-card>
</template>
<script>
export default {
  props: {
    data: {
      type: Object,
      required: true,
    },
    updateReports: {
      type: Function,
      required: true,
    },
  },
  methods: {
    async omitReport() {
      const response = await fetch(
        `/api/v1/commentReports/delByComment/${this.data.comment.id}`,
        {
          method: "DELETE",
          headers: { "Content-Type": "application/json" },
        }
      );
      if (response.status === 200) this.updateReports();
    },
    async deleteComment() {
      const response = await fetch(
        `/api/v1/commentReports/delByComment/${this.data.comment.id}`,
        {
          method: "DELETE",
          headers: { "Content-Type": "application/json" },
        }
      );
      const response2 = await fetch("/api/v1/forumComments", {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.data.comment),
      });

      if (response.status === 200 && response2.status === 200)
        this.updateReports();
    },
  },
  computed: {
    getFullSendNameUser() {
      return (
        this.data.user.name +
        " " +
        this.data.user.lastName1 +
        " " +
        this.data.user.lastName2
      );
    },
    getFullAutorNameUser() {
      return (
        this.data.comment.authorId.name +
        " " +
        this.data.comment.authorId.lastName1 +
        " " +
        this.data.comment.authorId.lastName2
      );
    },
  },
};
</script>

<style scoped>
.main-card {
  background-color: rgba(255, 255, 255, 0.863) !important;
  color: rgb(83, 76, 76);
}
p {
  font-size: 18px;
}
</style>
<template>
    <slide>
    <b-card class="main-card"
        :title="`Reporte #${this.data.comment.id} de ${this.data.user.id}`"
        style="max-width: 20rem;">
        <b-card-text>
            Motivo: {{this.data.motive}}
        </b-card-text>
        <b-card-text>
            Comentario: {{this.data.comment.description}}
        </b-card-text>
        <b-button :href="`/forums/id/${this.data.comment.forumId.id}`" variant="primary">Verificar</b-button>
        <b-button @click="omitReport()" variant="success">Omitir</b-button>
        <b-button @click="deleteComment()" variant="danger">Eliminar</b-button>
    </b-card>
    </slide>
</template>
<script>
import {Slide} from 'vue-carousel';
export default {
  props: {
    data: {
      type: Object,
      required: true,
    },
  },
  methods: {
    async omitReport(){
      alert ("xd")
        const resp = await fetch(`/api/v1/commentReports/delByComment/${this.data.comment.id}`, {
              method: "DELETE",
              headers: { "Content-Type": "application/json" },
            });
     },
     async deleteComment(){
       alert ("xd")
        const resp = await fetch(`/api/v1/commentReports/del/${this.data.comment.id}/${this.data.comment.userid.id}`, {
                      method: "DELETE",
                      headers: { "Content-Type": "application/json" },
                    });
            }
  },components: {Slide}
};
</script>

<style scoped>
.main-card {
  background-color: rgba(255, 255, 255, 0.76);
  color: rgba(0, 0, 0, 0.616);
  font-size: relative;
  font-size-adjust: inherit;
}
</style>
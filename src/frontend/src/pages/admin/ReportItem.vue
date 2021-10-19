<template>
  <slide>
    <b-card 
      class="main-card"
      :title="`Reporte #${this.data.comment.id} de ${this.data.user.id}`"
      style="max-width: 20rem"
    >
      <b-card-text> Motivo: {{ this.data.motive }} </b-card-text>
      <b-card-text>
        Comentario: {{ this.data.comment.description }}
      </b-card-text>
      <b-button
        :href="`#/forums/${this.data.comment.forumId.id}`"
        variant="primary"
        >Verificar</b-button>
      
      <b-button @click="omitReport()" variant="success">Cerar  Caso</b-button> 
      <br/>
      <b-button @click="deleteComment()" variant="danger">Cerrar Casos equivalentes</b-button>
    </b-card>
  </slide>
</template>
<script>
import { Slide } from "vue-carousel";
export default {
  props: {
    data: {
      type: Object,
      required: true,
    },updateReports:{
      type: Function,
      required: true
    }
  },
  methods: {
    async omitReport() {
      const response= await fetch(`/api/v1/commentReports/delByComment/${this.data.comment.id}`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
      });
      if(response.status===200)
        this.updateReports()
    },
    async deleteComment() {
      const response= await fetch(
        `/api/v1/commentReports/del/${this.data.comment.id}/${this.data.user.id}`,
        {
          method: "DELETE",
          headers: { "Content-Type": "application/json" },
        }
      );
      if(response.status===200)
        this.updateReports()
    },
  },
  components: { Slide },
};
</script>

<style scoped>
.main-card {
  background-color: rgba(255, 255, 255, 0.876);
  color: rgba(0, 0, 0, 0.616);
  font-size: relative;
  font-size-adjust: inherit;
}
</style>
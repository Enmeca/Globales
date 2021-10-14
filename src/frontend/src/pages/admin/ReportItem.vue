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
        <b-button href="#" variant="primary">Verificar</b-button>
        <b-button href="#" variant="warning">Eliminar</b-button>
        <b-button href="#" variant="danger">Sancionar</b-button>
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
  computed: {
    
  },components: {Slide}
};
</script>

<style scoped>
.main-card {
  background-color: rgba(255, 255, 255, 0.76);
  color: rgba(0, 0, 0, 0.616);
  max-height: 500px;
  max-width: 100px;
  font-size-adjust: inherit;
}
</style>
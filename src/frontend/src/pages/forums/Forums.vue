<template>
  <center>
    <div class="container-forum">
      <b-card>
        <b-input-group class="mt-1">
          <b-form-input
            v-model="newForum.titulo"
            placeholder="¿Titulo del foro o pregunta?"
          >
          </b-form-input>
        </b-input-group>
        <b-input-group class="mt-1">
          <b-form-textarea
            id="textarea"
            v-model="newForum.descripcion"
            placeholder="Ingresa información adicional"
            rows="3"
            max-rows="3"
          ></b-form-textarea>
        </b-input-group>
        <b-row class="mt-2 justify-content-around">
          <b-form-checkbox
            v-b-tooltip.hover
            title="Anónimo"
            switch
            size="lg"
            class="mt-1"
          >
            <b-icon-sunglasses scale="2" class="ml-2" />
          </b-form-checkbox>
          <b-button variant="info"> Publicar</b-button>
        </b-row>
      </b-card>
      <ItemForum
        v-for="forum in forums"
        :key="forum.id"
        :data="forum"
        class="mt-2"
      />
    </div>
  </center>
</template>

<script>
// Import components
import ItemForum from "./ItemForum.vue";

export default {
  data() {
    return {
      newForum: {
        titulo: "",
        descripcion: "",
        fecha: "28/09/2021",
        anonimo: false,
      },
      forums: [
        {
          id: 1,
          autor: "Juan Salas",
          titulo: "¿Pasos iniciales para aprender c++ rapido?",
          descripcion: "Estoy llevando el curso de fundamentos...",
          fecha: "28/09/2021",
          anonimo: false,
        },
        {
          id: 2,
          autor: "Daniela Delgado",
          titulo: "¿Como matricular en verano y no morir?",
          descripcion: "No pase el curso de programacion 1...",
          fecha: "25/09/2021",
          anonimo: true,
        },
      ],
    };
  },
  // Specify which components you are using
  components: {
    ItemForum,
  },
};
</script>

<style scoped>
center {
  background-color: rgba(0, 0, 0, 0.5);
}
.container-forum {
  margin-inline: 20vw;
}
p {
  font-size: 20px;
}
</style>
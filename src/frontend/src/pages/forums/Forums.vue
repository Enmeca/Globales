<template>
  <center>
    <div class="container-forum">
      <b-card class="create-forum">
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
            v-model="newForum.anonimo"
          >
            <b-icon-sunglasses v-if="newForum.anonimo" scale="2" class="ml-2" />
            <b-icon-eyeglasses v-else scale="2" class="ml-2" />
          </b-form-checkbox>
          <b-button variant="info" @click="publishForum" :disabled="!validForm">
            Publicar</b-button
          >
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
  components: {
    ItemForum,
  },
  data() {
    return {
      newForum: {
        titulo: "",
        descripcion: "",
        fecha: "28.09.2021",
        anonimo: false,
        autor: this.$store.state.user,
      },
      forums: [
        {
          id: 1,
          autor: { name: "Juan", lastName1: "Salas" },
          titulo: "¿Pasos iniciales para aprender c++ rapido?",
          descripcion:
            "Estoy llevando el curso de fundamentos con al profesor X y no logro comprender la materia, pronto será el examen y no se que hacer",
          fecha: "28.9.2021 03:46pm",
          anonimo: false,
          cantComentarios: 4,
        },
        {
          id: 2,
          autor: { name: "Daniela", lastName1: "Delgado" },
          titulo: "¿Como matricular en verano y no morir?",
          descripcion:
            "No pase el curso de programacion 1 el ciclo anterior, por lo que no se si es buena idea llevarlo en verano",
          fecha: "25.9.2021 09:21am",
          anonimo: true,
          cantComentarios: 2,
        },
      ],
    };
  },
  methods: {
    publishForum() {
      this.forums.unshift(this.newForum);
      this.newForum = {
        titulo: "",
        descripcion: "",
        fecha: "28.09.2021 08:26pm",
        anonimo: false,
        autor: this.$store.state.user,
      };
    },
  },
  computed: {
    validForm() {
      return this.newForum.titulo != "";
    },
  },
};
</script>

<style scoped>
center {
  background-color: rgba(0, 0, 0, 0.4);
  min-height: 100vh;
}
.container-forum {
  margin-inline: 15vw;
  padding-block: 5vh;
}
p {
  font-size: 20px;
}
.create-forum {
  border-radius: 30px;
}
</style>
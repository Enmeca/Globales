<template>
  <center>
    <b-row>
      <b-col sm="12" md="4" lg="4" class="mt-3">
        <b-button variant="secondary" @click="backHome">
          <b-icon-house-fill />
          Regresar
        </b-button>
      </b-col>
      <b-col sm="12" md="4" lg="4" class="mt-3">
        <b-input-group>
          <b-input-group-prepend is-text>
            <b-icon-search />
          </b-input-group-prepend>
          <b-form-input v-model.lazy="searchField"> </b-form-input>
        </b-input-group>
      </b-col>
      <b-col sm="12" md="4" lg="4" class="mt-3" v-if="!modeCreateForum">
        <b-button variant="info" @click="modeCreateForum = true">
          Crear foro
          <b-icon-pencil-fill />
        </b-button>
      </b-col>
    </b-row>
    <transition name="flipX">
      <b-card
        class="create-forum mt-3"
        v-if="modeCreateForum"
        style="animation-duration: 0.8s"
      >
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
          <b-icon-trash-fill
            class="mt-2 cancel-icon"
            variant="danger"
            scale="1.6"
            @click="cancelCreateForum"
          />
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
          <b-button
            variant="info"
            @click="publishForum"
            :disabled="!validForm"
            pill
          >
            Publicar</b-button
          >
        </b-row>
      </b-card>
    </transition>
    <transition-group name="fade">
      <ItemForum
        v-for="forum in filterForums"
        :key="forum.id"
        :data="forum"
        class="mt-3"
      />
    </transition-group>
    <b-card v-if="filterForums.length == 0" class="mt-3">
      <p>
        Ningun foro coincide con la busqueda realizada
        <b-icon-emoji-frown />
      </p>
    </b-card>
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
      searchField: "",
      anyoneFound: false,
      modeCreateForum: false,
      newForum: {
        id: 0,
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
      this.modeCreateForum = false;
      this.forums.unshift(this.newForum);
      this.newForum = {
        id: 0,
        titulo: "",
        descripcion: "",
        fecha: "28.09.2021 08:26pm",
        anonimo: false,
        autor: this.$store.state.user,
      };
    },
    cancelCreateForum() {
      this.modeCreateForum = false;
      this.newForum = {
        id: 0,
        titulo: "",
        descripcion: "",
        fecha: "28.09.2021 08:26pm",
        anonimo: false,
        autor: this.$store.state.user,
      };
    },
    backHome() {
      this.$router.push({ path: "/home" });
    },
  },
  computed: {
    validForm() {
      return this.newForum.titulo != "";
    },
    filterForums() {
      const criteria = this.searchField.trim().toLowerCase();
      if (criteria) {
        return this.forums.filter(
          (forum) => forum.titulo.toLowerCase().indexOf(criteria) > -1
        );
      }
      return this.forums;
    },
  },
};
</script>
<style scoped>
p {
  font-size: 20px;
}
.create-forum {
  border-radius: 10px;
}
.cancel-icon:hover {
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  transform: scale(1.5);
}
</style>
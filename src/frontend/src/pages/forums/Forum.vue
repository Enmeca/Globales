<template>
  <div id="forum-page">
    <center class="main-card">
      <div class="container-forum">
        <b-row class="mb-2" align-h="between">
          <b-col cols="4" class="text-left">
            <b-button variant="secondary" @click="backForums">
              Regresar
              <b-icon-signpost-split-fill />
            </b-button>
          </b-col>
        </b-row>
        <b-card class="forum">
          <b-row>
            <b-col sm="12" md="4" lg="2" align-self="center">
              <b-row>
                <b-col>
                  <b-avatar
                    v-if="!forum.anonimo"
                    variant="info"
                    :text="authorAbbreviatedName"
                    size="lg"
                  ></b-avatar>
                  <b-avatar
                    v-else
                    variant="dark"
                    size="lg"
                    icon="sunglasses"
                  ></b-avatar>
                  <div v-if="!forum.anonimo" class="text-muted">
                    {{ fullAuthorName }}
                  </div>
                  <div v-else class="text-muted">Anónimo</div>
                </b-col>
              </b-row>
            </b-col>
            <b-col
              sm="12"
              md="8"
              lg="8"
              align-self="center"
              class="forum-content"
            >
              <b-row>
                <b-col sm="12" lg="12">
                  <strong> {{ forum.titulo }} </strong>
                </b-col>
                <b-col sm="12" lg="12" class="text-justify">
                  {{ forum.descripcion }}
                </b-col>
              </b-row>
            </b-col>
            <b-col sm="12" md="12" lg="2" align-self="center">
              <b-row>
                <b-col
                  sm="12"
                  offset-sm="0"
                  offset-md="4"
                  md="4"
                  offset-lg="0"
                  lg="12"
                  class="text-muted"
                >
                  {{ forum.fecha }}
                </b-col>
                <b-col sm="12" md="4" lg="12" class="mt-4">
                  <b-badge variant="info">
                    <span>{{ forum.cantComentarios }}</span>
                    Comentarios
                  </b-badge>
                  <b-icon-trash-fill
                    v-if="isLoggedInAdmin"
                    scale="1.6"
                    class="cancel-icon ml-3"
                    variant="danger"
                  />
                </b-col>
              </b-row>
            </b-col>
          </b-row>
        </b-card>
        <b-row class="text-right mt-1" v-if="!modeWriteComment">
          <b-col align-self="end">
            <b-button variant="info" @click="modeWriteComment = true">
              Comentar
              <b-icon-pencil-fill />
            </b-button>
          </b-col>
        </b-row>
        <transition name="flipX">
          <b-card class="create-comment mt-2" v-if="modeWriteComment">
            <b-input-group class="mt-1">
              <b-form-textarea
                id="textarea"
                v-model="newComment.comentario"
                placeholder="Ingresa un comentario"
                rows="1"
                max-rows="3"
              ></b-form-textarea>
            </b-input-group>
            <b-row class="mt-2 justify-content-around">
              <b-icon-trash-fill
                class="mt-2 cancel-icon"
                scale="1.6"
                variant="danger"
                @click="cancelWriteComment"
              />
              <b-form-checkbox
                v-b-tooltip.hover
                title="Anónimo"
                switch
                size="lg"
                class="mt-1"
                v-model="newComment.anonimo"
              >
                <b-icon-sunglasses
                  v-if="newComment.anonimo"
                  scale="2"
                  class="ml-2"
                />
                <b-icon-eyeglasses v-else scale="2" class="ml-2" />
              </b-form-checkbox>
              <b-button
                variant="info"
                @click="publishComment"
                :disabled="!validForm"
              >
                Comentar</b-button
              >
            </b-row>
          </b-card>
        </transition>
        <CommentForum
          v-for="Comment in comments"
          :key="Comment.id"
          :data="Comment"
          class="mt-2 container-comments"
        />
      </div>
    </center>
  </div>
</template>

<script>
// Import components
import CommentForum from "./CommentForum.vue";

export default {
  components: {
    CommentForum,
  },
  data() {
    return {
      modeWriteComment: false,
      newComment: {
        id: 0,
        idForo: this.$route.params.id,
        autor: this.$store.state.user,
        comentario: "",
        fechaCreacion: "29.9.2021 08:40am",
        anonimo: false,
      },
      forum: {
        id: 1,
        autor: { name: " ", lastName1: " " },
        titulo: " ",
        descripcion: " ",
        fecha: " ",
        anonimo: false,
        cantComentarios: 1,
      },
      comments: [],
    };
  },
  mounted() {
    //console.log("You see the forum with id: " + this.$route.params.id);
    //Fetch data for comment
    this.forum = {
      id: 1,
      autor: { name: "Juan", lastName1: "Salas" },
      titulo: "¿Pasos iniciales para aprender c++ rapido?",
      descripcion:
        "Estoy llevando el curso de fundamentos con el profesor X y no logro comprender la materia, pronto será el examen y no se que hacer",
      fecha: "28.9.2021 03:46pm",
      anonimo: false,
      cantComentarios: 4,
    };
    this.comments = [
      {
        id: 1,
        idForo: 1,
        autor: { name: "Miguel", lastName1: "Salas" },
        comentario: "¿Asiste a tutorias?",
        fechaCreacion: "29.9.2021 08:40am",
        anonimo: false,
      },
      {
        id: 2,
        idForo: 1,
        autor: { name: "Daniela", lastName1: "Hernandez" },
        comentario: "En youtube hay buen contenido",
        fechaCreacion: "28.9.2021 05:23pm",
        anonimo: true,
      },
    ];
  },
  computed: {
    authorAbbreviatedName() {
      return this.forum.autor.name[0] + this.forum.autor.lastName1[0];
    },
    fullAuthorName() {
      return (
        this.forum.autor.name.split(" ")[0] + " " + this.forum.autor.lastName1
      );
    },
    validForm() {
      return this.newComment.comentario != "";
    },
    isLoggedInAdmin() {
      return this.$store.getters.isLoggedInAdmin;
    },
  },
  methods: {
    cancelWriteComment() {
      this.modeWriteComment = false;
      this.newComment.comentario = "";
      this.newComment.anonimo = false;
    },
    publishComment() {
      this.modeWriteComment = false;
      this.comments.unshift(this.newComment);
      this.newComment = {
        id: 0,
        idForo: this.$route.params.id,
        autor: this.$store.state.user,
        comentario: "",
        fechaCreacion: "29.9.2021 08:40am",
        anonimo: false,
      };
    },
    backForums() {
      this.$router.push({ path: "/forums" });
    },
  },
};
</script>

<style scoped>
#forum-page,
center {
  height: 40%;
}
.main-card {
  margin-inline: 10vw;
  margin-block: 1vw;
}
.forum {
  border-radius: 10px;
}
.container-comments {
  margin-inline: 3vw;
}
.create-comment {
  margin-inline: 3vw;
  border-radius: 10px;
}
.cancel-icon:hover {
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  transform: scale(1.5);
}
.forum-content {
  background-color: rgb(243, 243, 243);
  border-radius: 50px;
  padding-block: 15px;
}
</style>
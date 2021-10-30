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
                    v-if="!forum.isAnon"
                    variant="secondary"
                    :src="
                      'http://localhost:9191/api/v1/userPhoto/photo/' +
                      this.forum.authorId.id
                    "
                    :text="authorAbbreviatedName"
                    size="lg"
                  ></b-avatar>
                  <b-avatar
                    v-else
                    variant="dark"
                    size="lg"
                    icon="sunglasses"
                  ></b-avatar>
                  <div v-if="!forum.isAnon" class="text-muted">
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
                  <strong> {{ forum.title }} </strong>
                </b-col>
                <b-col sm="12" lg="12" class="text-justify">
                  {{ forum.description }}
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
                  {{ getFormatDate }}
                </b-col>
                <b-col sm="12" md="4" lg="12" class="mt-4">
                  <b-badge variant="info">
                    <span>{{ forum.commentsQuantity }}</span>
                    Comentarios
                  </b-badge>
                  <b-icon-trash-fill
                    v-if="isLoggedInAdmin"
                    scale="1.6"
                    class="cancel-icon ml-3"
                    variant="danger"
                    @click="deleteForum"
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
        <b-alert :show="registerSuccess" variant="success" fade dismissible>
          <h4>
            Comentario publicado
            <b-icon-emoji-laughing scale="1" />
          </h4>
        </b-alert>
        <b-alert :show="registerError" variant="danger" fade dismissible>
          <h4>
            Ha ocurrido un error, comentario no publicado
            <b-icon-emoji-frown scale="1" />
          </h4>
        </b-alert>
        <b-alert
          :show="registerReportSuccess"
          variant="success"
          fade
          dismissible
        >
          <h4>
            Reporte registrado
            <b-icon-emoji-wink scale="1" />
          </h4>
          Gracias por tu reporte uno de nuestros administradores lo revisara en
          brevedad
        </b-alert>
        <b-alert :show="registerReportError" variant="danger" fade dismissible>
          <h4>
            Ha ocurrido un error, reporte no enviado
            <b-icon-emoji-frown scale="1" />
          </h4>
        </b-alert>
        <b-overlay :show="status == 'Loading'" variant="dark">
          <transition name="flipX">
            <b-card class="create-comment mt-2" v-if="modeWriteComment">
              <b-input-group class="mt-1">
                <b-form-textarea
                  id="textarea"
                  v-model="newComment.description"
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
                  v-model="newComment.isAnon"
                >
                  <b-icon-sunglasses
                    v-if="newComment.isAnon"
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
          <template #overlay>
            <div class="text-center text-white">
              <b-icon
                icon="stopwatch"
                font-scale="3"
                animation="cylon"
              ></b-icon>
              <p id="cancel-label">Por favor espere...</p>
            </div>
          </template>
        </b-overlay>
        <div>
          <b-modal
            :visible="modeReportComment"
            title="Reporte de comentario"
            @hidden="resetReport"
            @ok="submitReport"
            cancel-title="Cancelar"
            ok-title="Enviar reporte"
          >
            <form @submit.stop.prevent="submitReport">
              {{ showReportComment }}
              <b-form-group
                label="¿Motivo del reporte?"
                label-for="name-input"
                invalid-feedback="Motivo es requerido"
              >
                <b-form-input
                  id="name-input"
                  v-model="newCommentReport.motive"
                  required
                ></b-form-input>
              </b-form-group>
            </form>
          </b-modal>
        </div>
        <CommentForum
          v-for="Comment in comments"
          :key="Comment.id"
          :data="Comment"
          :deleteComment="deleteComment"
          :reportComment="reportComment"
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
      status: "Ready",
      modeWriteComment: false,
      modeReportComment: false,
      registerError: false,
      registerSuccess: false,
      registerReportError: false,
      registerReportSuccess: false,
      newComment: {
        id: 0,
        forumId: { id: this.$route.params.id },
        authorId: this.$store.state.user,
        description: "",
        creationDate: null,
        isAnon: 0,
      },
      forum: {
        id: 1,
        authorId: { name: " ", lastName1: " " },
        titulo: " ",
        descripcion: " ",
        fecha: " ",
        isAnon: 0,
        cantDescriptions: 1,
      },
      comments: [],
      newCommentReport: {
        comment: null,
        user: this.$store.state.user,
        motive: "",
      },
    };
  },
  mounted() {
    fetch("/api/v1/forumTopic/" + this.$route.params.id)
      .then((response) => response.json())
      .then((data) => {
        this.forum = data;
      });
    fetch("/api/v1/forumComments/forum/" + this.$route.params.id)
      .then((response) => response.json())
      .then((data) => {
        this.comments = data.reverse();
      });
  },
  computed: {
    authorAbbreviatedName() {
      return this.forum.authorId.name[0] + this.forum.authorId.lastName1[0];
    },
    fullAuthorName() {
      return this.forum.authorId.name + " " + this.forum.authorId.lastName1;
    },
    validForm() {
      return this.newComment.description != "";
    },
    isLoggedInAdmin() {
      return this.$store.getters.isLoggedInAdmin;
    },
    getFormatDate() {
      let dateFix = new Date(this.forum.creationDate);
      dateFix.setHours(dateFix.getHours() - 6);
      return dateFix.toLocaleString();
    },
    showReportComment() {
      if (this.newCommentReport.comment) {
        if (this.newCommentReport.comment.isAnon) {
          return "Anónimo: " + this.newCommentReport.comment.description;
        }
        let autor = !this.newCommentReport.comment.isAnon
          ? this.newCommentReport.comment.authorId.name +
            " " +
            this.newCommentReport.comment.authorId.lastName1
          : (autor = "Anónimo");
        return autor + ": " + this.newCommentReport.comment.description;
      }
      return "";
    },
  },
  methods: {
    cancelWriteComment() {
      this.modeWriteComment = false;
      this.newComment.description = "";
      this.newComment.isAnon = false;
    },
    async publishComment() {
      this.status = "Loading";
      this.newComment.isAnon = this.newComment.isAnon ? 1 : 0;
      const response = await fetch("/api/v1/forumComments", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.newComment),
      });
      if (response.status == 200) {
        this.registerSuccess = true;
        fetch("/api/v1/forumComments/forum/" + this.$route.params.id)
          .then((response) => response.json())
          .then((data) => {
            this.comments = data.reverse();
          });
        this.newComment = {
          id: 0,
          forumId: { id: this.$route.params.id },
          authorId: this.$store.state.user,
          description: "",
          creationDate: null,
          isAnon: 0,
        };
      } else {
        this.registerError = true;
      }
      this.modeWriteComment = false;
      this.status = "Ready";
    },
    backForums() {
      this.$router.push({ path: "/forums" });
    },
    async deleteForum() {
      const response = await fetch("/api/v1/forumTopic", {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.forum),
      });
      if (response.status == 200) {
        this.$router.push({ path: "/forums" });
      }
    },
    async deleteComment(data) {
      const response = await fetch("/api/v1/forumComments", {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
      });
      if (response.status == 200) {
        fetch("/api/v1/forumTopic/" + this.$route.params.id)
          .then((response) => response.json())
          .then((data) => {
            this.forum = data;
          });
        fetch("/api/v1/forumComments/forum/" + this.$route.params.id)
          .then((response) => response.json())
          .then((data) => {
            this.comments = data.reverse();
          });
      }
    },
    reportComment(data) {
      this.newCommentReport.comment = data;
      this.modeReportComment = true;
    },
    resetReport() {
      this.modeReportComment = false;
      this.newCommentReport = {
        comment: null,
        user: this.$store.state.user,
        motive: "",
      };
    },
    async submitReport() {
      if (this.newCommentReport.comment) {
        const response = await fetch("/api/v1/commentReports", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(this.newCommentReport),
        });
        if (response.status == 200) {
          this.registerReportSuccess = true;
        } else {
          this.registerReportError = true;
        }
      }
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
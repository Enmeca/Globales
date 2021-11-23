<template>
  <div id="forum-page">
    <center class="main-card">
      <div class="container-forum">
        <b-row class="mb-2" align-h="between">
          <b-col sm="5" md="4" lg="4" class="text-left">
            <b-button variant="secondary" @click="backTutors">
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
                    variant="secondary"
                    :src="
                      'http://localhost:9191/api/v1/userPhoto/photo/' +
                      this.tutor.id
                    "
                    :text="authorAbbreviatedName"
                    size="5em"
                  ></b-avatar>
                </b-col>
              </b-row>
            </b-col>
            <b-col
              sm="12"
              md="7"
              lg="7"
              align-self="center"
              class="forum-content"
            >
              <b-row>
                <b-col sm="12" lg="12">
                  <strong> {{ fullAuthorName }} </strong>
                </b-col>
                <b-col sm="12" lg="12" class="text-justify">
                  {{ tutor.description }}
                </b-col>
              </b-row>
            </b-col>
            <b-col sm="12" md="12" lg="2" align-self="center">
              <b-row v-if="this.averageRating > 0.5">
                <b-col sm="12" md="4" lg="12" class="mt-4">
                  <label for="rating">
                    <strong>Calificación:</strong>
                  </label>
                  <b-form-rating
                    id="rating"
                    variant="warning"
                    v-model="averageRating"
                    readonly
                    show-value
                    precision="1"
                    no-border
                  ></b-form-rating>
                </b-col>
              </b-row>
              <b-row v-else>
                <p class="ml-3">Sin Calificaciones</p>
              </b-row>
            </b-col>
          </b-row>
        </b-card>
        <b-row
          class="text-right mt-1 justify-content-end"
          v-if="!modeWriteReview && canQualify"
        >
          <b-col sm="5" md="4" lg="4">
            <b-button variant="info" @click="modeWriteReview = true">
              Calificar
              <b-icon-pencil-fill />
            </b-button>
          </b-col>
        </b-row>
        <b-alert :show="registerSuccess" variant="success" fade dismissible>
          <h4>
            Calificacion publicada
            <b-icon-emoji-laughing scale="1" />
          </h4>
        </b-alert>
        <b-alert :show="registerError" variant="danger" fade dismissible>
          <h4>
            Ha ocurrido un error, calificacion no publicada
            <b-icon-emoji-frown scale="1" />
          </h4>
        </b-alert>
        <b-overlay :show="status == 'Loading'" variant="dark">
          <transition name="flipX">
            <b-card class="create-comment mt-2" v-if="modeWriteReview">
              <b-input-group class="mt-1">
                <b-form-input
                  id="textarea"
                  v-model="newReview.comments"
                  placeholder="Ingresa un comentario"
                  rows="1"
                  max-rows="3"
                ></b-form-input>
              </b-input-group>
              <b-input-group class="mt-3">
                <label for="rating" class="mt-1">
                  <strong>Calificación:</strong>
                </label>
                <b-form-rating
                  id="rating"
                  variant="warning"
                  v-model="newReview.score"
                  show-value
                  stars="5"
                  precision="1"
                  no-border
                ></b-form-rating>
              </b-input-group>
              <b-row class="mt-2 justify-content-around">
                <b-icon-trash-fill
                  class="mt-2 cancel-icon"
                  scale="1.6"
                  variant="danger"
                  @click="cancelWriteReview"
                />
                <b-button
                  variant="info"
                  @click="publishReview"
                  :disabled="!validForm"
                >
                  Enviar</b-button
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
        <ReviewTutor
          v-for="review in reviews"
          :key="review.user.id"
          :data="review"
          class="mt-2 container-comments"
        />
      </div>
    </center>
  </div>
</template>

<script>
// Import components
import ReviewTutor from "./ReviewTutor.vue";

export default {
  components: {
    ReviewTutor,
  },
  data() {
    return {
      status: "Ready",
      modeWriteReview: false,
      modeReportComment: false,
      registerError: false,
      registerSuccess: false,
      newReview: {
        user: { id: this.$store.state.user.id },
        tutor: { id: this.$route.params.id },
        score: 0,
        comments: "",
      },
      tutor: {
        name: " ",
        lastName1: " ",
        lastName2: " ",
      },
      reviews: [],
    };
  },
  mounted() {
    fetch("/api/v1/user/" + this.$route.params.id)
      .then((response) => response.json())
      .then((data) => {
        this.tutor = data;
      });
    fetch("/api/v1/tutorReviews/tutor/" + this.$route.params.id)
      .then((response) => response.json())
      .then((data) => {
        this.reviews = data;
      });
  },
  computed: {
    authorAbbreviatedName() {
      return this.tutor.name[0] + this.tutor.lastName1[0];
    },
    fullAuthorName() {
      return (
        this.tutor.name +
        " " +
        this.tutor.lastName1 +
        " " +
        this.tutor.lastName2
      );
    },
    validForm() {
      return this.newReview.comments != "";
    },
    averageRating() {
      let a =
        this.reviews.reduce((sum, reviews) => sum + reviews.score, 0) /
        this.reviews.length;
      // fix decimal
      return a;
    },
    canQualify() {
      return !this.reviews.some(
        (review) => review.user.id === this.$store.state.user.id
      );
    },
  },
  methods: {
    cancelWriteReview() {
      this.modeWriteReview = false;
      this.newReview.comments = "";
      this.newReview.score = 0;
    },
    async publishReview() {
      this.status = "Loading";
      const response = await fetch("/api/v1/tutorReviews", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.newReview),
      });
      if (response.status == 200) {
        this.registerSuccess = true;
        fetch("/api/v1/tutorReviews/tutor/" + this.$route.params.id)
          .then((response) => response.json())
          .then((data) => {
            this.reviews = data;
          });
        this.newReview = {
          user: { id: this.$store.state.user.id },
          tutor: { id: this.$route.params.id },
          score: 0,
          comments: "",
        };
      } else {
        this.registerError = true;
      }
      this.modeWriteReview = false;
      this.status = "Ready";
    },
    backTutors() {
      this.$router.push({ path: "/tutors" });
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
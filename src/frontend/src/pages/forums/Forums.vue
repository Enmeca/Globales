<template>
  <div id="forums-page">
    <center class="main-card">
      <b-alert :show="registerSuccess" variant="success" fade dismissible>
        <h4>
          Foro publicado
          <b-icon-emoji-laughing scale="1" />
        </h4>
      </b-alert>
      <b-alert :show="registerError" variant="danger" fade dismissible>
        <h4>
          Ha ocurrido un error, foro no publicado
          <b-icon-emoji-frown scale="1" />
        </h4>
      </b-alert>
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
            <b-form-input v-model="searchField"> </b-form-input>
          </b-input-group>
        </b-col>
        <b-col sm="12" md="4" lg="4" class="mt-3" v-if="!modeCreateForum">
          <b-button variant="info" @click="modeCreateForum = true">
            Crear foro
            <b-icon-pencil-fill />
          </b-button>
        </b-col>
      </b-row>
      <b-overlay :show="status == 'Loading'" variant="dark">
        <transition name="flipX">
          <b-card
            class="create-forum mt-3"
            v-if="modeCreateForum"
            style="animation-duration: 0.8s"
          >
            <b-input-group class="mt-1">
              <b-form-input
                v-model="newForum.title"
                placeholder="¿Título del foro o pregunta?"
              >
              </b-form-input>
            </b-input-group>
            <b-input-group class="mt-1">
              <b-form-textarea
                id="textarea"
                v-model="newForum.description"
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
                v-model="newForum.isAnon"
              >
                <b-icon-sunglasses
                  v-if="newForum.isAnon"
                  scale="2"
                  class="ml-2"
                />
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
        <template #overlay>
          <div class="text-center text-white">
            <b-icon icon="stopwatch" font-scale="3" animation="cylon"></b-icon>
            <p id="cancel-label">Por favor espere...</p>
          </div>
        </template>
      </b-overlay>
      <transition-group name="bounceLeft" tag="ul" class="pl-0">
        <ItemForum
          v-for="forum in filterForums"
          :key="forum.id"
          :data="forum"
          class="mt-3"
          style="animation-duration: 0.8s"
        />
      </transition-group>
      <b-card v-if="filterForums.length == 0" class="mt-3">
        <p>
          Ningún foro coincide con el criterio de busqueda
          <b-icon-emoji-frown />
        </p>
      </b-card>
    </center>
  </div>
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
      status: "Ready",
      searchField: "",
      anyoneFound: false,
      modeCreateForum: false,
      registerError: false,
      registerSuccess: false,
      newForum: {
        authorId: this.$store.state.user,
        title: "",
        description: "",
        creationDate: null,
        commentsQuantity: 0,
        isAnon: 0,
      },
      forums: [],
    };
  },
  mounted() {
    fetch("/api/v1/forumTopic/orderByDateDesc")
      .then((response) => response.json())
      .then((data) => {
        this.forums = data;
      });
  },
  methods: {
    async publishForum() {
      this.status = "Loading";
      this.newForum.isAnon = this.newForum.isAnon ? 1 : 0;
      const response = await fetch("/api/v1/forumTopic", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.newForum),
      });
      if (response.status == 200) {
        (this.registerSuccess = true),
          await fetch("/api/v1/forumTopic/orderByDateDesc")
            .then((response) => response.json())
            .then((data) => {
              this.forums = data;
            });
        this.newForum = {
          title: "",
          description: "",
          creationDate: null,
          isAnon: 0,
          authorId: this.$store.state.user,
        };
      } else {
        this.registerError = true;
      }
      this.modeCreateForum = false;
      this.status = "Ready";
    },
    cancelCreateForum() {
      this.modeCreateForum = false;
      this.newForum = {
        title: "",
        description: "",
        creationDate: "28.09.2021 08:26pm",
        isAnon: 0,
        authorId: this.$store.state.user,
      };
    },
    backHome() {
      this.$router.push({ path: "/home" });
    },
  },
  computed: {
    validForm() {
      return this.newForum.title != "" && this.newForum.description != "";
    },
    filterForums() {
      const criteria = this.searchField.trim().toLowerCase();
      if (criteria) {
        return this.forums.filter(
          (forum) => forum.title.toLowerCase().indexOf(criteria) > -1
        );
      }
      return this.forums;
    },
  },
};
</script>
<style scoped>
#forums-page,
center {
  height: 40%;
}
.main-card {
  margin-inline: 10vw;
  margin-block: 2vw;
}
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
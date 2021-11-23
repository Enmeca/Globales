<template>
  <div id="matchs-page">
    <center>
      <b-card class="main-card text-light">
        <b-card-title class="h2">
          Matchs
          <b-icon-bell-fill />
        </b-card-title>
        <b-card-body>
          <b-modal
            :visible="modalNewMatch"
            title="¡Nuevo Match!"
            @ok="modalNewMatch = false"
            ok-title="OK"
            ok-only
          >
            <p>
              {{
                newMatchUser.name +
                " " +
                newMatchUser.lastName1 +
                " " +
                newMatchUser.lastName2
              }}
              y tú ahora pueden hablar en la sección de chat
            </p>
          </b-modal>
          <div class="example-3d">
            <swiper ref="mySwiper" :options="swiperOption">
              <swiper-slide v-for="match in matchList" :key="match.id">
                <b-card class="second-card">
                  <b-card-body class="text-left p-0 m-0">
                    <h4 class="text-center">
                      {{ match.user.name }}
                      {{ match.user.lastName1 }}
                      {{ match.user.lastName2 }}
                    </h4>
                    <b-avatar
                      variant="light"
                      size="18rem"
                      :text="match.user.name[0] + match.user.lastName1[0]"
                      :src="
                        'http://localhost:8080/api/v1/userPhoto/photo/' +
                        match.user.id
                      "
                    ></b-avatar>
                    <p>
                      <strong>Descripcion:</strong>{{ match.user.description }}
                    </p>
                    <p>
                      <strong>Compatibilidad:</strong
                      >{{ compatibilityUser(match.user, match.tags) }}%
                    </p>
                    <p>
                      <strong>Universidad:</strong
                      >{{ getUniversity(match.user.universityId) }}
                    </p>
                    <p>
                      <strong>Tags:</strong>
                      <b-col cols="12" align-self="center">
                        <b-input-group class="mb-2 input">
                          <b-form-tags
                            id="tags-with-dropdown"
                            v-model="match.tags"
                            no-outer-focus
                          >
                            <template>
                              <ul class="list-inline d-inline-block m-0">
                                <li
                                  v-for="tag in match.tags"
                                  :key="tag"
                                  class="list-inline-item"
                                >
                                  <b-form-tag
                                    :title="tag"
                                    disabled
                                    variant="info"
                                    >{{ tag }}</b-form-tag
                                  >
                                </li>
                              </ul>
                            </template>
                          </b-form-tags>
                        </b-input-group>
                      </b-col>
                    </p>
                    <b-button
                      @click="sendMatch(match.user.id)"
                      variant="danger"
                      class="m-1"
                    >
                      <b-icon-heart-fill
                    /></b-button>
                    <b-button
                      @click="omitMatch(match.user.id)"
                      variant="info"
                      class="m-1"
                    >
                      <b-icon-arrow-right
                    /></b-button>
                  </b-card-body>
                </b-card>
              </swiper-slide>
            </swiper>
          </div>
        </b-card-body>
      </b-card>
    </center>
  </div>
</template>

<script>
import { Swiper, SwiperSlide, directive } from "vue-awesome-swiper";
import "swiper/swiper-bundle.css";
export default {
  components: { Swiper, SwiperSlide },
  directives: {
    swiper: directive,
  },
  data() {
    return {
      swiperOption: {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: "auto",
        coverflowEffect: {
          rotate: 50,
          stretch: 0,
          depth: 100,
          modifier: 1,
          slideShadows: true,
        },
        pagination: {
          el: ".swiper-pagination",
        },
      },
      actualProfile: {
        id: "0000",
        careerId: "0",
        universityId: "0",
        email: "N/A",
        password: "0",
        name: "",
        lastName1: "",
        lastName2: "",
        dateOfBirth: "",
        description: "No hay matchs disponibles",
        isTutor: 0,
        isAdmin: 0,
        isActive: 1,
        userPhoto: null,
      },
      matchList: [],
      tags: [],
      matches: [],
      careers: [],
      universities: [],
      modalNewMatch: false,
      newMatchUser: {
        name: "",
        lastName1: "",
        lastName2: "",
      },
    };
  },
  mounted() {
    fetch(
      "/api/v1/userTags/usersForMatch/getUsersByCompatibility/" +
        this.$store.state.user.id
    )
      .then((response) => response.json())
      .then((data) => {
        var list = data;
        list.sort(() => (Math.random() > 0.5 ? 1 : -1));
        this.matchList = list;
      });
  },
  beforeMount() {
    fetch(
      "/api/v1/userTags/onlyTags/asStrings/byUserId/" +
        this.$store.state.user.id
    )
      .then((response) => response.json())
      .then((data) => {
        this.tags = data;
      });
    fetch("/api/v1/university")
      .then((response) => response.json())
      .then((data) => {
        this.universities = data.map((university) => ({
          value: university.id,
          text: university.name,
        }));
      });
    fetch("/api/v1/career")
      .then((response) => response.json())
      .then((data) => {
        this.careers = data.map((career) => ({
          value: career.id,
          text: career.name,
        }));
      });
    fetch(
      "/api/v1/userTags/usersForMatch/getUsersByCompatibility/" +
        this.$store.state.user.id
    )
      .then((response) => response.json())
      .then((data) => {
        var list = data;
        list.sort(() => (Math.random() > 0.5 ? 1 : -1));
        this.matchList = list;
      });
    fetch(
      "/api/v1/userTags/onlyTags/asStrings/byUserId/" +
        this.$store.state.user.id
    )
      .then((response) => response.json())
      .then((data) => {
        this.tags = data;
      });
  },
  methods: {
    getUniversity(id) {
      return this.universities.find((x) => x.value == id).text;
    },
    omitMatch(id) {
      this.matchList = this.matchList.filter((x) => x.user.id != id);
    },
    async sendMatch(friend) {
      const response = await fetch("/api/v1/likedUsers", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          user: { id: this.$store.state.user.id },
          likedUser: { id: friend },
        }),
      });
      if (response.status === 200) {
        this.matchList = this.matchList.filter((x) => x.user.id != friend);
        let data = await response.json();
        if (data.user === null) {
          // match it!
          this.newMatchUser = data.likedUser;
          this.modalNewMatch = true;
          this.$store.dispatch("fetchChats", this.$store.state.user.id);
        }
      } else {
        alert("algo fallo");
      }
    },
    compatibilityUser(user, Utags) {
      let compatibility = 0;
      if (user.universityId === this.$store.state.user.universityId)
        compatibility += 1;
      for (let element of Utags) {
        if (this.tags.find((x) => x === element) !== undefined)
          compatibility += 1;
      }
      let result = compatibility / (this.tags.length + 1);
      result = result * 100;
      return result.toFixed(1);
    },
  },
};
</script>

<style scoped>
#matchs-page,
center {
  height: 40%;
}
.second-card {
  background-color: rgba(255, 255, 255, 0.863) !important;
  color: rgb(83, 76, 76);
  max-width: 20rem;
  min-width: 20rem;
}
.main-card {
  background-color: rgba(0, 0, 0, 0.5);
  margin-inline: 10vw;
  margin-block: 3vw;
}
p {
  font-size: 20px;
}

.example-3d {
  position: relative;
  overflow: hidden;
  padding: 15px;
}
.swiper {
  width: 300px !important;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -150px;
  margin-top: -150px;
}
</style>

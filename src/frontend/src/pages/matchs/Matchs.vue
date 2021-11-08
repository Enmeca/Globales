<template>
  <div id="matchs-page">
    <center>
      <b-card class="main-card text-light">
        <b-card-title class="h2">
          Matchs
          <b-icon-bell-fill />
        </b-card-title>
        <b-card-body>
          <div class="example-3d">
            <swiper ref="mySwiper" :options="swiperOption">
              <swiper-slide v-for="match in matchList" :key="match.id">
                <b-card class="second-card">
                  <b-card-body class="text-left p-0 m-0">
                    <h4 class="text-center">
                      {{ match.name }}
                      {{ match.lastName1 }}
                      {{ match.lastName2 }}
                    </h4>
                    <b-avatar
                      variant="light"
                      size="18rem"
                      :text="match.name[0] + match.lastName1[0]"
                      :src="
                        'http://localhost:8080/api/v1/userPhoto/photo/' +
                        match.id
                      "
                    ></b-avatar>
                    <p><strong>Descripcion:</strong>{{ match.description }}</p>
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
                    <b-button variant="danger" class="m-1">
                      <b-icon-heart-fill
                    /></b-button>
                    <b-button @click="omitMatch()" variant="info" class="m-1">
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
    };
  },
  beforeMount() {
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
    fetch("/api/v1/user")
      .then((response) => response.json())
      .then((data) => {
        this.matchList = data;
        this.matchList.forEach((match) => {
          fetch("api/v1/userTags/user/" + match.id)
            .then((response) => response.json())
            .then((data) => {
              match.tags = data.map((tag) => tag.tag.name);
            });
        });
      });
  },
  methods: {
    /*async omitMatch() {
      this.actualProfile = this.matchList.pop();
      fetch("api/v1/userTags/user/" + this.actualProfile.id)
        .then((response) => response.json())
        .then((data) => {
          this.tags = data.map((tag) => tag.tag.name);
        });
    },*/
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

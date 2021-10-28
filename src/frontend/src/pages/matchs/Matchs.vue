<template>
  <div id="matchs-page">
    <center>
      <b-card class="main-card text-light">
        <b-card-title class="h2">
          Matchs
          <b-icon-bell-fill />
        </b-card-title>
        <b-card-body>
          <b-card-text>
            <p>
                <b-card class="second-card">
                  <b-card-body class="text-left p-0 m-0">
                    <h4 class="text-center">{{`${actualProfile.name} ${actualProfile.lastName1} ${actualProfile.lastName2}` }}</h4>
                    <b-img
                      v-if="matchList[0].image!=undefined"
                      id="preview-photo"
                      :src="this.user_photo.base64Photo"
                      thumbnail
                      fluid
                      rounded
                      alt="Profile image"
                    ></b-img>
                    <b-icon-person-fill v-else />
                     <p> 
                    <strong>Descripcion:</strong>{{actualProfile.description}}
                     </p>
                      <b-button @click="omitMatch()" variant="danger" class="m-1">
                        <b-icon-heart-fill/></b-button>
                      <b-button variant="info" class="m-1">
                        <b-icon-arrow-right/></b-button>
                  </b-card-body>
                </b-card>
            </p>
            <p>Tambien podras ver quien ha hecho match contigo</p>
          </b-card-text>
        </b-card-body>
      </b-card>
    </center>
  </div>
</template>

<script>
  //import { Swiper, SwiperSlide } from 'swiper/vue';
  //import 'swiper/css';

export default {
 data() {
    return {
      actualProfile:{
        id: "0000",
        careerId: "0",
        universityId: "0",
        email: "N/A",
        password: "0",
        name: "No",
        lastName1: "hay",
        lastName2: "Matchs",
        dateOfBirth: "",
        description: "No hay matchs disponibles",
        isTutor: 0,
        isAdmin: 0,
        isActive: 1,
        userPhoto: null
      },
      matchList: [],
      careers: [],
      universities: [],
    };
  },
  mounted(){
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
      fetch("/api/v1/user").
      then(response => response.json()).
      then(data => {this.matchList=data;this.omitMatch()});
      
  },
  methods:{
    omitMatch(){
        this.actualProfile=this.matchList.pop()
        
    },
    match(){

    }
  }
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
  max-width: 50%;
}
.main-card {
  background-color: rgba(0, 0, 0, 0.5);
  margin-inline: 10vw;
  margin-block: 10vw;
}
p {
  font-size: 20px;
}
</style>

<template>
  <div id="tutors-page">
    <center class="main-card">
      <b-row>
        <b-col sm="12" md="4" lg="4" class="mt-3">
          <b-button variant="secondary" @click="backHome">
            <b-icon-house-fill />
            Regresar
          </b-button>
        </b-col>
      </b-row>
      <transition-group name="bounceLeft" tag="ul" class="pl-0">
        <ItemTutor
          v-for="tutor in tutors"
          :key="tutor.tutor.id"
          :data="tutor"
          class="mt-3"
          style="animation-duration: 0.8s"
        />
      </transition-group>
    </center>
  </div>
</template>
<script>
import ItemTutor from "./ItemTutor.vue";
export default {
  components: {
    ItemTutor,
  },
  data() {
    return {
      tutors: [],
    };
  },
  mounted() {
    fetch("/api/v1/tutorReviews/all/averageRating")
      .then((response) => response.json())
      .then((data) => {
        this.tutors = data;
      });
  },
  methods: {
    backHome() {
      this.$router.push({ path: "/home" });
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
<template>
  <b-card class="forum" @click="goToTutor">
    <b-row>
      <b-col sm="12" md="4" lg="2" align-self="center">
        <b-row>
          <b-col>
            <b-avatar
              variant="secondary"
              :text="authorAbbreviatedName"
              :src="
                'http://localhost:9191/api/v1/userPhoto/photo/' +
                this.data.tutor.id
              "
              size="5em"
            ></b-avatar>
          </b-col>
        </b-row>
      </b-col>
      <b-col
        sm="12"
        md="8"
        lg="7"
        align-self="center"
        class="item-forum-content"
      >
        <b-row>
          <b-col sm="12" lg="12">
            <strong>
              {{
                data.tutor.name +
                " " +
                data.tutor.lastName1 +
                " " +
                data.tutor.lastName2
              }}
            </strong>
          </b-col>
          <b-col sm="12" lg="12" class="text-justify">
            {{ data.tutor.description }}
          </b-col>
        </b-row>
      </b-col>
      <b-col sm="12" md="12" lg="3" align-self="center">
        <b-row v-if="data.averageRating > 0.5">
          <b-col sm="12" md="4" lg="12">
            <label for="rating">
              <strong>Calificación:</strong>
            </label>
            <b-form-rating
              id="rating"
              variant="warning"
              v-model="data.averageRating"
              readonly
              show-value
              precision="2"
              no-border
            ></b-form-rating>
          </b-col>
        </b-row>
        <b-row v-else>
          <p class="ml-5">Sin Calificaciones</p>
        </b-row>
      </b-col>
    </b-row>
  </b-card>
</template>

<script>
export default {
  props: {
    data: {
      type: Object,
      required: true,
    },
  },
  computed: {
    authorAbbreviatedName() {
      return this.data.tutor.name[0] + this.data.tutor.lastName1[0];
    },
  },
  methods: {
    goToTutor() {
      this.$router.push({ path: "/tutors/" + this.data.tutor.id });
    },
  },
};
</script>

<style scoped>
.forum {
  border-radius: 10px;
}
.forum:hover {
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  transform: scale(1.04);
}
.item-forum-content {
  background-color: rgb(243, 243, 243);
  border-radius: 50px;
  padding-block: 15px;
}

#rating {
  background-color: rgb(243, 243, 243);
  border-radius: 50px;
}
</style>
<template>
  <b-card class="comment">
    <b-row>
      <b-col sm="12" md="4" lg="2" align-self="center">
        <b-row>
          <b-col>
            <b-avatar
              variant="secondary"
              :src="
                'http://localhost:9191/api/v1/userPhoto/photo/' +
                this.data.user.id
              "
              :text="authorAbbreviatedName"
              size="lg"
            ></b-avatar>
            <div v-if="!data.isAnon" class="text-muted">
              {{ fullAuthorName }}
            </div>
          </b-col>
        </b-row>
      </b-col>
      <b-col sm="12" md="8" lg="8" align-self="center">
        <b-row class="comment-content">
          <b-col sm="3" lg="3" class="text-justify">
            <h5>
              {{ data.score }}

              <b-icon-star-fill class="ml-2" variant="warning" scale="1.5" />
            </h5>
          </b-col>
          <b-col sm="9" lg="9" class="text-left">
            {{ data.comments }}
          </b-col>
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
      return this.data.user.name[0] + this.data.user.lastName1[0];
    },
    fullAuthorName() {
      return this.data.user.name.split(" ")[0] + " " + this.data.user.lastName1;
    },
  },
};
</script>

<style scoped>
.comment {
  border-radius: 10px;
}
.flag-icon:hover {
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  transform: scale(1.5);
}
.comment-content {
  background-color: rgb(243, 243, 243);
  border-radius: 50px;
  padding-block: 15px;
}
</style>
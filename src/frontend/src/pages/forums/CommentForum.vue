<template>
  <b-card class="comment">
    <b-row>
      <b-col sm="12" md="4" lg="2" align-self="center">
        <b-row>
          <b-col>
            <b-avatar
              v-if="!data.isAnon"
              variant="secondary"
              :src="
                'http://localhost:9191/api/v1/userPhoto/photo/' +
                this.data.authorId.id
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
            <div v-if="!data.isAnon" class="text-muted">
              {{ fullAuthorName }}
            </div>
            <div v-else class="text-muted">Anónimo</div>
          </b-col>
        </b-row>
      </b-col>
      <b-col sm="12" md="8" lg="8" align-self="center">
        <b-row class="comment-content">
          <b-col sm="12" lg="12" class="text-justify">
            {{ data.description }}
          </b-col>
        </b-row>
      </b-col>
      <b-col sm="12" md="12" lg="2" align-self="center">
        <b-row class="justify-content-center">
          <b-col
            sm="10"
            offset-sm="0"
            offset-md="4"
            md="4"
            offset-lg="0"
            lg="12"
            class="text-muted"
            align-self="center"
          >
            {{ getFormatDate }}
          </b-col>
          <b-col
            sm="2"
            offset-sm="0"
            offset-md="4"
            md="8"
            offset-lg="0"
            lg="12"
            align-self="center"
          >
            <b-icon-trash-fill
              v-if="isLoggedInAdmin"
              class="flag-icon"
              variant="danger"
              @click="deleteComment(data)"
            />
            <b-icon-flag-fill
              v-else
              class="flag-icon"
              variant="danger"
              @click="reportComment(data)"
            />
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
    deleteComment: {
      type: Function,
      required: true,
    },
    reportComment: {
      type: Function,
      required: true,
    },
  },
  computed: {
    authorAbbreviatedName() {
      return this.data.authorId.name[0] + this.data.authorId.lastName1[0];
    },
    fullAuthorName() {
      return (
        this.data.authorId.name.split(" ")[0] +
        " " +
        this.data.authorId.lastName1
      );
    },
    isLoggedInAdmin() {
      return this.$store.getters.isLoggedInAdmin;
    },
    getFormatDate() {
      let dateFix = new Date(this.data.creationDate);
      //dateFix.setHours(dateFix.getHours() - 6);
      return dateFix.toLocaleString();
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
<template>
  <b-card class="forum" @click="goToForum">
    <b-row>
      <b-col sm="12" md="4" lg="2" align-self="center">
        <b-row>
          <b-col>
            <b-avatar
              v-if="!data.isAnon"
              variant="secondary"
              :text="authorAbbreviatedName"
              :src="
                'http://localhost:9191/api/v1/userPhoto/photo/' +
                this.data.authorId.id
              "
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
      <b-col
        sm="12"
        md="8"
        lg="8"
        align-self="center"
        class="item-forum-content"
      >
        <b-row>
          <b-col sm="12" lg="12">
            <strong> {{ data.title }} </strong>
          </b-col>
          <b-col sm="12" lg="12" class="text-justify">
            {{ getSpoilerDescription }}
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
          <b-col sm="12" md="4" lg="12">
            <b-badge variant="info" pill>
              <span v-if="data.commentsQuantity > 0">
                {{ data.commentsQuantity }}</span
              >
              <span v-else> Sin </span>
              Comentarios
            </b-badge>
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
      return this.data.authorId.name[0] + this.data.authorId.lastName1[0];
    },
    fullAuthorName() {
      return (
        this.data.authorId.name.split(" ")[0] +
        " " +
        this.data.authorId.lastName1
      );
    },
    getSpoilerDescription() {
      return this.data.description.length < 100
        ? this.data.description
        : this.data.description.slice(0, 100) + "...";
    },
    getFormatDate() {
      let dateFix = new Date(this.data.creationDate);
      //dateFix.setHours(dateFix.getHours() - 6);
      return dateFix.toLocaleString();
    },
  },
  methods: {
    goToForum() {
      // forums/id
      this.$router.push({ path: "/forums/" + this.data.id });
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
</style>
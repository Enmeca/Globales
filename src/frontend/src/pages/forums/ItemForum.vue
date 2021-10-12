<template>
  <b-card class="forum" @click="goToForum">
    <b-row>
      <b-col sm="12" md="4" lg="2" align-self="center">
        <b-row>
          <b-col>
            <b-avatar
              v-if="!data.anonimo"
              variant="info"
              :text="authorAbbreviatedName"
              size="lg"
            ></b-avatar>
            <b-avatar
              v-else
              variant="dark"
              size="lg"
              icon="sunglasses"
            ></b-avatar>
            <div v-if="!data.anonimo" class="text-muted">
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
            <strong> {{ data.titulo }} </strong>
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
            {{ data.fecha }}
          </b-col>
          <b-col sm="12" md="4" lg="12">
            <b-badge variant="info">
              <span>{{ data.cantComentarios }}</span>
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
      return this.data.autor.name[0] + this.data.autor.lastName1[0];
    },
    fullAuthorName() {
      return (
        this.data.autor.name.split(" ")[0] + " " + this.data.autor.lastName1
      );
    },
    getSpoilerDescription() {
      return this.data.descripcion.length < 100
        ? this.data.descripcion
        : this.data.descripcion.slice(0, 100) + "...";
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
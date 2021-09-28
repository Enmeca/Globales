<template>
  <div id="home-page">
    <center>
      <b-card class="main-card text-light">
        <b-card-title class="display-4">
          Administrador
          <b-icon-people-fill />
        </b-card-title>
        <b-card-body>
          <b-card-text>
            <p>Desde aqui se pueden administrar usuarios y foros.</p>
            <p>Asi como registrar otros administradores o tutores.</p>
          </b-card-text>
        </b-card-body>
      </b-card>
    </center>
  </div>
</template>

<script>
export default {
  data() {
    return {
      date_server: "",
    };
  },
  mounted() {
    fetch("/api/v1/home/date")
      .then((response) => response.text())
      .then((data) => {
        this.date_server = data;
      });
  },
  computed: {
    userName() {
      if (this.$store.getters.isLoggedIn) {
        return "Bienvenido " + this.$store.state.user.name;
      }
      return "";
    },
  },
};
</script>

<style scoped>
center {
  margin: 5vw;
  margin-inline: 15vw;
}
.main-card {
  background-color: rgba(0, 0, 0, 0.5);
}
p {
  font-size: 20px;
}
.display-4 {
  font-size: 2.4em;
}
</style>

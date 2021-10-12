<template>
  <div id="home-page">
    <center>
      <b-card class="main-card text-light">
        <b-card-title class="display-4">
          UFriends
          <b-icon-people-fill />
          <p>{{ welcomeUser }}</p>
        </b-card-title>
        <b-card-body>
          <b-card-text>
            <p>
              Una aplicación en la que podrás conocer amigos de tu universidad.
            </p>
            <p>
              Nuestro algoritmo te emparejara con personas con gustos similares
              a los tuyos.
            </p>
            <p>
              Conoce tutores que podrán ayudarte con las asignaciones de los
              cursos.
            </p>
            <p>
              Podrás participar y crear foros de discusión, inclusive de forma
              anonima.
            </p>
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
    welcomeUser() {
      if (this.$store.getters.isLoggedIn) {
        return (
          "Bienvenido " +
          this.$store.state.user.name +
          " " +
          this.$store.state.user.lastName1 +
          " " +
          this.$store.state.user.lastName2
        );
      }
      return "";
    },
  },
};
</script>

<style scoped>
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

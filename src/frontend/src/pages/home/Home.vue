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
              Una aplicación en la que podrás conocer amigos de tu universidad
              <b-icon-chat-dots-fill />
            </p>
            <p>
              <b-icon-bell-fill />
              Nuestro algoritmo te emparejara con personas con gustos similares
              a los tuyos
            </p>
            <p>
              Conoce tutores que podrán ayudarte con las asignaciones de los
              cursos
              <b-icon-people-fill />
              <b-icon-list-stars />
            </p>
            <p>
              <b-icon-signpost-fill />
              Podrás participar y crear foros de discusión, inclusive de forma
              anonima
            </p>
          </b-card-text>
        </b-card-body>
      </b-card>
    </center>
  </div>
</template>

<script>
export default {
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
#home-page,
center {
  height: 40%;
}
.main-card {
  background-color: rgba(0, 0, 0, 0.5);
  margin-inline: 10vw;
  margin-block: 10vw;
}
p {
  font-size: 20px;
}
.display-4 {
  font-size: 2.4em;
}
</style>

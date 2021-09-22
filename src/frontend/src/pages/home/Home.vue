<template>
  <div id="home-page">
    <center>
      <b-card class="main-card text-light">
        <b-card-title class="display-4">
          UFriends
          <b-icon-people-fill />
        </b-card-title>
        <b-card-body>
          <b-card-text>
            <p>Fecha del servidor: {{ date_server }}</p>
            <p>{{ userName }}</p>
            <p>
              Una aplicación en la que podrás conocer amigos de tu universidad
            </p>
            <p>
              Nuestro algoritmo te emparejara con personas con gustos similares
              a los tuyos
            </p>
            <p>
              Nuestros mentores y tutores podrán ayudarte con las asignaciones
              de los cursos
            </p>
            <p>
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

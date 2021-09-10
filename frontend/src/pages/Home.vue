<template>
  <div id="home-page">
    <center>
      <b-card class="main-card text-light">
        <b-card-title class="h2">
          UFriends
          <b-icon-people-fill />
        </b-card-title>
        <b-card-body>
          <b-card-text>
            <p>
              Una aplicación en la que podrás conocer amigos de tu universidad
            </p>
            <p>
              Nuestro algoritmo te emparejara con personas con gustos similares
              a los tuyos
            </p>
            <p>
              Nuestros mentores y tutores podrán ayudarte con asignaciones de
              los cursos
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
export default {};
</script>

<style scoped>
center {
  margin: 5vw;
  margin-right: 10vw;
  margin-left: 10vw;
}
p {
  font-size: 22px;
}
.main-card {
  background-color: rgba(0, 0, 0, 0.5);
  min-height: 30vw;
}
</style>

<template>
  <div id="home-page">
    <center>
      <b-card class="main-card text-light">
        <b-card-title class="h2">
          Mentores
          <b-icon-people-fill />
          <b-icon-list-stars />
        </b-card-title>
        <b-card-body>
          <b-card-text>
            <p>
              Aqui podras ver los tutores o mentores asi como calificaciones o
              opiniones
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

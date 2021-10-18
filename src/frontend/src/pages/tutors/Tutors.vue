<template>
  <div id="tutors-page">
    <center>
      <b-card class="main-card text-light">
        <b-card-title class="h2">
          Tutores
          <b-icon-people-fill />
          <b-icon-list-stars />
        </b-card-title>
        <b-card-body>
          <b-card-text>
            <p>
              Aqui podras ver los tutores asi como las calificaciones o
              opiniones de otros estudiantes
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
#tutors-page,
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
</style>

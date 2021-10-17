<template>
  <div id="admin-page">
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
export default {};
</script>

<style scoped>
#admin-page,
center {
  height: 40%;
}
.main-card {
  margin-inline: 10vw;
  margin-block: 8vw;
  background-color: rgba(0, 0, 0, 0.5);
}
p {
  font-size: 20px;
}
.display-4 {
  font-size: 2.4em;
}
</style>

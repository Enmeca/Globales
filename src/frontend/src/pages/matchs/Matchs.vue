<template>
  <div id="matchs-page">
    <center>
      <b-card class="main-card text-light">
        <b-card-title class="h2">
          Matchs
          <b-icon-bell-fill />
        </b-card-title>
        <b-card-body>
          <b-card-text>
            <p>
              Desde aqui podras ver a otras personas y decidir si haces match
            </p>
            <p>Tambien podras ver quien ha hecho match contigo</p>
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
#matchs-page,
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

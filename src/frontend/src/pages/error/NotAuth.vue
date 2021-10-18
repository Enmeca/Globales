<template>
  <div d="not-auth-page">
    <center>
      <b-card class="main-card">
        <b-card-text>
          <h3 class="text-light mb-4">
            <b-icon-exclamation-diamond-fill variant="light" />
            Acceso denegado
          </h3>
          <b-button variant="light" href="./">
            Regrese a la pagina principal
            <b-icon-house-fill class="text-dark" />
          </b-button>
        </b-card-text>
      </b-card>
    </center>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#not-auth-page,
center {
  height: 40%;
}
.main-card {
  background-color: rgba(255, 0, 0, 0.3);
  margin-inline: 10vw;
  margin-block: 10vw;
}
</style>
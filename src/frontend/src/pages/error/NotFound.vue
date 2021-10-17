<template>
  <div id="error-page">
    <center>
      <b-card class="main-card">
        <b-card-text>
          <h3 class="text-light mb-4">
            <b-icon-exclamation-diamond-fill variant="light" />
            Dirección invalida
          </h3>
          <b-button variant="light" href="./">
            Regresar a la pagina principal
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
#error-page,
center {
  height: 40%;
}
.main-card {
  background-color: rgba(255, 0, 0, 0.3);
  margin-inline: 10vw;
  margin-block: 10vw;
}
</style>
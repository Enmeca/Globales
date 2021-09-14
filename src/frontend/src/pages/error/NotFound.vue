<template>
  <center>
    <b-card class="card-opacity">
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
</template>

<script>
export default {};
</script>

<style scoped>
center {
  margin: 5vw;
  margin-inline: 15vw;
}
.card-opacity {
  background-color: rgba(255, 0, 0, 0.3);
}
</style>
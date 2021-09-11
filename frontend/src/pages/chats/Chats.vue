<template>
  <center>
    <b-card class="main-card text-light">
      <b-card-title class="h2">
        Chats
        <b-icon-chat-dots-fill />
      </b-card-title>
      <b-card-body>
        <b-card-text>
          <p>
            Desde aqui podras chatear con personas con las que hayas hecho match
          </p>
        </b-card-text>
      </b-card-body>
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
.main-card {
  background-color: rgba(0, 0, 0, 0.5);
}
p {
  font-size: 20px;
}
</style>
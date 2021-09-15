<template>
  <div id="home-page">
    <center>
      <b-card class="main-card text-light">
        <b-card-body>
          <b-card-title class="display-4">
            <b-icon-person-fill />
          </b-card-title>
          <b-input-group class="mb-2 input">
            <b-input-group-prepend is-text>
              <b-icon-envelope></b-icon-envelope>
            </b-input-group-prepend>
            <b-form-input
              type="text"
              placeholder="Correo"
              v-model="user.email"
            ></b-form-input>
          </b-input-group>

          <b-input-group class="mb-2 input">
            <b-input-group-prepend is-text>
              <b-icon-lock-fill></b-icon-lock-fill>
            </b-input-group-prepend>
            <b-form-input
              type="password"
              placeholder="Contraseña"
              v-model="user.password"
            ></b-form-input>
          </b-input-group>
          <b-button variant="info" class="m-3" href="#/signup"
            >Registrarse</b-button
          >
          <b-button variant="info" @click="login">Iniciar sesión</b-button>
        </b-card-body>
      </b-card>
    </center>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        email: "",
        password: "",
      },
    };
  },
  methods: {
    async login() {
      alert(`Email: ${this.user.email}, Password: ${this.user.password}`);
      /*const response = await fetch('api/v1/users/authenticate', {
        method: 'POST',
        headers: { "Content-Type": "application/json"},
        body: JSON.stringify(this.user)
      });
      if(response.status == 200) {
        this.user = response.body;
      }else{
        alert("Credenciales invalidos");
      }
      */
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
.input {
  max-width: 300px;
}
</style>
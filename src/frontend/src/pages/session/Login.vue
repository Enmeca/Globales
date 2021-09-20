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
              placeholder="Cedula o Correo"
              v-model="user.id"
            >
            </b-form-input>
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
          <b-button variant="info" @click="login" :disabled="!validForm"
            >Iniciar sesión</b-button
          >
          <b-alert
            :show="invalidCredentials"
            variant="danger"
            fade
            dismissible
            @dismissed="cleanData"
          >
            <h4 class="alert-heading">Credenciales erroneos!</h4>
            <p>Inténtalo nuevamente.</p>
          </b-alert>
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
        id: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  computed: {
    validForm() {
      return this.user.id != "" && this.user.password != "";
    },
  },
  methods: {
    async login() {
      const response = await fetch("api/v1/user/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.user),
      });
      if (response.status == 200) {
        this.$store.commit("saveUser", await response.json());
        this.$router.push({ path: "/profile" }); // redirifiendo a la pagina de perfil
      } else {
        this.invalidCredentials = true;
      }
    },
    cleanData() {
      this.invalidCredentials = false;
      this.user.id = "";
      this.user.password = "";
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
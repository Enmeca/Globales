<template>
  <div id="login-page">
    <center>
      <b-overlay :show="status == 'Loading'" variant="dark">
        <b-card class="main-card text-light" v-on:keyup.enter="login">
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
            <b-button variant="info" class="m-3" href="#/signup" pill
              >Registrarse</b-button
            >
            <b-button variant="info" @click="login" :disabled="!validForm" pill
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
            <b-alert
              :show="userNotActive"
              variant="danger"
              fade
              dismissible
              @dismissed="cleanData"
            >
              <h4 class="alert-heading">
                Cuenta inactiva
                <b-icon-shield-fill-exclamation />
              </h4>
              <p>
                Su cuenta se encuentra inactiva, por que ha infringido alguna de
                nuestras reglas. <br />Consulte con un administrador para
                reactivarla.
              </p>
            </b-alert>
          </b-card-body>
        </b-card>
        <template #overlay>
          <div class="text-center text-white">
            <b-icon icon="stopwatch" font-scale="3" animation="cylon"></b-icon>
            <p id="cancel-label">Por favor espere...</p>
          </div>
        </template>
      </b-overlay>
    </center>
  </div>
</template>

<script>
export default {
  data() {
    return {
      status: "Ready",
      user: {
        id: "",
        password: "",
      },
      invalidCredentials: false,
      userNotActive: false,
    };
  },
  computed: {
    validForm() {
      return this.user.id != "" && this.user.password != "";
    },
  },
  methods: {
    async login() {
      if (!this.validForm) return;
      this.status = "Loading";
      const response = await fetch("api/v1/user/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.user),
      });
      this.status = "Ready";
      this.responseStatus = response.status;
      if (response.status == 200) {
        let user = await response.json();
        if (user.isActive) {
          this.$store.commit("saveUser", user);
          this.$router.push({ path: "/home" }); // redirifiendo a la pagina principal
        } else {
          this.userNotActive = true;
        }
      } else {
        this.invalidCredentials = true;
      }
    },
    cleanData() {
      this.invalidCredentials = false;
      this.userNotActive = false;
      this.user.id = "";
      this.user.password = "";
    },
  },
};
</script>

<style scoped>
#login-page,
center {
  height: 40%;
}
.main-card {
  background-color: rgba(0, 0, 0, 0.5);
  margin-inline: 10vw;
  margin-block: 7vw;
}
.input {
  max-width: 300px;
  border-radius: 300px !important;
}
</style>
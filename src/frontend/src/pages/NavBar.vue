<template>
  <b-navbar
    toggleable="lg"
    type="dark"
    class="bg-navbar container-fluid p-0 navbar-background"
    sticky
  >
    <b-navbar-brand class="ml-3 mr-5" href="#/home">
      <div class="display-4">
        UFriends
        <b-icon-people-fill />
      </div>
    </b-navbar-brand>
    <b-navbar-toggle target="nav-collapse">
      <b-icon-chevron-compact-up v-if="expanded"></b-icon-chevron-compact-up>
      <b-icon-chevron-compact-down v-else></b-icon-chevron-compact-down>
    </b-navbar-toggle>
    <!-- Opciones de rutas -->
    <b-collapse
      id="nav-collapse"
      is-nav
      appear
      @show="expanded = true"
      @hidden="expanded = false"
      class="text-left"
    >
      <b-navbar-nav class="mx-auto h5">
        <b-nav-item class="mr-5" href="#/home" title="Pagina principal">
          <b-icon-house-fill />
          <template v-if="expanded">Pagina principal</template>
        </b-nav-item>
        <b-nav-item
          v-if="!isLoggedIn"
          class="mr-5"
          href="#/login"
          title="Iniciar Sesión"
        >
          <b-icon-person-fill />
          <template v-if="expanded">Iniciar Sesión </template>
        </b-nav-item>

        <b-nav-item
          v-if="!isLoggedIn"
          class="mr-5"
          href="#/signup"
          title="Registrarse"
        >
          <b-icon-person-plus-fill />
          <template v-if="expanded">Registrarse</template>
        </b-nav-item>
        <b-nav-item
          v-if="isLoggedIn"
          class="mr-5"
          href="#/matchs"
          title="Matchs"
        >
          <b-icon-bell-fill />
          <span v-if="cantMatchs > 0">{{ cantMatchs }} </span>
          <template v-if="expanded">Matchs</template>
        </b-nav-item>
        <b-nav-item v-if="isLoggedIn" class="mr-5" href="#/chats" title="Chats">
          <b-icon-chat-dots-fill />
          <span v-if="cantNotReaded > 0">
            {{ cantNotReaded }}
          </span>
          <template v-if="expanded">Chats</template>
        </b-nav-item>
        <b-nav-item
          v-if="isLoggedIn"
          class="mr-5"
          href="#/forums"
          title="Foros"
        >
          <b-icon-signpost-fill />
          <span v-if="cantForums > 0"> {{ cantForums }} </span>
          <template v-if="expanded">Foros</template>
        </b-nav-item>
        <b-nav-item
          v-if="isLoggedIn"
          class="mr-5"
          href="#/tutors"
          title="Tutores"
        >
          <b-icon-people-fill />
          <b-icon-list-stars />
          <template v-if="expanded">Tutores</template>
        </b-nav-item>

        <b-nav-item
          v-if="isLoggedInAdmin"
          class="mr-5"
          href="#/administrator"
          title="Administrador"
        >
          <b-icon-gear />
          <template v-if="expanded">Administrador</template>
        </b-nav-item>
      </b-navbar-nav>

      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown right v-if="isLoggedIn">
          <template #button-content>
            <b-avatar
              variant="secondary"
              :text="userAbbreviatedName"
              :src="profile_pic"
            ></b-avatar>
          </template>
          <b-dropdown-item href="#/profile">
            <b-icon-person-fill variant="info" /> Ver/Editar
            Perfil</b-dropdown-item
          >
          <b-dropdown-item @click="logout">
            <b-icon-box-arrow-up-right variant="danger" /> Cerrar
            Sesión</b-dropdown-item
          >
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>

<script>
export default {
  data() {
    // fetch data from session or backend

    return {
      expanded: false,
      cantForums: 0,
      cantMatchs: 0,
    };
  },
  methods: {
    logout() {
      /// Actualizar la ultima hora de conexión
      fetch("api/v1/user/updateLC/" + this.$store.state.user.id, {
        method: "PUT",
      });
      this.$store.commit("removeUser");
      this.$router.push({ path: "/login" }); // redirifiendo a la pagina de login
    },
  },
  computed: {
    userAbbreviatedName() {
      return (
        this.$store.state.user.name[0] + this.$store.state.user.lastName1[0]
      );
    },
    isAdmin() {
      return this.$store.user.isAdmin;
    },
    isLoggedIn() {
      return this.$store.getters.isLoggedIn;
    },
    isLoggedInAdmin() {
      return this.$store.getters.isLoggedInAdmin;
    },
    profile_pic() {
      let idUser = this.$store.getters.isLoggedIn
        ? this.$store.state.user.id
        : "";
      return "http://localhost:9191/api/v1/userPhoto/photo/" + idUser;
    },
    cantNotReaded() {
      let total = 0;
      this.$store.state.chats.forEach((chat) => {
        total += chat.messages.filter(
          (message) =>
            message.userUid.id !== this.$store.state.user.id && !message.wasRead
        ).length;
      });
      return total;
    },
  },
};
</script>


<style scoped>
.display-4 {
  font-size: 1.5em;
}
.navbar-background {
  background-color: rgba(0, 0, 0, 0.75);
}
</style>

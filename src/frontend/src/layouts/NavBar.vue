<template>
  <b-navbar
    toggleable="sm"
    type="dark"
    class="bg-navbar container-fluid p-0 navbar-background"
    sticky
  >
    <b-navbar-brand class="ml-3 mr-3" href="#/home">
      <div class="display-4">
        UFriends
        <b-icon-people-fill />
      </div>
    </b-navbar-brand>
    <b-navbar-toggle target="nav-collapse">
      <template #default="{ expanded }">
        <b-icon-chevron-compact-up v-if="expanded"></b-icon-chevron-compact-up>
        <b-icon-chevron-compact-down v-else></b-icon-chevron-compact-down>
      </template>
    </b-navbar-toggle>

    <!-- Opciones de rutas -->
    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav class="ml-auto h5">
        <b-nav-item class="mr-3" href="#/home" title="Pagina principal">
          <b-icon-house-fill />
        </b-nav-item>
        <b-nav-item class="mr-3" href="#/login" title="Iniciar Sesión">
          <b-icon-person-fill />
        </b-nav-item>
        <b-nav-item class="mr-3" href="#/signup" title="Registrarse">
          <b-icon-person-plus-fill />
        </b-nav-item>
        <b-nav-item class="mr-3" href="#/matchs" title="Matchs">
          <b-icon-bell-fill />
          <span> {{ cantMatchs }} </span>
        </b-nav-item>
        <b-nav-item class="mr-3" href="#/chats" title="Chats">
          <b-icon-chat-dots-fill />
          <span> {{ cantChats }} </span>
        </b-nav-item>
        <b-nav-item class="mr-3" href="#/forums" title="Foros">
          <b-icon-signpost-fill />
          <span> {{ cantForums }} </span>
        </b-nav-item>
        <b-nav-item class="mr-3" href="#/mentors" title="Mentores">
          <b-icon-people-fill />
          <b-icon-list-stars />
        </b-nav-item>
      </b-navbar-nav>

      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown right>
          <template #button-content>
            <b-avatar
              variant="primary"
              :src="user_photo.profile_pic"
              :text="abbreviatedName"
            ></b-avatar>
          </template>
          <b-dropdown-item href="#/profile">Perfil</b-dropdown-item>
          <b-dropdown-item href="#/logout">Cerrar Sesión</b-dropdown-item>
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
      user: {
        name: "David",
        lastname: "Villalobos",
      },
      user_photo: {
        profile_pic: "https://source.unsplash.com/150x150/?icon",
      },
      cantChats: 3,
      cantMatchs: 2,
      cantForums: 9,
    };
  },
  computed: {
    abbreviatedName() {
      return this.user.name[0] + this.user.lastname[0];
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
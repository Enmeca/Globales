<template>
  <div id="home-page">
    <center>
      <b-card class="main-card text-light">
        <b-card-body>
          <b-card-title class="display-3">
            <b-icon-person-fill />
          </b-card-title>

          <b-row>
            <b-col sm="12" lg="6">
              <b-input-group class="mb-2 input">
                <b-input-group-prepend is-text> </b-input-group-prepend>
                <b-form-input
                  type="text"
                  placeholder="Cedula"
                  v-model="user.id"
                ></b-form-input>
              </b-input-group>
            </b-col>

            <b-col sm="12" lg="6">
              <b-input-group class="mb-2 input">
                <b-input-group-prepend is-text> </b-input-group-prepend>
                <b-form-input
                  type="text"
                  placeholder="Nombre"
                  v-model="user.name"
                ></b-form-input>
              </b-input-group>
            </b-col>

            <b-col sm="12" lg="6">
              <b-input-group class="mb-2 input">
                <b-input-group-prepend is-text> </b-input-group-prepend>
                <b-form-input
                  type="text"
                  placeholder="Apellido 1"
                  v-model="user.lastname_first"
                ></b-form-input>
              </b-input-group>
            </b-col>

            <b-col sm="12" lg="6">
              <b-input-group class="mb-2 input">
                <b-input-group-prepend is-text> </b-input-group-prepend>
                <b-form-input
                  type="text"
                  placeholder="Apellido 2"
                  v-model="user.lastname_second"
                ></b-form-input>
              </b-input-group>
            </b-col>

            <b-col sm="12" lg="6">
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
            </b-col>

            <b-col sm="12" lg="6">
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
            </b-col>

            <b-col sm="12" lg="6">
              <b-input-group class="mb-2 input">
                <b-input-group-prepend is-text>
                  <b-icon-building></b-icon-building>
                </b-input-group-prepend>
                <b-form-select
                  v-model="user.university"
                  :options="universities"
                >
                  <template #first>
                    <option disabled value="">Elige tu universidad</option>
                  </template>
                </b-form-select>
              </b-input-group>
            </b-col>

            <b-col sm="12" lg="6">
              <b-input-group class="mb-2 input">
                <b-input-group-prepend is-text>
                  <b-icon-bookmark-check-fill></b-icon-bookmark-check-fill>
                </b-input-group-prepend>
                <b-form-select v-model="user.career" :options="careers">
                  <template #first>
                    <option disabled value="">Elige tu carrera</option>
                  </template>
                </b-form-select>
              </b-input-group>
            </b-col>

            <b-col sm="12" lg="6" align-self="stretch">
              <b-input-group class="mb-2 input">
                <b-form-tags
                  id="tags-with-dropdown"
                  v-model="user.tags"
                  no-outer-focus
                  class="mb-2"
                >
                  <template v-slot="{ tags, disabled, addTag, removeTag }">
                    <ul
                      v-if="tags.length > 0"
                      class="list-inline d-inline-block mb-2"
                    >
                      <li
                        v-for="tag in tags"
                        :key="tag"
                        class="list-inline-item"
                      >
                        <b-form-tag
                          @remove="removeTag(tag)"
                          :title="tag"
                          :disabled="disabled"
                          variant="info"
                          >{{ tag }}</b-form-tag
                        >
                      </li>
                    </ul>

                    <b-dropdown
                      size="sm"
                      variant="secondary"
                      no-flip
                      block
                      menu-class="w-100"
                    >
                      <template #button-content>
                        <b-icon icon="tag-fill"></b-icon> Elige tus etiquetas
                      </template>
                      <b-dropdown-form @submit.stop.prevent="() => {}">
                        <b-form-group
                          label="Buscar etiquetas"
                          label-for="tag-search-input"
                          label-cols-md="auto"
                          class="mb-0"
                          label-size="sm"
                          :description="searchDesc"
                          :disabled="disabled"
                        >
                          <b-form-input
                            v-model="searchTag"
                            id="tag-search-input"
                            type="search"
                            size="sm"
                            autocomplete="off"
                          ></b-form-input>
                        </b-form-group>
                      </b-dropdown-form>
                      <b-dropdown-divider></b-dropdown-divider>
                      <b-dropdown-item-button
                        v-for="option in availableOptions"
                        :key="option"
                        @click="onOptionClick({ option, addTag })"
                      >
                        {{ option }}
                      </b-dropdown-item-button>
                      <b-dropdown-text v-if="availableOptions.length === 0">
                        No hay etiquetas disponibles para seleccionar
                      </b-dropdown-text>
                    </b-dropdown>
                  </template>
                </b-form-tags>
              </b-input-group>
            </b-col>

            <b-col sm="12" lg="6" align-self="baseline">
              <b-input-group class="mb-2 input">
                <b-form-datepicker
                  v-model="user.birthday"
                  locale="es"
                  placeholder="Fecha Nacimiento"
                ></b-form-datepicker>
              </b-input-group>
            </b-col>

            <b-col sm="12" lg="12">
              <b-button variant="info" class="m-3" href="./signup" size="lg"
                >Registrarse</b-button
              >
            </b-col>
          </b-row>
        </b-card-body>
      </b-card>
    </center>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchTag: "",
      tags: [
        "Python",
        "Deportes",
        "Videojuegos",
        "Fotografia",
        "Cine",
        "Teatro",
        "Tecnologia",
        "Naturaleza",
        "Futbol",
        "Basketbol",
      ],
      careers: [
        "Ingenieria de Sistemas",
        "Administracion de Empresas",
        "Mecatronica",
        "Biologia",
        "Topografia",
      ],
      universities: [
        "Universidad de Costa Rica",
        "Universidad Nacional de Costa Rica",
        "Instituto Tecnologico de Costa Rica",
        "Universidad Estatal a Distancia",
      ],
      user: {
        id: "",
        name: "",
        lastname_first: "",
        lastname_second: "",
        birthday: "",
        email: "",
        password: "",
        career: "",
        university: "",
        description: "",
        tags: [],
      },
    };
  },
  mounted() {
    /*fetch("/api/v1/tags")
      .then((response) => response.text())
      .then((data) => {
        this.tags = data;
      });
    fetch("/api/v1/universities")
      .then((response) => response.text())
      .then((data) => {
        this.universities = data;
      });
    fetch("/api/v1/user")
      .then((response) => response.text())
      .then((data) => {
        this.user = data;
      });*/
  },
  computed: {
    availableOptions() {
      const criteria = this.searchTag.trim().toLowerCase();
      const options = this.tags.filter(
        (opt) => this.user.tags.indexOf(opt) === -1
      );
      if (criteria) {
        return options.filter(
          (opt) => opt.toLowerCase().indexOf(criteria) > -1
        );
      }
      return options;
    },
    searchDesc() {
      if (this.criteria && this.availableOptions.length === 0) {
        return "No hay etiquetas que coincidan con los criterios de búsqueda.";
      }
      return "";
    },
  },
  methods: {
    async login() {
      //data is bind in v-model in inputs
      alert(`Email: ${this.user.email}, Password: ${this.user.password}`);
      /*const response = await fetch('http://localhost:9191/api/v1/users/authenticate', {
        method: 'POST',
        headers: { "Content-Type": "application/json"},
        body: JSON.stringify(this.user)
      });*/
    },
    onOptionClick({ option, addTag }) {
      addTag(option);
      this.searchTag = "";
    },
  },
};
</script>

<style scoped>
center {
  margin-top: 2vw;
  margin-inline: 10vw;
}
.main-card {
  background-color: rgba(0, 0, 0, 0.5);
}
.input {
  max-width: 350px;
}
</style>
<template>
  <div id="profile-page">
    <center>
      <b-alert :show="registerSuccess" variant="success" fade dismissible>
        <h5>
          Perfil actualizado exitosamente!
          <b-icon-check-circle-fill scale="1" />
        </h5>
      </b-alert>
      <b-alert :show="registerError" variant="danger" fade dismissible>
        <h5>
          Ha ocurrido un error al actualizar el perfil.
          <b-icon-exclamation-octagon-fill scale="1.2" />
        </h5>
      </b-alert>
      <b-overlay :show="status == 'Loading'" variant="dark">
        <b-card class="main-card text-light">
          <b-card-body>
            <b-card-title class="display-3">
              <b-img
                v-if="this.urlProfile"
                id="preview-photo"
                :src="this.urlProfile"
                thumbnail
                fluid
                rounded
                alt="Profile image"
              ></b-img>
              <b-icon-person-fill v-else />
            </b-card-title>
            <b-row>
              <b-col sm="12" lg="6">
                <b-input-group class="mb-2 input">
                  <b-input-group-prepend is-text> </b-input-group-prepend>
                  <b-form-input
                    type="text"
                    placeholder="Cedula"
                    v-model="user.id"
                    :readonly="true"
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
                    v-model="user.lastName1"
                    :readonly="true"
                  ></b-form-input>
                </b-input-group>
              </b-col>

              <b-col sm="12" lg="6">
                <b-input-group class="mb-2 input">
                  <b-input-group-prepend is-text> </b-input-group-prepend>
                  <b-form-input
                    type="text"
                    placeholder="Apellido 2"
                    v-model="user.lastName2"
                    :readonly="true"
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
                    :readonly="true"
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
                    v-if="!user.isTutor"
                    v-model="user.universityId"
                    :options="universities"
                  >
                    <template #first>
                      <option disabled value="">Cambia tu universidad</option>
                    </template>
                  </b-form-select>
                  <b-form-select
                    v-else
                    v-model="user.universityId"
                    :options="
                      universities.filter((x) => x.value === user.universityId)
                    "
                  >
                  </b-form-select>
                </b-input-group>
              </b-col>

              <b-col sm="12" lg="6">
                <b-input-group class="mb-2 input">
                  <b-input-group-prepend is-text>
                    <b-icon-journal-bookmark-fill></b-icon-journal-bookmark-fill>
                  </b-input-group-prepend>
                  <b-form-select
                    v-if="!user.isTutor"
                    v-model="user.careerId"
                    :options="careers"
                  >
                    <template #first>
                      <option disabled value="">Elige tu carrera</option>
                    </template>
                  </b-form-select>
                  <b-form-select
                    v-else
                    v-model="user.careerId"
                    :options="careers.filter((x) => x.value === user.careerId)"
                  >
                  </b-form-select>
                </b-input-group>
              </b-col>

              <b-col sm="12" lg="6" align-self="center">
                <b-input-group class="mb-2 input">
                  <b-input-group-prepend is-text>
                    <b-icon-image></b-icon-image>
                  </b-input-group-prepend>
                  <b-form-file
                    accept="image/*"
                    v-model="images"
                    :state="Boolean(images)"
                    placeholder="Elige una foto de perfil"
                    browse-text=" "
                    @change="updateImage"
                  ></b-form-file>
                </b-input-group>
              </b-col>

              <b-col sm="12" lg="6" class="mb-2" align-self="center">
                <b-input-group class="mb-2 input">
                  <b-form-datepicker
                    v-model="user.dateOfBirth"
                    locale="es"
                    placeholder="Fecha Nacimiento"
                    :readonly="true"
                  ></b-form-datepicker>
                </b-input-group>
              </b-col>

              <b-col sm="12" lg="6">
                <b-input-group class="mb-2 input">
                  <b-input-group-prepend is-text> </b-input-group-prepend>
                  <b-form-input
                    type="text"
                    placeholder="Descripcion"
                    v-model="user.description"
                  ></b-form-input>
                </b-input-group>
              </b-col>

              <b-col sm="12" lg="6" align-self="center">
                <b-input-group class="mb-2 input">
                  <b-form-tags
                    id="tags-with-dropdown"
                    v-model="user_tags"
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

              <b-col cols="12">
                <b-col cols="6" class="bg-light text-dark">
                  (temp) Ultima hora de conexion: {{ user.lastConnected }}
                </b-col>
              </b-col>
              <b-col sm="12" lg="12">
                <b-button
                  variant="info"
                  class="mt-2"
                  @click="UpdateUser"
                  size="lg"
                  pill
                  >Actualizar</b-button
                >
              </b-col>
            </b-row>
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
      registerError: false,
      registerSuccess: false,
      searchTag: "",
      tags: [],
      careers: [],
      universities: [],
      images: [],
      urlProfile: null, //"https://source.unsplash.com/150x150/?icon",
      user: this.$store.state.user,
      user_tags: [],
      user_photo: {
        user_uid: "",
        profile_pic: "",
      },
    };
  },
  mounted() {
    fetch("/api/v1/tag")
      .then((response) => response.json())
      .then((data) => {
        this.tags = data.map((tag) => tag.name);
      });
    fetch("/api/v1/university")
      .then((response) => response.json())
      .then((data) => {
        this.universities = data.map((university) => ({
          value: university.id,
          text: university.name,
        }));
      });
    fetch("/api/v1/career")
      .then((response) => response.json())
      .then((data) => {
        this.careers = data.map((career) => ({
          value: career.id,
          text: career.name,
        }));
      });
    fetch("api/v1/userTags/user/" + this.user.id)
      .then((response) => response.json())
      .then((data) => {
        this.user_tags = data.map((tag) => tag.tag.name);
        this.currentTags = [...this.user_tags];
      });
  },
  computed: {
    availableOptions() {
      const criteria = this.searchTag.trim().toLowerCase();
      const options = this.tags.filter(
        (opt) => this.user_tags.indexOf(opt) === -1
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
    async UpdateUser() {
      let myTags = this.user_tags.map((tag) => ({
        user: { id: this.user.id },
        tag: { name: tag },
      }));
      this.status = "Loading";
      const response = await fetch("api/v1/user", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.user),
      });
      this.status = "Ready";
      if (response.status == 200) {
        this.$store.commit("saveUser", await response.json());
        this.user = this.$store.state.user;
        this.registerSuccess = true;
        let addtetags = this.currentTags
          .filter((x) => this.user_tags.find((y) => y === x))
          .map((tag) => ({
            user: { id: this.user.id },
            tag: { name: tag },
          }));

        if (addtetags.length > 0) {
          fetch("api/v1/userTags/multiple", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(myTags),
          });

          let deletetags = this.currentTags
            .filter((x) => !this.user_tags.find((y) => y === x))
            .map((tag) => ({
              user: { id: this.user.id },
              tag: { name: tag },
            }));

          if (deletetags.length > 0) {
            fetch("api/v1/userTags/multiple", {
              method: "DELETE",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(deletetags),
            });
          }

          setTimeout(() => this.$router.go(0), 3000);
        }
      } else {
        this.registerError = true;
      }
    },
    onOptionClick({ option, addTag }) {
      addTag(option);
      this.searchTag = "";
    },
    updateImage(e) {
      this.urlProfile = URL.createObjectURL(e.target.files[0]);
    },
  },
};
</script>

<style scoped>
#profile-page,
center {
  height: 50%;
}
.main-card {
  background-color: rgba(0, 0, 0, 0.5);
  margin-inline: 10vw;
}
.input {
  max-width: 350px;
}
#preview-photo {
  max-height: 100px;
}
</style>

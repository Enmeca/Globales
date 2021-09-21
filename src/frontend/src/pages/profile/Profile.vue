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
                :readonly="true"></b-form-input>
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
                  v-model="user.lastname_1"
                readonly="true"></b-form-input>
              </b-input-group>
            </b-col>

            <b-col sm="12" lg="6">
              <b-input-group class="mb-2 input">
                <b-input-group-prepend is-text> </b-input-group-prepend>
                <b-form-input
                  type="text"
                  placeholder="Apellido 2"
                  v-model="user.lastname_2"
                readonly="true"></b-form-input>
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
                :readonly="true"></b-form-input>
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
                <div v-if="user.is_tutor===false"> 
                <b-form-select
                  v-model="user.university_id"
                  :options="universities">
                  <template #first>
                    <option disabled value="">Cambia tu universidad</option>
                  </template>
                </b-form-select>
                </div>
                <div v-else>
                  <b-form-select
                  v-model="user.university_id"
                  :options="universities.filter(x=>x.value===user.university_id)" >
                </b-form-select>
                </div>
              </b-input-group>
            </b-col>

            <b-col sm="12" lg="6">
              <b-input-group class="mb-2 input">
                <b-input-group-prepend is-text>
                  <b-icon-journal-bookmark-fill></b-icon-journal-bookmark-fill>
                </b-input-group-prepend>
                <div v-if="user.is_tutor===false">
                  <b-form-select v-model="user.career_id" :options="careers">
                    <template #first>
                      <option disabled value="">Elige tu carrera</option>
                    </template>
                  </b-form-select>
                </div>
                <div v-else>
                  <b-form-select v-model="user.career_id" :options="careers.filter(x=>x.value===user.career_id)">
                  </b-form-select>
                </div>
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

            <b-col
              v-if="this.urlProfile"
              class="mb-2"
              sm="12"
              lg="6"
              align-self="center"
            >
              <b-img
                id="preview-photo"
                :src="this.urlProfile"
                thumbnail
                fluid
                rounded
                alt="Profile image"
              ></b-img>
            </b-col>
            <b-col v-else sm="12" lg="6" align-self="center">
              (Prevista de la foto de perfil)
            </b-col>

            <b-col sm="12" lg="6" class="mb-2" align-self="center">
              <b-input-group class="mb-2 input">
                <b-form-datepicker
                  v-model="user.date_of_birth"
                  locale="es"
                  placeholder="Fecha Nacimiento"
                :readonly="true"></b-form-datepicker>
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

            <b-col sm="12" lg="12">
              <b-button variant="info" class="mt-2" @click="UpdateUser" size="lg"
                >Actualizar</b-button>
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
        { value: "ING-SYS", text: "Ingenieria de Sistemas" },
        { value: "ADM", text: "Administracion de Empresas" },
        { value: "MECTR", text: "Mecatronica" },
        { value: "BIO", text: "Biologia" },
        { value: "TOP", text: "Topografia" },
      ],
      universities: [
        { value: "UCR", text: "Universidad de Costa Rica" },
        { value: "UNA", text: "Universidad Nacional de Costa Rica" },
        { value: "TEC", text: "Instituto Tecnologico de Costa Rica" },
        { value: "UNED", text: "Universidad Estatal a Distancia" },
      ],
      images: [],
      urlProfile: null, //"https://source.unsplash.com/150x150/?icon",
      user: {
        id: "402420750",
        career_id: "ING-SYS",
        university_id: "UNA",
        email: "Braslynrrr999@gmail.com",
        password: "123",
        name: "Braslyn",
        lastname_1: "Rodriguez",
        lastname_2: "Ramirez",
        date_of_birth: "1999-04-22",
        description: "im looking for fuckin whores",
        is_tutor: true,
        is_admin: false,
      },
      user_tags: ["C++","Python","Prolog"],
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
    async UpdateUser(){
        let myTags = this.user_tags.map((tag) => ({
        user: { id: this.user.id },
        tag: { name: tag },}));
        alert(JSON.stringify(myTags))
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
#preview-photo {
  max-height: 100px;
}
</style>

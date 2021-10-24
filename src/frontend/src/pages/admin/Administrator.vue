<template>
  <center class="admin-page">
    <b-card class="admin-card text-light text-center">
      <b-card-body>
        <b-row align-h="center">
          <b-col cols="12" class="mb-2" align-self="center">
            <p class="title">Administrar usuarios y foros</p>
          </b-col>
          <b-col sm="12" lg="6" class="mb-2" align-self="center">
            <b-button-group>
              <b-button @click="changeType">Usuarios</b-button>
              <b-button @click="changeType">Foros</b-button>
            </b-button-group>
          </b-col>
        </b-row>
        <b-row align-h="center" v-if="manageType">
          <b-col sm="12" lg="6" class="mb-2" align-self="center">
            <b-input-group prepend="Identificacion">
              <b-form-input
                type="text"
                placeholder="Cedula"
                v-model="user.id"
              />
              <b-button variant="info" @click="findUser" class="mr-2 ml-2"
                ><b-icon-search
              /></b-button>
              <b-button variant="success" @click="newUser"
                ><b-icon-person-plus-fill />
              </b-button>
            </b-input-group>
          </b-col>

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
                  v-model="user.lastName1"
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
                  v-model="user.universityId"
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
                  <b-icon-journal-bookmark-fill></b-icon-journal-bookmark-fill>
                </b-input-group-prepend>
                <b-form-select v-model="user.careerId" :options="careers">
                  <template #first>
                    <option disabled value="">Elige tu carrera</option>
                  </template>
                </b-form-select>
              </b-input-group>
            </b-col>

            <b-col sm="12" lg="6" class="mb-2" align-self="center">
              <b-input-group class="mb-2 input">
                <b-form-datepicker
                  v-model="user.dateOfBirth"
                  locale="es"
                  :max="new Date()"
                  placeholder="Fecha Nacimiento"
                ></b-form-datepicker>
              </b-input-group>
            </b-col>
            <b-col sm="12" lg="6" class="mb-2" align-self="center">
              <b-input-group>
                <b-input-group-prepend is-text>Admin: </b-input-group-prepend>
                <b-input-group-prepend is-text>
                  <b-form-checkbox v-model="user.isAdmin" switch />
                </b-input-group-prepend>

                <b-input-group-prepend is-text>Tutor: </b-input-group-prepend>
                <b-input-group-prepend is-text>
                  <b-form-checkbox v-model="user.isTutor" switch />
                </b-input-group-prepend>

                <b-input-group-prepend is-text>Activo: </b-input-group-prepend>
                <b-input-group-prepend is-text>
                  <b-form-checkbox v-model="user.isActive" switch />
                </b-input-group-prepend>
              </b-input-group>
            </b-col>
            <b-col sm="12" lg="12" class="mb-2">
              <b-button variant="info" class="mt-2" size="lg" @click="process"
                >Procesar</b-button
              >
            </b-col>
          </b-row>
          <b-alert :show="registerSuccess" variant="success" fade dismissible>
            <h4>
              Usuario {{ user.id }} Actualizado
              <b-icon-emoji-laughing scale="1" />
            </h4>
          </b-alert>
          <b-alert :show="registerError" variant="danger" fade dismissible>
            <h4>
              Ha ocurrido un error, Usuario no Actualizado
              <b-icon-emoji-frown scale="1" />
            </h4>
          </b-alert>
        </b-row>
        <b-row v-else align-h="center">
          <!-- <carousel :per-page="1" :loop="true" :autoplayTimeout="1000">-->
          <b-col
            sm="12"
            md="6"
            lg="4"
            v-for="report in reports"
            :key="report.id"
            class="mt-2"
          >
            <customSlice :data="report" :updateReports="UpdateReports" />
            <!--<slice >-->
          </b-col>
          <b-col v-if="reports.length === 0">
            <b-card class="second-card" :title="`Sin Reportes`">
              <p>No hay casos para administrar</p>
            </b-card>
          </b-col>
        </b-row>
        <!--</slice>-->
        <!-- </carousel>-->
      </b-card-body>
    </b-card>
  </center>
</template>

<script>
//import { Carousel } from "vue-carousel";
import CustomSlice from "./ReportItem.vue";

export default {
  data() {
    return {
      registerError: false,
      registerSuccess: false,
      manageType: true,
      ForumList: [],
      user: {
        id: "",
        careerId: "",
        universityId: "",
        email: "",
        password: "",
        name: "",
        lastName1: "",
        lastName2: "",
        dateOfBirth: "",
        description: "",
        isTutor: false,
        isActive: false,
        isAdmin: false,
        userPhoto: null,
      },
      careers: [],
      universities: [],
      reports: [],
    };
  },
  mounted() {
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
    fetch("/api/v1/commentReports")
      .then((response) => response.json())
      .then((data) => {
        this.reports = data;
      });
  },

  methods: {
    changeType() {
      this.manageType = !this.manageType;
    },
    findUser() {
      fetch(`/api/v1/user/${this.user.id}`)
        .then((response) => response.json())
        .then((data) => {
          this.user = data;
          this.user.isTutor = this.user.isTutor === 1 ? true : false;
          this.user.isAdmin = this.user.isAdmin === 1 ? true : false;
          this.user.isActive = this.user.isActive === 1 ? true : false;
        });
    },
    UpdateReports() {
      fetch("/api/v1/commentReports")
        .then((response) => response.json())
        .then((data) => {
          this.reports = data;
        });
    },
    newUser() {
      this.user = {
        id: "",
        careerId: "",
        universityId: "",
        email: "",
        password: "",
        name: "",
        lastName1: "",
        lastName2: "",
        dateOfBirth: "",
        description: "",
        isTutor: false,
        isAdmin: false,
        isActive: true,
        userPhoto: null,
      };
    },
    async process() {
      let tempUser = { ...this.user };
      tempUser.isTutor = tempUser.isTutor ? 1 : 0;
      tempUser.isAdmin = tempUser.isAdmin ? 1 : 0;
      tempUser.isActive = tempUser.isActive ? 1 : 0;
      const response = await fetch("api/v1/user", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(tempUser),
      });
      if (response.status === 200) this.registerSuccess = true;
      else this.registerError = true;
    },
  },
  components: {
    //Carousel,
    CustomSlice,
  },
};
</script>

<style scoped>
#admin-page,
center {
  height: 50%;
}
.admin-card {
  background-color: rgba(0, 0, 0, 0.5);
  margin-inline: 10vw;
}
.second-card {
  background-color: rgba(255, 255, 255, 0.876);
  color: rgba(0, 0, 0, 0.616);
  font-size: relative;
  font-size-adjust: inherit;
}
p {
  font-size: 20px;
}
</style>
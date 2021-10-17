<template>
  <center>
    <b-card class="main-card text-light">
      <b-card-title class="h2">
        Pagina de administrador
        <b-icon-gear />
      </b-card-title>
      <b-card-body>
        <b-card-text>
          <p>
            Administra usuarios y foros
          </p>
        <b-col sm="12" lg="6" class="mb-2" align-self="center">
            <b-button-group >
            <b-button @click="changeType">Usuarios</b-button>
            <b-button @click="changeType">Foros</b-button>
        </b-button-group>
        </b-col>

        <div v-if="manageType">
        <b-col sm="12" lg="6" class="mb-2" align-self="center">
            <b-input-group prepend="Identificacion">
                <b-form-input
                  type="text"
                  placeholder="Cedula"
                  v-model="user.id"
                />
                <b-button
                variant="info"
                @click="findUser"
                >Buscar</b-button
              
              >
              <b-button
                variant="success"
                @click="newUser"
                >Nuevo</b-button
              >
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
                <b-input-group prepend="Admin">
                    <b-input-group-prepend is-text>
                    <b-form-checkbox 
                          id="checkAdmin"
                            v-model="user.isAdmin"
                            name="checkAdmin"
                         />
                    </b-input-group-prepend>
                    <b-input-group-prepend is-text><b>Tutor:</b>
                    </b-input-group-prepend>
                    <b-input-group-prepend is-text>
                         <b-form-checkbox 
                          id="checktutor"
                            v-model="user.isTutor"
                            name="checktutor"
                         />
                    </b-input-group-prepend>
                </b-input-group>
            </b-col>
            <b-col sm="12" lg="12" class="mb-2">
              <b-button
                variant="info"
                class="mt-2"
                size="lg"
                @click="process"
                >Procesar</b-button
              >
            </b-col>
          </b-row>
        </div>
        <div v-else>
          <carousel   :per-page="4" :loop="true" :autoplayTimeout="1000">
            <customSlice
            v-for="report in reports"
            :key="report.id"
            :data="report"
            />
          </carousel>
        </div>
        </b-card-text>
      </b-card-body>
    </b-card>
  </center>
</template>

<script>
import {Carousel} from 'vue-carousel';
import CustomSlice from './ReportItem.vue'

export default {
    data(){
        return {
            manageType:true,
            ForumList:[],
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
                isAdmin: false,
                userPhoto: null,
            },
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
            reports:[]
            };
    },
    mounted(){
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
      fetch("/api/v1/commentReports").then((response) => response.json())
      .then((data) => {
          this.reports=data
          //alert(JSON.stringify(data))
      });
    }
    
    ,
    methods:{
        changeType(){
            this.manageType=!this.manageType
        },
        findUser(){
            fetch(`/api/v1/user/${this.user.id}`).then((response) => response.json()).then((data) => {
            this.user = data
            this.user.isTutor=this.user.isTutor===1?true:false;
            this.user.isAdmin=this.user.isAdmin===1?true:false;
        });
        },
        newUser(){
            this.user={
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
                userPhoto: null,
            }
        },
        async process(){
            let tempUser={...this.user}
            tempUser.isTutor=tempUser.isTutor?1:0;
            tempUser.isAdmin=tempUser.isAdmin?1:0;
            fetch("api/v1/user", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(tempUser),
            });
            
        }
    },
    components:{
      Carousel,CustomSlice
    }

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
p {
  font-size: 20px;
}
</style>
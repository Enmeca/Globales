<template>
  <div>
    <NavBar />
    <!-- Current page -->
    <router-view />
  </div>
</template>

<script>
// Import components
import NavBar from "./layouts/NavBar.vue";

export default {
  // Specify which components you are using
  components: {
    NavBar,
  },
};
</script>

<style>
body {
  background-image: url("./assets/background.jpeg");
  background-size: cover;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-position: left center;
  margin: 0;
  padding: 0;
  height: 100%;
}
</style>

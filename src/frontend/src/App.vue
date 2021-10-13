<template>
  <div>
    <NavBar />
    <transition enter-active-class="animated fadeInDown">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
import NavBar from "./pages/NavBar.vue";

export default {
  components: {
    NavBar,
  },
};
</script>

<style>
html,
body {
  background-image: url("./assets/background.jpeg");
  background-size: cover;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-position: left center;
  margin: 0;
  padding: 0;
  height: 100%;
  overflow: hidden;
}
center {
  padding-inline: 5vh;
  padding-block: 4vw;
}
</style>

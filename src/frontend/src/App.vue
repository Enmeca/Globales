<template>
  <div class="rootPage">
    <NavBar />
    <transition enter-active-class="animated fadeInDown">
      <router-view class="currentPage"></router-view>
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
}
.rootPage,
.currentPage {
  height: 100%;
}

::-webkit-scrollbar {
  width: 15px;
}
::-webkit-scrollbar-track {
  background: rgb(100, 100, 100);
  border-radius: 10px;
}
::-webkit-scrollbar-thumb {
  background: rgb(180, 180, 180);
  border-radius: 10px;
}
</style>

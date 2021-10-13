<template>
    <slide>
    <b-card class="main-card"
        :title="`Report #${this.data.num}`"
        style="max-width: 20rem;">

        <b-card-text>
            caso {{this.data.id}}
        </b-card-text>

        <b-button href="#" variant="primary">Go to Check</b-button>
    </b-card>
    </slide>
</template>
<script>
import {Slide} from 'vue-carousel';
export default {
  props: {
    data: {
      type: Object,
      required: true,
    },
  },
  computed: {

  },components: {Slide}
};
</script>

<style scoped>
.main-card {
  background-color: rgba(255, 255, 255, 0.76);
 color: rgba(0, 0, 0, 0.616);
}
</style>
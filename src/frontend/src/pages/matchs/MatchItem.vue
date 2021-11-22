<template>
    <b-card class="third-card">
        <b-card-body class="text-left p-0 m-0">
         <h4 class="text-center">{{`${data.name}` }}</h4>
         <b-avatar
            variant="light"
            size="6rem"
            :text="this.data.shortName"
            :src="this.data.userPhoto"
            ></b-avatar>
        </b-card-body>
    </b-card>
</template>
<script>

export default {
 props: {
    data: {
      type: Object,
      required: true,
    }
  }
}
</script>

<style scoped>
#matchs-page,
center {
 height: 40%;
}
.third-card {
  background-color: rgba(255, 255, 255, 0.863) !important;
  color: rgb(83, 76, 76);
  max-width: 8rem;
  min-width: 8rem;
}
p {
  font-size: 20px;
}
</style>
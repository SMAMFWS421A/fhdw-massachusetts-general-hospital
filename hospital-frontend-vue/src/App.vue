
<script>

import HomeView from "@/components/HomeView.vue";
import PatientenListe from "@/components/PatientenListe.vue";
import TerminPlan from "@/components/TerminPlan.vue";
import AerzteTeam from "@/components/AerzteTeam.vue";
export default {
  name: 'App',
  components: {
    HomeView,
    PatientenListe,
    TerminPlan,
    AerzteTeam
  }
}
</script>

<template>
  <router-view>
  </router-view>
</template>

<style>

</style>

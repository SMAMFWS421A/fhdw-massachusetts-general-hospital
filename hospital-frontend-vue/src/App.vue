
<script>

import HomeView from "@/components/HomeView.vue";
import PatientenListe from "@/components/PatientenListe.vue";
import TerminPlan from "@/components/TerminPlan.vue";
import AerzteTeam from "@/components/AerzteTeam.vue";
import ImpressumSeite from "@/components/ImpressumSeite.vue";
import DatenschutzSeite from "@/components/DatenschutzSeite.vue";

export default {
  name: 'App',
  components: {
    HomeView,
    PatientenListe,
    TerminPlan,
    AerzteTeam,
    ImpressumSeite,
    DatenschutzSeite
  }
}
</script>

<template>
  <router-view>
  </router-view>
</template>

<style>

</style>

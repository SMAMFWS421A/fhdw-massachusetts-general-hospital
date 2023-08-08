import { createRouter, createWebHistory } from 'vue-router';
import HomeView from "@/components/HomeView.vue";
import PatientenListe from "@/components/PatientenListe.vue";
import TerminPlan from "@/components/TerminPlan.vue";
import AerzteTeam from "@/components/AerzteTeam.vue";


const routes = [
    {
        path: '/', // Pfad für die Startseite (HomeView)
        component: HomeView
    },
    {
        path: '/Patienten',
        component: PatientenListe
    },
    {
        path: '/Termine',
        component: TerminPlan
    },
    {
        path: '/Aerzte',
        component: AerzteTeam

    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
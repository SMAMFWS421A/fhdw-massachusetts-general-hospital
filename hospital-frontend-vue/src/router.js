import { createRouter, createWebHistory } from 'vue-router';
import HomeView from "@/components/HomeView.vue";
import PatientenListe from "@/components/PatientenListe.vue";
import TerminPlan from "@/components/TerminPlan.vue";
import AerzteTeam from "@/components/AerzteTeam.vue";
import ImpressumSeite from "@/components/ImpressumSeite.vue";
import DatenschutzSeite from "@/components/DatenschutzSeite.vue";


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
    {
        path: '/Impressum',
        component: ImpressumSeite
    },

    {
        path: '/Datenschutz',
        component: DatenschutzSeite
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
import { createRouter, createWebHistory } from "vue-router";

import PizzaIndex from './pages/PizzaIndex.vue';



const router = createRouter({

    history: createWebHistory(),

    routes: [
        {
            path:'/',
            name: 'PizzaIndex',
            component: PizzaIndex,
        },
     

    ]

});

export { router }
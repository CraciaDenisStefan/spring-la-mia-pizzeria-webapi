import { createRouter, createWebHistory } from "vue-router";

import PizzaIndex from './pages/PizzaIndex.vue';
import Pizza from './pages/Pizza.vue';
import NuovaPizza from './pages/NuovaPizza.vue';

const router = createRouter({

    history: createWebHistory(),

    routes: [
        {
            path:'/',
            name: 'PizzaIndex',
            component: PizzaIndex,
        },
        {
            path: '/pizza/:id',
            name: 'Pizza',
            component: Pizza,
        },
        {
            path: '/NuovaPizza',
            name: 'NuovaPizza',
            component: NuovaPizza,
        },
    ]

});

export { router }
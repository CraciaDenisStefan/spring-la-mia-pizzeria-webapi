import { createRouter, createWebHistory } from "vue-router";

import PizzaIndex from './pages/PizzaIndex.vue';
import Pizza from './pages/Pizza.vue';


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

    ]

});

export { router }
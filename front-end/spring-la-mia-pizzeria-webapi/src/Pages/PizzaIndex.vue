<script>
import axios from 'axios';

import PizzaSingola from '../components/PizzaSingola.vue';
export default {
    name: 'PizzaIndex',
    components:{
        PizzaSingola,
    },
    
    data(){
        return{
            pizza: [],


        }
    },
    created() {
    this.getPizza();
  },
    methods: {
      async getPizza() {
      try {
        const response = await axios.get('http://localhost:8080/api/v1.0/pizza');
        this.pizza = response.data;
      } catch (error) {
        console.error('Errore durante la richiesta delle pizze', error);
      }
    },
  },
    
}
</script>
<template lang="">
        <ul>
            <li v-for="pizza in pizza" :key="pizza.id">
                <PizzaSingola :pizza="pizza" />
            </li>
        </ul>
</template>
<style lang="scss">
</style>
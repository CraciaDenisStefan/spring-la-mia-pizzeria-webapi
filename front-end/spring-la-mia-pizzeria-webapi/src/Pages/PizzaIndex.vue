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
            filtroNome: '',

        }
    },
    created() {
    this.getPizza();
  },
    methods: {
      async getPizza() {
      try {
        const response = await axios.get(`http://localhost:8080/api/v1.0/pizza?nome=${this.filtroNome}`);
        this.pizza = response.data;
      } catch (error) {
        console.error('Errore durante la richiesta delle pizze', error);
      }
    },
    async eliminaPizza(idPizza) {
      try {
        await axios.delete(`http://localhost:8080/api/v1.0/pizza/${idPizza}`);
        this.getPizza();
      } catch (error) {
        console.error('Errore durante l\'eliminazione della pizza', error);
      }
    },
  },
    
}
</script>
<template lang="">
  <router-link :to="{ name: 'NuovaPizza' }">Crea Nuova Pizza</router-link><br>
  <input v-model="filtroNome" @keyup.enter="getPizza" placeholder="Filtra per nome" />
        <ul>
            <li class="spazio" v-for="pizza in pizza" :key="pizza.id">
                <PizzaSingola :pizza="pizza" />
                <button @click="eliminaPizza(pizza.id)">Elimina Pizza</button>
            </li>
        </ul>
</template>
<style >
.spazio{
  margin-bottom: 20px;
  cursor: pointer;
}
</style>
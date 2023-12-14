
<script >
import axios from 'axios';

export default {
    name: 'Pizza',  
    data(){ 
        return {
      pizza: {},
    }; 
    },
    created() {
    this.getUnaPizza();
  },
    methods: {
      async getUnaPizza() {
      try {
        const response = await axios.get(`http://localhost:8080/api/v1.0/pizza/${this.$route.params.id}`);
        this.pizza = response.data;
      } catch (error) {
        console.error('Errore durante la richiesta delle pizze', error);
      }
    },
  },
    
}

</script>
<template>
  <div>
    <div class="mt-5">
      <router-link  :to="{name: 'PizzaIndex' }">Torna alla lista </router-link>
     </div>
    <h1>{{ pizza.nome }}</h1>
    <p>{{ pizza.descrizione }}</p>
    <img :src="pizza.fotoUrl" alt="Immagine della pizza" />
    <p>Prezzo: {{ pizza.prezzo }}</p>
  </div>
</template>
<style >

</style>



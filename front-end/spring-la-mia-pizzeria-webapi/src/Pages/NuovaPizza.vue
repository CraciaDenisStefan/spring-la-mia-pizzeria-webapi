<script>
import axios from 'axios';

export default {
  data() {
    return {
      nuovaPizza: {
        nome: '',
        descrizione: '',
        fotoUrl: '',
        prezzo: 0,
      },
    };
  },
  methods: {
    async creaPizza() {
      try {
        await axios.post('http://localhost:8080/api/v1.0/pizza', this.nuovaPizza);
        this.$router.push({ name: 'PizzaIndex' });
      } catch (error) {
        console.error('Errore durante la creazione della pizza', error);
      }
    },
  },
};
</script>

<template>
    <div>
      <h1>Crea Nuova Pizza</h1>
      <form @submit.prevent="creaPizza">
        <label for="nome">Nome:</label>
        <input type="text" v-model="nuovaPizza.nome" required />
  <br>
        <label for="descrizione">Descrizione:</label>
        <textarea v-model="nuovaPizza.descrizione" required></textarea>
  <br>
        <label for="fotoUrl">URL dell'immagine:</label>
        <input type="text" v-model="nuovaPizza.fotoUrl" required />
  <br>
        <label for="prezzo">Prezzo:</label>
        <input type="number" v-model="nuovaPizza.prezzo" required />
  <br>
        <button type="submit">Crea Pizza</button><br>
        <router-link :to="{ name: 'PizzaIndex' }">Torna Indietro</router-link>
      </form>
    </div>
  </template>
  
 
  
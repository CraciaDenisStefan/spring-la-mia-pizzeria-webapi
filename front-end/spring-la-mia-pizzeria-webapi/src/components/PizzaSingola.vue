<script>


export default {
    name: 'PizzaSingola',
    props:{
        pizza: Object
    },
methods: {
    navigateToPizza() {
      this.$router.push({ name: 'Pizza', params: { id: this.pizza.id } });
    },
}
}
</script>
<template lang=""> 
        <div @click="navigateToPizza">{{pizza.nome}}</div>    
</template>
<style lang="">
    
</style>
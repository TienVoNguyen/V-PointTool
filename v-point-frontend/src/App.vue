<template>
  <div id="app">
    <addmark></addmark>
  </div>
</template>

<script>
import addmark from './components/mark/Add.vue'

export default {
  name: 'App',
  components: {
    addmark
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

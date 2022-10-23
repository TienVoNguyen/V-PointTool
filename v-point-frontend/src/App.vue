<template >
  <div id="app">
    <div v-if="loggedIn && currentUser.roles.length === 2">
      <Navbar/>
      <div class="row" style="width: 100%; height: 100%" >
        <div class="col-3 div-1" >
          <Sidebar/>
        </div>
        <div class="col-9">
          <router-view/>
        </div>
      </div>
    </div>
    <div v-if="!loggedIn">
      <router-view/>
    </div>
    <div v-if="loggedIn && currentUser.roles.length === 1">
      <Navbar/>
      <router-view/>
    </div>

  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
.div-1 {
  background-color: #dc3545;
}
</style>
<script>

import Navbar from "@/components/Navbar";
import Sidebar from "@/components/Sidebar";
import login from "@/views/Login";

export default {
  components: {Sidebar, Navbar}, login,
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
}
</script>

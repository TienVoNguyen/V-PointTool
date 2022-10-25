<template >
  <div id="app">
    <div v-if="roleId === 'ROLE_ADMIN' && loggedIn">
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
    <div v-if="roleId === 'ROLE_USER' && loggedIn">
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
  color: #ffffff;
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
  data: function () {
    return {
      roleId: ''
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    currentUser() {
      return this.$store.state.auth.user;
    },

  },

  created() {
    this.getRoleId()
    console.log(this.roleId)
  },

  methods: {
    getRoleId() {
      console.log(this.currentUser)
      for (let i = 0; i < this.currentUser.roles.length; i++) {
        if (this.currentUser.roles[i].authority === 'ROLE_ADMIN') {
           this.roleId = 'ROLE_ADMIN'
        }
        if( this.currentUser.roles[i].authority !== 'ROLE_ADMIN') {
          this.roleId = 'ROLE_USER'
        }
      }
    }
  }
}
</script>

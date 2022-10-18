
<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
          id="profile-img"
          src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
          class="profile-img-card"
      />
      <form name="form" @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="name">Username</label>
          <input
              v-model="user.email"

              type="text"
              class="form-control"
              name="email"
          />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
              v-model="user.password"
              type="password"
              class="form-control"
              name="password"
          />
        </div>
        <div class="form-group">
          <button class="btn btn-primary btn-block">
<!--            <span class="spinner-border spinner-border-sm"></span>-->
            <span>Login</span>
          </button>
        </div>
        <div class="form-group">
          <div v-if="message" class="alert alert-danger" role="alert">{{message}}</div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import User from '../model/user';

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Login',
  data() {
    return {
      user: new User('', ''),
      message: '',
      a: ''
    };
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
    if (this.loggedIn && this.currentUser.roles.length === 2) {
      this.$router.push('/profile');
    }
    if (this.loggedIn && this.currentUser.roles.length === 1) {
      this.$router.push('/');
    }
  },
  methods: {
    handleLogin() {
      this.$store.dispatch('auth/login', this.user).then(
          () => {
            console.log(this.currentUser.roles.length)
            if (this.loggedIn && this.currentUser.roles.length === 2) {
              this.$router.push('/profile');
            }
            if (this.loggedIn && this.currentUser.roles.length === 1) {
              this.$router.push('/');
            }
          },
          error => {
            this.a = (error.response && error.response.data)
            this.message = 'Email hoặc mật khẩu không chính xác'
          }
      );
    }
  }
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>

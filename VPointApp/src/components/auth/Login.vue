<template>
  <div class="login-bg ">
    <div class="row justify-content-center">
      <div class="col-md-7 col-lg-5">
        <div class="wrap">
          <div>
            <img height="200px" v-bind:src="'https://vmgmedia.vn/Content/images/logo/01.png'" />
          </div>
          <div class="login-wrap p-4 p-md-5">
            <div class="d-flex">
              <div class="w-100">
                <h3 class="mb-4 text-uppercase">Đăng nhập hệ thống</h3>
              </div>
            </div>
            <form name="form" @submit.prevent="handleLogin" >
              <small v-if="messageForm" style="color: red">{{messageForm}}</small>
              <div class="form-group mt-3 text-left">
                <label for="email">Email:</label>
                <input v-model="user.email"
                       type="email"
                       class="form-control"
                       name="email"
                       id="email">
                <small v-if="messageEmail" style="color: red">{{messageEmail}}</small>
              </div>

              <div class="form-group mt-3 text-left">
                <label for="password">Password:</label>
                <input v-model="user.password"
                       type="password"
                       class="form-control"
                       name="password"
                       id="password">
                <small v-if="messagePass !== null" style="color: red">{{messagePass}}</small>
              </div>

              <div class="form-group mt-3">
                <button type="submit" class="form-control btn btn-danger rounded submit px-3">Đăng nhập</button>
              </div>
              <small v-if="message" class="alert alert-danger" role="alert">{{message}}</small>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import User from "@/models/user";

export default {
  name: "LoginComponent",
  data() {
    return {
      user: new User('', ''),
      message: '',
      a: '',
      check: true,
      messageEmail:'',
      messagePass:'',
      messageForm: ''
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
      this.$router.push('/admin/home');
    }
    if (this.loggedIn && this.currentUser.roles.length === 1) {

      this.$router.push('/user/home');
    }
  },
  methods: {
    handleLogin() {
      if (!this.user.email && !this.user.password){
        this.messageForm = 'Vui lòng nhập thông tin đăng nhập';
        this.messageEmail = '';
        this.messagePass = '';
        this.message = '';
        this.check = false
      }
      if (!this.user.email && this.user.password){
        this.messageEmail = 'Vui lòng nhập email';
        this.messageForm = '';
        this.message = '';
        this.check = false
      }
      if (!this.user.password && this.user.email){
        this.messagePass = 'Vui lòng nhập mật khẩu'
        this.messageForm = '';
        this.message = '';
        this.check = false
      }
      if(this.user.email && this.user.password){
        this.check = true;
      }
      if (this.check === true) {
        this.$store.dispatch('auth/login', this.user).then(
            () => {
              console.log(this.currentUser.roles.length)
              if (this.loggedIn && this.currentUser.roles[0].authority === "ROLE_ADMIN") {
                this.$router.push('/admin/home');
              }
              if (this.loggedIn && this.currentUser.roles[0].authority === "ROLE_USER") {
                this.$router.push('/user/home');
              }
            },
            error => {
              this.a = (error.response && error.response.data)
              this.message = 'Email hoặc mật khẩu không chính xác'
            }
        );
      }
    }
  }
}
</script>

<style scoped>
.login-bg {
  height: 860px;
  background-image: url("http://getwallpapers.com/wallpaper/full/a/5/d/544750.jpg") ;
}
.login-wrap {
  background-color: #FFFFFF;
  margin: 1.5rem;
  border-radius: 5%;
}
</style>
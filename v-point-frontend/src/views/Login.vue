
<!--<template>-->
<!--  <div>-->
<!--      <div class="container">-->
<!--        <div class="row justify-content-center">-->
<!--          <div class="col-md-7 col-lg-5">-->
<!--            <div class="wrap">-->
<!--              <div>-->
<!--                <img height="200px" v-bind:src="'https://vmgmedia.vn/Content/images/logo/01.png'" />-->
<!--              </div>-->
<!--              <div class="login-wrap p-4 p-md-5">-->
<!--                <div class="d-flex">-->
<!--                  <div class="w-100">-->
<!--                    <h3 class="mb-4">Sign In</h3>-->
<!--                  </div>-->
<!--                </div>-->
<!--                <form name="form" @submit.prevent="handleLogin">-->
<!--                  <small v-if="messageForm" style="color: red">{{messageForm}}</small>-->
<!--                  <div class="form-group mt-5">-->
<!--                    <label>Email</label>-->
<!--                    <input               v-model="user.email"-->

<!--                                         type="email"-->
<!--                                         class="form-control"-->
<!--                                         name="email">-->
<!--                  </div>-->
<!--                  <small v-if="messageEmail" style="color: red">{{messageEmail}}</small>-->
<!--                  <div class="form-group">-->
<!--                    <label>Password</label>-->
<!--                    <input               v-model="user.password"-->
<!--                                         type="password"-->
<!--                                         class="form-control"-->
<!--                                         name="password">-->


<!--                  </div>-->
<!--                  <small v-if="messagePass !== null" style="color: red">{{messagePass}}</small>-->
<!--                  <div class="form-group">-->
<!--                    <button type="submit" class="form-control btn btn-danger rounded submit px-3">Sign In</button>-->
<!--                  </div>-->
<!--                  <small v-if="message" class="alert alert-danger" role="alert">{{message}}</small>-->
<!--                </form>-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import User from '../model/user';-->

<!--export default {-->
<!--  // eslint-disable-next-line vue/multi-word-component-names-->
<!--  name: 'Login',-->
<!--  data() {-->
<!--    return {-->
<!--      roleId: '',-->
<!--      user: new User('', ''),-->
<!--      message: '',-->
<!--      a: '',-->
<!--      check: true,-->
<!--      messageEmail:'',-->
<!--      messagePass:'',-->
<!--      messageForm: ''-->
<!--    };-->
<!--  },-->
<!--  computed: {-->
<!--    loggedIn() {-->
<!--      return this.$store.state.auth.status.loggedIn;-->
<!--    },-->
<!--    currentUser() {-->
<!--      return this.$store.state.auth.user;-->
<!--    },-->
<!--  },-->

<!--  created() {-->
<!--    this.getRoleId()-->
<!--    // if (this.loggedIn && this.roleId === 'ROLE_ADMIN') {-->
<!--    //   this.$router.push('/profile');-->
<!--    // } else {-->
<!--    //   this.$router.push('/');-->
<!--    // }-->
<!--  },-->
<!--  methods: {-->

<!--    getRoleId() {-->
<!--      console.log(this.currentUser)-->
<!--      for (let i = 0; i < this.currentUser.roles.length; i++) {-->
<!--        if (this.currentUser.roles[i].authority === 'ROLE_ADMIN') {-->
<!--          this.roleId = 'ROLE_ADMIN'-->
<!--        } else-->
<!--          this.roleId = '2'-->
<!--      }-->
<!--    },-->


<!--    handleLogin() {-->
<!--      // this.getRoleId();-->
<!--      if (!this.user.email && !this.user.password){-->
<!--        this.messageForm = 'Vui lòng nhập thông tin đăng nhập';-->
<!--        this.messageEmail = '';-->
<!--        this.messagePass = '';-->
<!--        this.message = '';-->
<!--        this.check = false-->
<!--      }-->
<!--      if (!this.user.email && this.user.password){-->
<!--        this.messageEmail = 'Vui lòng nhập email';-->
<!--        this.messageForm = '';-->
<!--        this.message = '';-->
<!--        this.check = false-->
<!--      }-->
<!--      if (!this.user.password && this.user.email){-->
<!--        this.messagePass = 'Vui lòng nhập mật khẩu'-->
<!--        this.messageForm = '';-->
<!--        this.message = '';-->
<!--        this.check = false-->
<!--      }-->
<!--      if(this.user.email && this.user.password){-->
<!--        this.check = true;-->
<!--      }-->
<!--      if (this.check === true) {-->
<!--        this.$store.dispatch('auth/login', this.user).then(-->
<!--            () => {-->
<!--              this.getRoleId()-->
<!--              if (this.loggedIn && this.roleId === 'ROLE_ADMIN') {-->
<!--                this.$router.push('/profile');-->
<!--              }-->
<!--              if (this.loggedIn && this.roleId === '2') {-->
<!--                this.$router.push('/UserVpoint');-->
<!--              }-->
<!--            },-->
<!--            error => {-->
<!--              this.a = (error.response && error.response.data)-->
<!--              this.message = 'Email hoặc mật khẩu không chính xác'-->
<!--            }-->
<!--        );-->
<!--      }-->

<!--    }-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--label {-->
<!--  display: block;-->
<!--  margin-top: 10px;-->
<!--}-->

<!--.card-container.card {-->
<!--  max-width: 350px !important;-->
<!--  padding: 40px 40px;-->
<!--}-->

<!--.card {-->
<!--  background-color: #f7f7f7;-->
<!--  padding: 20px 25px 30px;-->
<!--  margin: 0 auto 25px;-->
<!--  margin-top: 50px;-->
<!--  -moz-border-radius: 2px;-->
<!--  -webkit-border-radius: 2px;-->
<!--  border-radius: 2px;-->
<!--  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);-->
<!--  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);-->
<!--  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);-->
<!--}-->

<!--.profile-img-card {-->
<!--  width: 96px;-->
<!--  height: 96px;-->
<!--  margin: 0 auto 10px;-->
<!--  display: block;-->
<!--  -moz-border-radius: 50%;-->
<!--  -webkit-border-radius: 50%;-->
<!--  border-radius: 50%;-->
<!--}-->
<!--</style>-->
<template>

    <div>
      <b-button v-b-toggle.sidebar-no-header>Toggle Sidebar</b-button>
      <b-sidebar id="sidebar-no-header" aria-labelledby="sidebar-no-header-title" no-header shadow>
        <template #default="{  show }">
          <div class="p-3">
            <h4 id="sidebar-no-header-title">Custom header sidebar</h4>
            <p>
              Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis
              in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
            </p>
            <nav class="mb-3">
              <b-nav vertical>
                <b-nav-item active @click="show">Active</b-nav-item>
                <b-nav-item href="#link-1" @click="show">Link</b-nav-item>
                <b-nav-item href="#link-2" @click="show">Another Link</b-nav-item>
              </b-nav>
            </nav>
            <b-button variant="primary" block @click="show">Close Sidebar</b-button>
          </div>
        </template>
      </b-sidebar>
      
    </div>

</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Login"
}
</script>

<style scoped>

</style>


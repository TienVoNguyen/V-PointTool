<template>
<div>
  <nav class="navbar navbar-dark bg-danger navbar-expand-sm">
    <div class="container">
      <router-link to="/profile" class="navbar-brand">VMG Point</router-link>
    </div>

    <div style="width: 200px">
      <el-dropdown trigger="click"  v-if="loggedIn" >
      <span class="el-dropdown-link">
        {{ currentUser.name }}<i class="el-icon-arrow-down el-icon--right"></i>
      </span>
        <el-dropdown-menu slot="dropdown">

          <el-dropdown-item><el-button type="text" @click="dialogFormVisible1 = true">Đổi mật khẩu</el-button></el-dropdown-item>
          <el-dropdown-item><el-button type="text" @click="logOut">Đăng xuất</el-button></el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </nav>


  <el-dialog title="Đổi mật khẩu" :visible.sync="dialogFormVisible1">
    <el-form>
      <el-form-item label="Nhập mật cũ">
        <el-input v-model="changePass.oldPassword" autocomplete="off"></el-input>
        <small v-if="oldPass != null" style="color: red">{{oldPass}}</small>
      </el-form-item>
      <el-form-item label="Nhập mật khẩu mới">
        <el-input v-model="changePass.newPassword" autocomplete="off"></el-input>
        <small v-if="errP1 != null" style="color: red">{{errP1}}</small>
      </el-form-item>
      <el-form-item label="Xác nhận mật khẩu mới">
        <el-input v-model="changePass.confirmNewPassword" autocomplete="off"></el-input>
        <small v-if="errorsPass != null" style="color: red">{{errorsPass}}</small>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click.prevent="RepassUser(currentUser.id)">Confirm</el-button>
      <!--        <pre>{{changePass}}</pre>-->
  </span>
  </el-dialog>
</div>
</template>

<script>
import login from "@/views/Login";
import authService from "@/service/auth-service";
import swal from "sweetalert2";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Navbar",
  data: function () {
    return {
      dialogFormVisible1: false,
      dialogTableVisible1: false,
      oldPass: '',
      errorsPass: '',
      errP1: '',
      check1: true,
      formLabelWidth: '120px',

      changePass: {
        oldPassword: '',
        newPassword: '',
        confirmNewPassword: '',
      },
    };
  },
  created: async function () {
    try {
      let response = await authService.getAllUser()
      this.user = response.data;
      let response1 = await authService.getAllRole()
      this.roles = response1.data;
      let response2 = await authService.getAllDepartment()
      this.department = response2.data;
      console.log(this.department)
      console.log(this.currentUser)
    } catch (error) {
      console.log(error)
    }
  },
  components: login,
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  methods: {
    removeValidate1(check) {
      this.dialogFormVisible1 = check
      this.oldPass = ''
      this.errP1 = ''
      this.errorsPass = ''
    },
    validPass: function (pass) {
      var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/;
      return re.test(pass);
    },
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    },

    RepassUser(userId) {
      if (!this.changePass.newPassword && this.changePass.confirmNewPassword || !this.changePass.newPassword && !this.changePass.confirmNewPassword) {
        this.errP1 = 'Vui lòng nhập mật khẩu'
        this.check1 = false;
      } else if (!this.validPass(this.changePass.newPassword)) {
        this.errP1 = 'Mật khẩu gồm 8 ký tự trở lên có ít nhất một số và một chữ hoa và chữ thường'
      } else {
        this.errP1 = ''
        this.check1 = true;
      }

      if (this.changePass.newPassword && !this.changePass.confirmNewPassword) {
        this.errorsPass = 'Vui lòng xác nhận mật khẩu'
        this.check1 = false;
      } else if (this.changePass.newPassword !== this.changePass.confirmNewPassword) {
        this.errorsPass = 'Mật khẩu không trùng khớp'
        this.check1 = false;
      } else if (this.changePass.newPassword === this.changePass.confirmNewPassword) {
        this.errorsPass = ''
        this.check1 = true;
      }

      if (this.check1 === true) {
        authService.userRepass(userId, this.changePass)
            .then(
                async data => {
                  this.a = data.message,
                      this.dialogFormVisible1 = false;
                  await swal.fire( {
                        toast: true,
                        title: "Xong!",
                        icon: "success",
                        position: 'top-end',
                        showConfirmButton: false,
                        timer: 3000
                      }
                  )
                },
                () => {
                  this.oldPass = 'Mật khẩu cũ không chính xác'
                  this.dialogFormVisible = true;
                } );
      }

    }
  }
}


</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  color: #ffffff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.demonstration {
  display: block;
  color: #8492a6;
  font-size: 14px;
  margin-bottom: 20px;
}
</style>

<template>
<div>
  <nav class="navbar navbar-dark bg-danger navbar-expand-sm">
    <div class="container">
      <router-link to="/profile" class="navbar-brand">VMG Point</router-link>
    </div>

    <div>
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
    <el-form :model="form">
      <el-form-item label="Promotion name" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="Zones" :label-width="formLabelWidth">
        <el-select v-model="form.region" placeholder="Please select a zone">
          <el-option label="Zone No.1" value="shanghai"></el-option>
          <el-option label="Zone No.2" value="beijing"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible1 = false">Cancel</el-button>
    <el-button type="primary" @click="dialogFormVisible1 = false">Confirm</el-button>
  </span>
  </el-dialog>
</div>
</template>

<script>
import login from "@/views/Login";
import authService from "@/service/auth-service";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Navbar",
  data : function() {
    return {
      userForm1: {
        staffId: '',
        fullname: '',
        password: '',
        confirmPassword: '',
        email: '',
        department: '',
        role: []
      },
      dialogTableVisible: false,
      dialogFormVisible: false,
      dialogFormVisible1: false,
      dialogTableVisible1: false,
      user: [],
      roles: [],
      errorsPass: '',
      department: [],
      errorsName: '',
      matchName: '',
      errorEmail: '',
      check1 : true,
      formLabelWidth: '120px',



      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
    };
  },
  created : async function(){
    try {
      let response = await authService.getAllUser()
      this.user = response.data;
      let response1 = await authService.getAllRole()
      this.roles = response1.data;
      let response2 = await authService.getAllDepartment()
      this.department = response2.data;
      console.log(this.department)
      console.log(this.currentUser)
    }
    catch (error){
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
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    },
    handleRegister() {
      let form = document.querySelector('#userForm');
      let formdata = new FormData(form);
      formdata.append("department.id", this.userForm1.department)
      formdata.append("role", this.userForm1.role)
      console.log(formdata);
      authService.createUser(formdata);
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

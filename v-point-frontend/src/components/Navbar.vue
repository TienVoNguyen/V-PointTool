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

  <el-dialog title="Tạo nhân sự mới" :visible.sync="dialogFormVisible">
    <el-form :model="userForm1" id="userForm">
      <el-form-item label="Họ và tên:" :label-width="formLabelWidth" prop="fullname">
        <el-input name= "fullname" v-model="userForm1.fullname" autocomplete="off"></el-input>
      </el-form-item>
      <div class="row">
        <div class="col-6">
          <el-form-item label="Mã nhân sự:" :label-width="formLabelWidth" prop="staffId">
            <el-input name= "staffId" v-model="userForm1.staffId" autocomplete="off"></el-input>
          </el-form-item>
          <small v-if="errorsName != null">{{errorsName}}</small>
          <small v-if="matchName != null">{{matchName}}</small>
        </div>
        <div class="col-6">
          <el-form-item  label="Phòng ban:" :label-width="formLabelWidth" prop="department">
            <el-select  v-model="userForm1.department" placeholder="Vui lòng chọn phòng ban">
              <el-option v-for="item in department"
                         :key="item.id"
                         :label="item.name"
                         :value="item.id"></el-option>
            </el-select>
          </el-form-item>
        </div>
      </div>
      <el-form-item label="Email:" :label-width="formLabelWidth" prop="email">
        <el-input name= "email" v-model="userForm1.email" autocomplete="off"></el-input>
      </el-form-item>
      <small v-if="errorEmail != null">{{errorEmail}}</small>

      <el-form-item label="Password:" :label-width="formLabelWidth" prop="password">
        <el-input name= "password" type="password" v-model="userForm1.password" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="Confirm password:" :label-width="formLabelWidth" prop="confirmPassword">
        <el-input name= "confirmPassword" type="password" v-model="userForm1.confirmPassword" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="Quyền truy cập" :label-width="formLabelWidth" prop="role">
        <el-select  v-model="userForm1.role" multiple placeholder="Chọn quyền truy cập">
          <el-option v-for="item in roles"
                     :key="item.id"
                     :label="item.name=='ROLE_ADMIN'?'Admin':'Người dùng'"
                     :value="item.id"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">Cancel</el-button>
    <el-button type="primary"  @click.prevent="handleRegister">Confirm</el-button>
<!--      <pre>{{userForm1}}</pre>-->
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

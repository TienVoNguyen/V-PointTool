<template>
  <el-row class="tac">
      <el-menu
          default-active="2"
          class="el-menu-vertical-demo "
          @open="handleOpen"
          @close="handleClose"
          background-color="#dc3545"
          text-color="#fff"
          active-text-color="#ffd04b">
        <el-menu-item index="">
          <el-button plain type="text">
            <router-link to="/profile" class="navbar-brand"><i class="fa fa-home"></i><span>Trang chủ</span></router-link>

          </el-button>


        </el-menu-item>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-plus"></i>
            <span>Thêm điểm V-Point</span>
          </template>

            <el-menu-item index="1-1"><i class="el-icon-user"></i>Thêm theo tên</el-menu-item>
            <el-menu-item index="1-2"><i class="el-icon-folder-opened"></i>Import file excel</el-menu-item>


        </el-submenu>
        <el-menu-item index="2">
          <el-button plain type="text" @click="dialogFormVisible = true"><i class="el-icon-user-solid"></i>
            <span>Thêm nhân viên mới</span></el-button>

        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-check"></i>
          <span><el-button plain type="text" v-if="loggedIn && currentUser.roles.length === 2" ><router-link to="/">V-Point của tôi</router-link></el-button></span>
        </el-menu-item>
      </el-menu>


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
          <el-select  v-model="userForm1.role" multiple placeholder="Chọn quyền truy cập" >
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
  </el-row>

</template>

<script>
import authService from "@/service/auth-service";
import login from "@/views/Login";
import swal from 'sweetalert2'

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Sidebar",
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
      console.log(this.role)
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
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleRegister() {
      let form = document.querySelector('#userForm');
      let formdata = new FormData(form);
      formdata.append("department.id", this.userForm1.department)
      formdata.append("role", this.userForm1.role)
      console.log(formdata);
      authService.createUser(formdata);
      if (authService.createUser(formdata)){
        this.dialogFormVisible = false;
        // eslint-disable-next-line no-undef
        swal.fire({
          title: "Xong!",
          icon: "success",
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000
            }
        )
      } else {
        // eslint-disable-next-line no-undef
        swal.fire({
          title: "Đã có lỗi xảy ra!",
          icon: "error"
        })
      }
    }
  }
}
</script>

<style scoped>

</style>

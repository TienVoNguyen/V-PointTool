<template>
  <el-row class="tac">
      <el-form :model="userForm1" id="userForm" class="text-left">
        <div class="row text-start" >
          <div class="col-4">
            <el-form-item prop="fullname">
              <label for="fullname">Họ và tên</label>
              <el-input name= "fullname" v-model="userForm1.fullname" autocomplete="off"></el-input>
              <small v-if="errorsName !== null" style="color: red">{{errorsName}}</small>
            </el-form-item>
          </div>
          <div class="col-2">
            <el-form-item prop="staffId">
              <label for="staffId">Mã nhân sự</label>
              <el-input name= "staffId" v-model="userForm1.staffId" autocomplete="off"></el-input>
              <small v-if="errId !== null" style="color: red">{{errId}}</small>
            </el-form-item>

          </div>
          <div class="col-3">
            <el-form-item   prop="department">
              <label for="a">Phòng ban</label> <br>
              <el-select style="width: 100%" v-model="userForm1.department" placeholder="Vui lòng chọn phòng ban">

                <el-option   v-for="item in department"
                           :key="item.id"
                           :label="item.name"
                           :value="item.id"></el-option>
              </el-select>
              <small v-if="errDpm !== null" style="color: red">{{errDpm}}</small>
            </el-form-item>
          </div>
          <div class="col-3">
            <el-form-item prop="role">
              <label for="">Quyền truy cập</label>
              <el-select style="width: 100%" v-model="userForm1.role" placeholder="Chọn quyền truy cập" >
                <el-option v-for="item in roles"
                           :key="item.id"
                           :label="item.name==='ROLE_ADMIN'?'Admin':'Người dùng'"
                           :value="item.id"></el-option>
              </el-select>
              <small v-if="errRole !== null" style="color: red">{{errRole}}</small>
            </el-form-item>
          </div>
        </div>

        <div class="row">

          <div class="col-6 text-start">
            <el-form-item prop="email">
              <label for="email">Email đăng nhập</label>
              <el-input type="email" name= "email" v-model="userForm1.email" autocomplete="off"></el-input>
              <small v-if="errorEmail != null" style="color: red">{{errorEmail}}</small>
            </el-form-item>

          </div>
          <div class="col-3" >
            <el-form-item prop="password">
              <label for="">Nhập mật khẩu</label>
              <el-input name= "password" type="password" v-model="userForm1.password" autocomplete="off"></el-input>
              <small v-if="errP1 != null" style="color: red">{{errP1}}</small>
            </el-form-item>
          </div>
          <div class="col-3">
            <el-form-item  prop="confirmPassword">
              <label for="">Nhập lại mật khẩu</label>
              <el-input name= "confirmPassword" type="password" v-model="userForm1.confirmPassword" autocomplete="off"></el-input>
              <small v-if="errorsPass !== null" style="color: red">{{errorsPass}}</small>
            </el-form-item>

          </div>
        </div>
      </el-form>
    <el-button @click="removeValidate(false)">Cancel</el-button>
    <el-button type="primary"  @click.prevent="handleRegister">Confirm</el-button>
  </el-row>

</template>

<script>
import authService from "@/service/auth-service";
import login from "@/components/auth/Login";
import swal from 'sweetalert2'

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Sidebar",
  data: function () {
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
      roleId: '',
      dialogTableVisible: false,
      dialogFormVisible: false,
      user: [],
      a: '',
      roles: [],
      message: '',
      errId: '',
      errP1: '',
      errorsPass: '',
      department: [],
      errorsName: '',
      matchName: '',
      errorEmail: '',
      errDpm: '',
      errRole: '',
      checkButton: true,

      check1: true,
      checkId: true,
      checkEmail: true,
      checkName: true,
      checkDpm: true,
      checkRole: true,
      checkPass: true,
      formLabelWidth: '120px',

    };
  },

  created: async function () {
    this.getRoleId()
    try {
      let response = await authService.getAllUser()
      this.user = response.data;
      let response1 = await authService.getAllRole()
      this.roles = response1.data;
      let response2 = await authService.getAllDepartment()
      this.department = response2.data;
      console.log(this.roles)
      console.log(this.department)
      console.log(this.user)
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
    getRoleId() {
      for (let i = 0; i < this.currentUser.roles.length; i++) {
        if (this.currentUser.roles[i].id === 1) {
          this.roleId = this.currentUser.roles[i].id
        } else
          this.roleId = 2
      }
    },

    validEmail: function (email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },

    validPass: function (pass) {
      var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/;
      return re.test(pass);
    },

    removeValidate(check){
      this.dialogFormVisible = check,
          this.check1= false,
          this.checkId= false,
          this.checkEmail= false,
          this.checkName= false,
          this.checkDpm= false,
          this.checkRole= false,
          this.checkPass= false,
          this.message = '',
          this.errId = '',
          this.errP1 = '',
          this.errorsPass = '',
          this.errorsName = '',
          this.matchName = '',
          this.errorEmail = '',
          this.errDpm = '',
          this.errRole = ''
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    async handleRegister() {
      let response = await authService.getAllUser()
      this.user = response.data;

      for (let i = 0; i < this.user.length; i++) {
        if (this.userForm1.staffId === this.user[i].staffId) {
          this.errId = 'Mã nhân sự đã tồn tại'
          this.checkId = false;
          break
        } else {
          this.checkId = true;
        }
      }

      if (!this.userForm1.staffId) {
        this.errId = 'Hãy nhập mã nhân sự'

        this.checkId = false;
      }

      if (this.userForm1.staffId && this.checkId === true) {
        this.errId = ''

      }

      if (!this.userForm1.department) {
        this.checkDpm = false;
        this.errDpm = 'Hãy chọn phòng ban'
      } else {
        this.errDpm = ''
        this.checkDpm = true
      }

      if (this.userForm1.role.length === 0) {
        this.checkRole = false;
        this.errRole = 'Hãy chọn quyền truy cập'
      } else {
        this.checkRole = true;
        this.errRole = ''
      }

      for (let i = 0; i < this.user.length; i++) {
        if (this.userForm1.email === this.user[i].email) {
          this.errorEmail = 'Email này đã tồn tại trong hệ thống'

          this.checkEmail = false;
          break
        } else {

          this.checkEmail = true;
        }
      }

      if (!this.userForm1.email) {
        this.errorEmail = 'Vui lòng nhập email nhân viên'

        this.checkEmail = false;
      } else if (!this.validEmail(this.userForm1.email)) {
        this.errorEmail = 'Vui lòng nhập đúng định dạng email'
        this.checkEmail = false;
      } else if (this.validEmail(this.userForm1.email) && this.userForm1.email && this.checkEmail === true) {
        this.errorEmail = ''
        this.checkEmail = true
      }


      if (!this.userForm1.fullname) {
        this.errorsName = 'Vui lòng nhập tên nhân viên'
        this.check1 = false;
      } else {
        this.errorsName = ''
        this.check1 = true;
      }


      if (this.userForm1.password !== this.userForm1.confirmPassword) {
        this.errorsPass = 'Mật khẩu không trùng khớp'
        this.checkPass = false
      }


      if (!this.userForm1.password && this.userForm1.confirmPassword || !this.userForm1.password && !this.userForm1.confirmPassword) {
        this.errP1 = 'Vui lòng nhập mật khẩu'
        this.checkPass = false;
      } else if (!this.validPass(this.userForm1.password)) {
        this.errP1 = 'Mật khẩu gồm 8 ký tự trở lên có ít nhất một số và một chữ hoa và chữ thường'
        this.checkPass = false;
      } else {
        this.errP1 = ''
        this.checkPass = true;
      }

      if (this.userForm1.password && !this.userForm1.confirmPassword) {
        this.errorsPass = 'Vui lòng xác nhận mật khẩu'
        this.checkPass = false;
      } else if (this.userForm1.password !== this.userForm1.confirmPassword) {
        this.errorsPass = 'Mật khẩu không trùng khớp'
        this.checkPass = false;
      } else if (this.userForm1.password === this.userForm1.confirmPassword) {
        this.errorsPass = ''
        this.checkPass = true;
      }


      if (this.check1 === true && this.checkId === true && this.checkEmail === true && this.checkDpm === true && this.checkPass === true && this.checkRole === true) {
        let form = document.querySelector('#userForm');
        let formdata = new FormData(form);
        formdata.append("department.id", this.userForm1.department)
        formdata.append("role", this.userForm1.role)
        console.log(formdata);
        authService.createUser(formdata)
            .then(
                data => {
                  this.a = data.message;
                  // let response = authService.getAllUser()
                  // this.user = response.data;
                  this.dialogFormVisible = false;
                  this.checkId = false;
                  this.checkEmail = false;
                  swal.fire({
                        toast: true,
                        title: "Xong!",
                        icon: "success",
                        position: 'top-end',
                        showConfirmButton: false,
                        timer: 3000
                      }
                  )
                }, () => {
                  this.dialogFormVisible = true;
                  swal.fire({
                    toast: true,
                    title: "Đã có lỗi xảy ra!",
                    icon: "error",
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 3000
                  });
                });
      } else {
        this.message = 'Vui lòng điền đầy đủ thông tin'
      }
    }
  }
}
</script>

<style scoped>

</style>

<template>
  <el-container>
    <h3 style="color: #6c757d" align="center">Quản lý người dùng</h3><br>
    <br>
    <el-table
        border
        :data="listUser"
        style="width: 100%">
      <el-table-column
          align="center"
          prop="staffId"
          label="Mã nhân viên"
          width="120">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fullName"
          label="Họ và tên"
          width="180">
      </el-table-column>
      <el-table-column
          align="center"
          prop="email"
          label="Email"
          width="250">
      </el-table-column>
      <el-table-column
          align="center"
          prop="department.name"
          label="Phòng ban"
          width="150">
      </el-table-column>
      <el-table-column
          align="center"
          label="Quyền truy cập"
          width="125">
        <template v-slot="scope">
          <p height="50px" v-for="(role, index) in scope.row.role" :key="index" >
            {{role.name == 'ROLE_ADMIN'? 'Admin' : 'Người dùng'}}
          </p>
        </template>
      </el-table-column>
      <!--      <el-table-column-->
      <!--          width="100"-->
      <!--          label="V-point">-->
      <!--        <template v-slot="scope">-->
      <!--          <p height="50px">-->
      <!--            {{scope.row.marks.reduce((total, mark)=>{return total += mark.point},0)}}-->
      <!--          </p>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column
          align="center" class="w-100" style="width: 200px">

        <template v-slot="scope">
          <el-button class="btn btn-warning" type="text" @click="removeValidate(true, scope.row.id)"><i size="default" class="el-icon-edit"></i></el-button>
          <el-button class="btn btn-primary" type="text" @click="removeValidate1(true, scope.row.id)"><i size="default" class="el-icon-key"></i></el-button>
          <el-button class="btn btn-danger" type="text" @click="deleteUser(scope.row.id)"><i size="default" class="el-icon-delete"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-footer>
      <el-pagination
          layout="prev, pager, next"
          :page-count="count"
          @current-change="handlePageChange"
          hide-on-single-page>
      </el-pagination>
    </el-footer>


    <!--     //change pass -->

    <el-dialog title="Đổi mật khẩu" :visible.sync="dialogFormVisible1">
      <el-form>
        <el-form-item label="Nhập mật khẩu mới">
          <el-input v-model="changePass.newPassword" autocomplete="off"></el-input>
          <small v-if="errP1 != null" style="color: red">{{errP1}}</small>
        </el-form-item>
        <el-form-item label="Xác nhận mật khẩu mới">
          <el-input v-model="changePass.confirmNewPass" autocomplete="off"></el-input>
          <small v-if="errorsPass != null" style="color: red">{{errorsPass}}</small>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click.prevent="RepassUser(user.id)">Confirm</el-button>
        <!--        <pre>{{changePass}}</pre>-->
  </span>
    </el-dialog>


    <el-dialog  title="Sửa thông tin" :visible.sync="dialogFormVisible" width="70%">
      <el-form :model="user" id="userForm" >
        <div class="row text-start" >
          <div class="col-6">
            <el-form-item prop="fullname">
              <label for="fullname">Họ và tên</label>
              <el-input name= "fullname" v-model="user.fullName" autocomplete="off"></el-input>
              <small v-if="errorsName !== null" style="color: red">{{errorsName}}</small>
            </el-form-item>
          </div>
          <div class="col-3">
            <el-form-item prop="staffId">
              <label for="staffId">Mã nhân sự</label>
              <el-input name= "staffId" v-model="user.staffId" autocomplete="off"></el-input>
              <small v-if="errId !== null" style="color: red">{{errId}}</small>
            </el-form-item>

          </div>
          <div class="col-3">
            <el-form-item   prop="department">
              <label for="a">Phòng ban</label> <br>
              <el-select style="width: 100%" v-model="user.department" value-key="id">
                <el-option   v-for="item in departments"
                             :key="item.id"
                             :label="item.name"
                             :value="item"
                ></el-option>
              </el-select>
              <small v-if="errDpm !== null" style="color: red">{{errDpm}}</small>
            </el-form-item>
          </div>
        </div>

        <div class="row">

          <div class="col-6 text-start">
            <el-form-item prop="email">
              <label for="email">Email đăng nhập</label>
              <el-input type="email" name= "email" v-model="user.email" autocomplete="off"></el-input>
              <small v-if="errorEmail != null" style="color: red">{{errorEmail}}</small>
            </el-form-item>

          </div>
          <div class="col-6">
            <el-form-item prop="role">
              <label for="">Quyền truy cập</label>
              <el-select style="width: 100%" v-model="user.role" multiple placeholder="" value-key="id">
                <el-option v-for="item in roles"
                           :key="item.id"
                           :label="item.name=='ROLE_ADMIN'?'Admin':'Người dùng'"
                           :value="item"></el-option>
              </el-select>
              <small v-if="errRole !== null" style="color: red">{{errRole}}</small>
            </el-form-item>
          </div>
        </div>

        <div style="display: none">
          <div class="col-3">
            <el-form-item prop="password" >
              <label for="">Nhập mật khẩu</label>
              <el-input name= "password" type="password" v-model="user.password" autocomplete="off"></el-input>
              <small v-if="errP1 != null" style="color: red">{{errP1}}</small>
            </el-form-item>
          </div>
          <div class="col-3">
            <el-form-item  prop="confirmPassword">
              <label for="">Nhập lại mật khẩu</label>
              <el-input name= "confirmPassword" type="password" v-model="user.password" autocomplete="off"></el-input>
              <small v-if="errorsPass !== null" style="color: red">{{errorsPass}}</small>
            </el-form-item>

          </div>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
<!--    <el-button @click="removeValidate(false)">Cancel</el-button>-->
    <el-button type="primary"  @click.prevent="editUser(user.id)">Confirm</el-button>
        <!--      <pre>{{userForm1}}</pre>-->
  </span>
    </el-dialog>
  </el-container>



</template>
<script>
import  {UserService as userService} from "@/service/user-service";
import swal from 'sweetalert2'
import login from "@/components/auth/Login";
import authService from "@/service/auth-service";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'UserManager',
  data : function() {
    return {
      listUser: '',
      listU: '',
      point: [],
      currentIndex: -1,
      page: 1,
      count: 0,
      itemCount: 6,
      categoryCode: '',
      errorMessage: '',
      curStaffId: '',
      curEmail: '',
      user: '',
      user1: '',
      dialogTableVisible: false,
      dialogFormVisible: false,
      dialogFormVisible1: false,
      dialogTableVisible1: false,
      message: '',
      roles: [],
      errId: '',
      errP1: '',
      errorsPass: '',
      departments: [],
      errorsName: '',
      matchName: '',
      errorEmail: '',
      errDpm: '',
      errRole: '',
      checkButton: true,
      selected: '',
      check1: true,
      checkId: true,
      checkEmail: true,

      changePass: {
        newPassword: '',
        confirmNewPass: '',
      },
    }
  },
  async created() {
    await this.retrieveUserList()
    let response = await authService.getAllUser()
    this.listU = response.data;
    let response1 = await authService.getAllRole()
    this.roles = response1.data;
    let response2 = await authService.getAllDepartment()
    this.departments = response2.data;
    console.log(this.roles)
    console.log(this.departments)
    console.log(this.currentUser)
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
    validEmail: function (email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },

    validPass: function (pass) {
      var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/;
      return re.test(pass);
    },

    removeValidate1(check, userId) {
      this.findByIdUser(userId)
      this.dialogFormVisible1 = check
      this.errP1 = ''
      this.errorsPass = ''
    },


    removeValidate(check, userId){
      this.findByIdUser(userId)
      this.dialogFormVisible = check,
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
    async retrieveUserList() {
      const params = this.getRequestParams(
          this.page
      );
      console.log(params)
      let response = await userService.getAll(params)
      this.listUser = response.data.content;
      this.count = response.data.totalPages;
      console.log(this.point)
      console.log(this.count)
      console.log(this.listUser)
    },
    findByIdUser : async function (userId) {
      let response = await userService.findById(userId);
      if (response) {
        this.user = response.data
        console.log(this.user)
        console.log(this.UserId)
        this.user1 = response.data
        this.curStaffId = this.user1.staffId;
        this.curEmail = this.user1.email;
        this.selected = this.user.department.id
      }
    },
    deleteUser : async function (userId){
      swal.fire({
        title: 'Bạn có chắc muốn xóa người này?',
        text: 'Thao tác này không thể hoàn tác lại!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d63049',
        cancelButtonColor: '#33dd91',
        confirmButtonText: 'Xóa',
        cancelButtonText: 'Quay lại',
      }).then(async (result) => {
            if (result.isConfirmed) {
              let response = await userService.deleteUser(userId);
              if (response) {
                const params = this.getRequestParams(
                    this.page
                );
                console.log(params)
                let response = await userService.getAll(params)
                this.listUser = response.data.content
                this.count = response.data.totalPages;
              }
              await swal.fire(
                  'Đã xóa',
                  '',
                  'success'
              );
            }
          }
      );
    },

    RepassUser(userId){
      if (!this.changePass.newPassword && this.changePass.confirmNewPass || !this.changePass.newPassword && !this.changePass.confirmNewPass){
        this.errP1 = 'Vui lòng nhập mật khẩu'
        this.check1 = false;
      } else if (!this.validPass(this.changePass.newPassword)) {
        this.errP1 = 'Mật khẩu gồm 8 ký tự trở lên có ít nhất một số và một chữ hoa và chữ thường'
        this.check1 = false;
      } else {
        this.errP1 = ''
        this.check1 = true;
      }

      if (this.changePass.newPassword && !this.changePass.confirmNewPass){
        this.errorsPass = 'Vui lòng xác nhận mật khẩu'
        this.check1 = false;
      } else if (this.changePass.newPassword !== this.changePass.confirmNewPass){
        this.errorsPass = 'Mật khẩu không trùng khớp'
        this.check1 = false;
      } else if (this.changePass.newPassword === this.changePass.confirmNewPass && this.validPass(this.changePass.newPassword)){
        this.errorsPass = ''
        this.check1 = true;
      }

      if (this.check1 === true){
        authService.adminRepass(userId, this.changePass)
            .then(
                async data => {
                  const params = this.getRequestParams(
                      this.page
                  );
                  // if (this.listUser.length === 10){
                  //   this.params += 1
                  // }
                  console.log(params)
                  let response = await authService.getUserPage(params)
                  console.log(response)
                  this.listUser = response.data.content
                  this.count = response.data.totalPages;
                  this.a = data.message,
                      this.dialogFormVisible1 = false;
                  await swal.fire({
                        toast: true,
                        title: "Xong!",
                        icon: "success",
                        position: 'top-end',
                        showConfirmButton: false,
                        timer: 3000
                      }
                  )
                },  () => {
                  this.dialogFormVisible1 = true;
                  swal.fire({
                    toast: true,
                    title: "Đã có lỗi xảy ra!",
                    icon: "error",
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 3000
                  });
                });
      }


    },

    editUser(userId){
      if (!this.user.fullName){
        this.errorsName = 'Vui lòng nhập tên nhân viên'
        this.check1 = false;
      } else {
        this.errorsName = ''
      }
      for (let i = 0; i < this.listUser.length; i++) {
        if (this.user.staffId === this.listUser[i].staffId && this.user.staffId !== this.curStaffId){
          this.errId = 'Mã nhân sự đã tồn tại'
          this.check1 = false;
          this.checkId = false;
          break
        } else {
          this.checkId = true;
          this.check1 = true;
          this.errId = ''
        }
      }
      if (!this.user.staffId){
        this.errId = 'Hãy nhập mã nhân sự'
        this.check1 = false;
        this.checkId = false;
      }
      for (let i = 0; i < this.listUser.length; i++) {
        if (this.user.email === this.listUser[i].email && this.user.email !== this.curEmail){
          this.errorEmail = 'Email này đã tồn tại trong hệ thống'
          this.check1 = false;
          this.checkEmail = false;
          break
        } else {
          this.check1 = true;
          this.checkEmail = true;
        }
      }
      if (!this.user.email){
        this.errorEmail = 'Vui lòng nhập email nhân viên'
        this.check1 = false;
        this.checkEmail = false;
      } else if (!this.validEmail(this.user.email)) {
        this.errorEmail = 'Vui lòng nhập đúng định dạng email'
        this.checkEmail = false;
      } else if (this.validEmail(this.user.email) && this.user.email && this.checkEmail === true){
        this.errorEmail = ''
        this.check1 = true;
      }
      if (!this.user.department){
        this.check1 = false;
        this.errDpm = 'Hãy chọn phòng ban'
      } else {
        this.errDpm = ''
        this.check1 = true;
      }
      if (this.user.role.length === 0){
        this.check1 = false;
        this.errRole = 'Hãy chọn quyền truy cập'
      } else {
        this.errRole = ''
        this.check1 = true;
      }
      if (this.check1 === true && this.checkId === true && this.checkEmail === true){
        let form = document.querySelector('#userForm');
        let formdata = new FormData(form);
        formdata.append("department.id", this.user.department.id)
        let roles = [];
        this.user.role.forEach(r => {
          roles.push(r.id)
        })
        console.log(roles)
        formdata.append("role", roles)
        authService.editUser(userId, formdata)
            .then(
                async data => {
                  const params = this.getRequestParams(
                      this.page
                  );
                  console.log(params)
                  let response = await authService.getUserPage(params)
                  console.log(response)
                  this.listUser = response.data.content
                  this.count = response.data.totalPages;
                  this.a = data.message,
                      this.dialogFormVisible = false;
                  swal.fire({
                    toast: true,
                    title: "Xong!",
                    icon: "success",
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 3000
                  })
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
      }
    },
    refreshList() {
      this.retrieveUserList();
      this.currentIndex = -1;
    },
    mounted() {
      this.retrieveUserList();
    },
    handlePageChange(value) {
      this.page = value;
      console.log(this.page)
      this.retrieveUserList();
    },
    getRequestParams(page) {
      let params = {};
      if (page) {
        params["p"] = page - 1;
      }
      return params;
    },
  }
};
</script>

<style scoped>
</style>

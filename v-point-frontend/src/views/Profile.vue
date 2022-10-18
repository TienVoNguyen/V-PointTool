<template>
  <el-container>
    <el-table
        :data="listUser"
        style="width: 100%">
      <el-table-column
          prop="staffId"
          label="Mã nhân viên"
          width="180">
      </el-table-column>
      <el-table-column
          prop="fullName"
          label="Họ và tên"
          width="180">
      </el-table-column>
      <el-table-column
          prop="department.name"
          label="Phòng ban"
          width="180">
      </el-table-column>
      <el-table-column
          label="Quyền truy cập"
          width="180">
        <template v-slot="scope">
          <p height="50px" v-for="(role, index) in scope.row.roleSet" :key="index" >
            {{role.name == 'ROLE_ADMIN'? 'Admin' : 'Người dùng'}}
          </p>
        </template>
      </el-table-column>
      <el-table-column
          prop="address"
          label="Tổng điểm V-point">
      </el-table-column>

      <el-table-column
          align="right">
        <template v-slot="scope">
          <el-button
              type="success"
              @click="deleteUser(scope.row.id)">Thêm điểm
          </el-button>
          <el-button
              type="warning"
              @click="deleteUser(scope.row.id)">Sửa thông tin
          </el-button>
          <el-button
              type="danger"
              @click="deleteUser(scope.row.id)">Xóa
          </el-button>
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
  </el-container>
</template>

<script>


import {UserService as userService} from "@/service/user-service";
// import authService from "@/service/auth-service";
// import response from "core-js/internals/is-forced";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Profile',
  data : function() {
    return {
          listUser: [],
          currentUser: null,
          currentIndex: -1,
          page: 1,
          count: 0,
          itemCount: 6,
          categoryCode: '',
      errorMessage: ''
    }
  },
  created() {
    this.retrieveUserList()
  },

  methods: {
    async retrieveUserList() {
      const params = this.getRequestParams(
          this.page
      );
      console.log(params)
      let response = await userService.getAll(params)
      this.listUser = response.data.content
      this.count = response.data.totalPages;
      console.log(this.count)
      console.log(this.listUser)
    },

    deleteUser : async function (userId){
      try {
        let response = await userService.deleteUser(userId);
        if (response){
          const params = this.getRequestParams(
              this.page
          );
          console.log(params)
          let response = await userService.getAll(params)
          this.listUser = response.data.content
          this.count = response.data.totalPages;
        }
      } catch (error){
        this.errorMessage(error)
      }
    },

    refreshList() {
      this.retrieveUserList();
      this.currentIndex = -1;
    },

    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      }

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

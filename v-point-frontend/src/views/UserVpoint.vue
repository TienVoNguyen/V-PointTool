<template>
  <el-table
      :data="point.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 700px; margin-left: auto; margin-right: auto;">
    <el-table-column
        label="date"
        prop="date">
    </el-table-column>
    <el-table-column
        label="Point"
        prop="point">
    </el-table-column>
    <el-table-column
        align="right">
      <template slot="header" slot-scope="scope">
        <el-input
            v-model="search"
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
            placeholder="Type to search"/>
      </template>
      <template slot-scope="scope">
        <el-button
            type="warning"
            @click="handleDelete(scope.$index, scope.row)">Chi tiết</el-button>
      </template>
    </el-table-column>
  </el-table>
<!--  <div class="el-container">-->
<!--    <el-table-->
<!--        :data="point"-->
<!--        style="margin-left: auto; margin-right: auto; width: 100px">-->
<!--      <el-table-column-->
<!--          prop="date"-->
<!--          label="Date"-->
<!--          >-->
<!--      </el-table-column>-->
<!--      <el-table-column-->
<!--          prop="point"-->
<!--          label="Point"-->
<!--          >-->
<!--      </el-table-column>-->

<!--    </el-table>-->
<!--  </div>-->

</template>

<script>

import {UserService as userService} from "@/service/user-service";

export default {
  name: "UserVpoint",
  data() {
    return {
      idUser : '',
      point: [],
      search: '',
    }
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
    this.getVPoint()
  },

  methods: {

    async getVPoint() {
      if (this.currentUser != null) {
        console.log(this.currentUser)
        this.idUser = this.currentUser.id;
      }
      let response = await userService.getVpoint(this.idUser)
      this.point = response.data
      console.log(this.point)
    }

  }

}
</script>

<style scoped>

</style>

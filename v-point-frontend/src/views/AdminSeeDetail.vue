<template>
  <el-table
      :data="Point"
      style="width: 100%">
    <el-table-column
        prop="date"
        label="Thời gian"
        width="120">
    </el-table-column>
    <el-table-column
        prop="sum"
        label="Điểm V-poin"
        width="180">
    </el-table-column>
    <el-table-column
        label="Operations">
      <template slot-scope="scope">
        <router-link :to="`/AdminSeeDetailVPoint/${formatYear(scope.row.date)}/${formatMonth(scope.row.date)}/${idUser}`" class="btn btn-warning">Xem chi tiết</router-link>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import moment from "moment";
import {UserService as userService} from "@/service/user-service";

export default {
  name: "AdminSeeDetail",
  data() {
    return {
      idUser : this.$route.params.idUser,
      Point: [],
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
    formatYear(value){
      if (value) {
        return moment(String(value)).format('YYYY')
      }
    },

    formatMonth(value){
      if (value) {
        return moment(String(value)).format('MM')
      }
    },

    async getVPoint() {
      let response = await userService.getVpoint(this.idUser)
      this.Point = response.data
      console.log(this.Point)
    }

  }
}
</script>

<style scoped>

</style>

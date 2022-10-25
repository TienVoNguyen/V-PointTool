<template>
  <div>
    <div>
            <el-select filterable placeholder="Select">
              <el-option
                  v-for="item in year"
                  :key="item"
                  :label="item"
                  :value="item"
                  :click="getVpointByYear(item)">
              </el-option>
            </el-select>
    </div>
    <div>
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
            <router-link :to="`/api/mark/${formatYear(scope.row.date)}/${formatMonth(scope.row.date)}`" class="btn btn-warning">Xem chi tiết</router-link>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>



</template>

<script>

import {UserService as userService} from "@/service/user-service";
import moment from "moment";

export default {
  name: "UserVpoint",
  data() {
    return {
      idUser : '',
      Point: [],
      search: '',
      year: [],
      page: ''
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
      if (this.currentUser != null) {
        this.idUser = this.currentUser.id;
      }
      let response = await userService.getVpoint(this.idUser)
      this.Point = response.data
      let response1 = await userService.getYear(this.idUser)

      for (let i = 0; i < response1.data.length; i++) {
        this.year.push(this.formatYear(response1.data[i].date))
      }


    },

    async getVpointByYear(params) {
      if (this.currentUser != null) {
        console.log(this.currentUser)
        this.idUser = this.currentUser.id;
      }
      let params1 = this.getRequestParams(params)
      let response = await userService.getVpointByYear(this.idUser, params1)
      this.Point = response.data
      console.log(this.Point)
    },

    getRequestParams(page) {
      let params = {};
      if (page) {
        params["year"] = page;
      }
      return params;
    },


  }

}
</script>

<style scoped>

</style>

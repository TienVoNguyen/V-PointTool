<template>


  <div>
    <br>
    <h3 align="center" style="color: #6c757d"> Điểm V-Point năm: {{selected}} </h3>
    <br><br><br>

    <div class="text-center">
      <h4 style="color: #6c757d"> Chọn năm: <span style="">
        <select class="form-control" v-model="selected" @change="get(selected)" style="width: 200px; display: inherit; align-items: center" >
          <option v-for="y in year" v-bind:value="y"  v-bind:key ="y" >
            {{ y }}
          </option>
        </select>
      </span>

      </h4>
    </div>

    <br>


    <div>
      <el-table
          :data="Point"
          style="width: 100%">
        <el-table-column align="center"
            label="Thời gian"
            width="300">
          <template slot-scope="scope">
            <p height="50px">
              tháng {{formatMonth(scope.row.date)}} năm {{formatYear(scope.row.date)}}
            </p>
          </template>
        </el-table-column>
        <el-table-column align="center"
            prop="sum"
            label="Điểm V-poin"
            width="300">
        </el-table-column>
        <el-table-column align="center"
            label="Tùy chọn">
          <template slot-scope="scope">
            <router-link :to="`/api/mark/${formatYear(scope.row.date)}/${formatMonth(scope.row.date)}`" class="btn btn-warning">Xem chi tiết</router-link>
          </template>
        </el-table-column>
      </el-table>
      <br>
      <br>

      <h4 align="center" style="color: #6c757d"> Tổng điểm: {{sum}} </h4>
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
      selected: '',
      sum: 0
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
      let date = new Date();
      this.selected = date.getFullYear()
      if (this.currentUser != null) {
        this.idUser = this.currentUser.id;
      }
      let response = await userService.getVpoint(this.idUser)
      this.Point = response.data
      for (let i = 0; i < this.Point.length; i++) {
        this.sum += this.Point[i].sum
      }
      let response1 = await userService.getYear(this.idUser)
      for (let i = 0; i < response1.data.length; i++) {
        this.year.push(this.formatYear(response1.data[i].date))
      }
    },

    get(params){
      this.sum = ''
      this.getVpointByYear(params)
    },

    async getVpointByYear(params) {
      if (this.currentUser != null) {
        console.log(this.currentUser)
        this.idUser = this.currentUser.id;
      }
      let params1 = this.getRequestParams(params)
      let response = await userService.getVpointByYear(this.idUser, params1)
      this.Point = response.data
      for (let i = 0; i < this.Point.length; i++) {
        this.sum += this.Point[i].sum
      }
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

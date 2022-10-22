<template>
  <div>
    <table class="table table-bordered" :data="Point">
      <tr >
        <th scope="col">Stt</th>
        <th scope="col">Mục</th>
        <th scope="col">Hạng mục</th>
        <th scope="col">Điểm V-Point</th>
      </tr>
      <tr >
        <th scope="row" rowspan="2">1</th>
        <td rowspan="2">Hiệu suất công việc</td>
        <td>KPI cá nhân hàng tháng</td>
        <td v-for="(p, index) in Point" :key="index">{{p.item.id===1? p.point : removeEventListener}}</td>
      </tr>
      <tr>
        <td>Nhân viên xuất sắc, bộ phận xuất sắc</td>
        <td v-for="(p, index) in Point" :key="index">{{p.item.id===2? p.point : removeEventListener}}</td>
      </tr>
      <tr>
        <td rowspan="2">02</td>
        <td rowspan="2">Làm việc nhóm</td>
        <td>Điểm bcs bộ phận</td>
        <td v-for="(p, index) in Point" :key="index">{{p.item.id===3? p.point : removeEventListener}}</td>
      </tr>
      <tr>
        <td>Hoạt động chung</td>
        <td v-for="(p, index) in Point" :key="index">{{p.item.id===4? p.point : removeEventListener}}</td>
      </tr>
      <tr>
        <td>03</td>
        <td>Đào tạo và phát triển</td>
        <td>Đào tạo</td>
        <td v-for="(p, index) in Point" :key="index">{{p.item.id===5? p.point : removeEventListener}}</td>
      </tr>
      <tr>
        <td>04</td>
        <td>Sáng tạo</td>
        <td>Cải tiến đổi mới</td>
        <td v-for="(p, index) in Point" :key="index">{{p.item.id===6? p.point : removeEventListener}}</td>
      </tr>
      <tr>
        <td rowspan="2">05</td>
        <td rowspan="2">Tuân thủ</td>
        <td>Tôi yêu VMG</td>
        <td v-for="(p, index) in Point" :key="index">{{p.item.id===7? p.point : removeEventListener}}</td>
      </tr>
      <tr>
        <td>Kỷ luật</td>
        <td v-for="(p, index) in Point" :key="index">{{p.item.id===8? p.point : removeEventListener}}</td>
      </tr>
      <tr >
        <td>06</td>
        <td colspan="2" style="background-color: #dc3545"><h4>Tổng điểm</h4></td>
        <td>{{Point.reduce((total, p)=>{return total += p.point},0)}}</td>
      </tr>
    </table>
  </div>


</template>

<script>

import {UserService as userService} from "@/service/user-service";

export default {
  name: "DetailPoint",
  data: function (){
    return{
      Point: [],
      idUser: '',
      year : this.$route.params.year,
      month: this.$route.params.month
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

  async created() {
    await this.getVPoint()
  },


  methods: {

    async getVPoint() {

        console.log(this.currentUser)
        this.idUser = this.currentUser.id;
        const params = {};
        params["year"] = this.year
        params["month"] = this.month
      console.log(params)
      let response = await userService.getVpointByTime(this.idUser, params)
      this.Point = response.data
      console.log(this.Point)

    }
  }
}
</script>

<style scoped>

</style>

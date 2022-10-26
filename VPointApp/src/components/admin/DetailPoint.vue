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
        <td>{{td1}}</td>
      </tr>
      <tr>
        <td>Nhân viên xuất sắc, bộ phận xuất sắc</td>
        <td>{{td2}}</td>
      </tr>
      <tr>
        <td rowspan="2">02</td>
        <td rowspan="2">Làm việc nhóm</td>
        <td>Điểm bcs bộ phận</td>
        <td>{{td3}}</td>
      </tr>
      <tr>
        <td>Hoạt động chung</td>
        <td>{{td4}}</td>
      </tr>
      <tr>
        <td>03</td>
        <td>Đào tạo và phát triển</td>
        <td>Đào tạo</td>
        <td>{{td5}}</td>
      </tr>
      <tr>
        <td>04</td>
        <td>Sáng tạo</td>
        <td>Cải tiến đổi mới</td>
        <td>{{td6}}</td>
      </tr>
      <tr>
        <td rowspan="2">05</td>
        <td rowspan="2">Tuân thủ</td>
        <td>Tôi yêu VMG</td>
        <td>{{td7}}</td>
      </tr>
      <tr>
        <td>Kỷ luật</td>
        <td>{{td8}}</td>
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
      idUser: this.$route.params.idUser,
      year : this.$route.params.year,
      month: this.$route.params.month,
      td1: '',
      td2: '',
      td3: '',
      td4: '',
      td5: '',
      td6: '',
      td7: '',
      td8: '',
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
      const params = {};
      params["year"] = this.year
      params["month"] = this.month
      console.log(params)
      let response = await userService.getVpointByTime(this.idUser, params)
      this.Point = response.data
      console.log(this.Point);
      for (let i = 0; i < this.Point.length; i++) {
        if (this.Point[i].item.id === 1){
          this.td1 = this.Point[i].point
        }
        if (this.Point[i].item.id === 2){
          this.td2 = this.Point[i].point
        }
        if (this.Point[i].item.id === 3){
          this.td3 = this.Point[i].point
        }
        if (this.Point[i].item.id === 4){
          this.td4 = this.Point[i].point
        }
        if (this.Point[i].item.id === 5){
          this.td5 = this.Point[i].point
        }
        if (this.Point[i].item.id === 6){
          this.td6 = this.Point[i].point
        }
        if (this.Point[i].item.id === 7){
          this.td7 = this.Point[i].point
        }
        if (this.Point[i].item.id === 8){
          this.td8 = this.Point[i].point
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
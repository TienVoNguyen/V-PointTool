<template>
  <el-main>
    <div style="margin: 20px;"></div>
    <el-form :label-position="labelPosition" :rules="rules" label-width="100px" :model="mark" ref="mark">
      <el-form-item class="inline profile" >
        <el-row :gutter="20" type="flex" justify="space-around">
          <el-col :span="4">
          <el-form-item label="Mã nhân viên">
            <el-input v-model="mark.staff_id" :disabled="true"></el-input>
          </el-form-item>    
          </el-col>
          <el-col :span="4">
            <el-form-item label="Tên nhân viên">
              <el-input v-model="user.name" :disabled="true"></el-input>
            </el-form-item>    
          </el-col>
          <el-col :span="4">
            <el-form-item label="Bộ phận">
              <el-input v-model="user.department" :disabled="true" ></el-input>
            </el-form-item>    
          </el-col>
          <el-col :span="4">
            <el-form-item label="Ngày">
              <el-date-picker
                type="month"
                v-model="date"
                @change="handleData"
                placeholder="Chọn ngày"
                required
                >
              </el-date-picker>
            </el-form-item>    
          </el-col>
        </el-row>
      </el-form-item>
      <h2>Mục I. Hiệu suất công việc</h2>
      <h4>1. KPI cá nhân</h4>
      <el-row>
        <el-col :span="4" :offset="2">
            <el-form-item prop="kpi">
              <el-input placeholder="Nhập KPI(%)" type="number" v-model="mark.kpi"></el-input>
            </el-form-item>    
          </el-col>
      </el-row>
      <h4>2. Nhân viên xuất sắc & Bộ phận xuất sắc</h4>
      <el-row >
          <el-col :span="16" :offset="4">
            <el-row :gutter="80">
              <el-col :span="10" :offset="2">
                <h6>2.1 Nhân viên xuất sắc tháng</h6>
                <el-form-item label="" class="item-left">
                  <el-checkbox v-model="mark.bestDepartmentMonth">Nhân viên xuất sắc tháng</el-checkbox>
                </el-form-item>
              </el-col>   
              <el-col :span="10">
                <h6>3.1 Bộ phận xuất sắc quý</h6>
                <el-form-item label="" class="item-left">
                  <el-checkbox v-model="mark.excellentDepartmentMonth">Bộ phận xuất sắc quý</el-checkbox>
                </el-form-item>
              </el-col>   
            </el-row>
          </el-col>
          <el-col :span="16" :offset="4">
            <el-row justify="center" :gutter="80">
              <el-col :span="10" :offset="2">
                <h6>2.2 Nhân viên xuất sắc quý</h6>
                <el-form-item>
                  <el-select v-model="mark.bestDepartmentQuarter" placeholder="Nhân viên xuất sắc" class="select-item">
                  <el-option
                    v-for="item in bestDepartmentMonth"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
                </el-form-item>
              </el-col>   
              <el-col :span="10">
                <h6>3.2 Bộ phận xuất sắc năm</h6>
                <el-form-item label="" class="item-left">
                  <el-checkbox v-model="mark.excellentDepartmentYear">Bộ phận xuất sắc năm</el-checkbox>
                </el-form-item>
              </el-col>   
            </el-row>
          </el-col>
          <el-col :span="16" :offset="4">
            <el-row justify="center" :gutter="80">
              <el-col :span="10" :offset="2">
                <h6>2.3 Nhân viên xuất sắc năm</h6>
                <el-form-item label="">
                  <el-select v-model="mark.bestDepartmentYear" placeholder="Nhân viên xuất sắc" class="select-item">
                  <el-option
                    v-for="item in bestDepartmentYear"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
                </el-form-item>
              </el-col>     
            </el-row>
          </el-col>
      </el-row>
      <h2>Mục II. Làm việc nhóm</h2>
      <el-row justify="center">
        <el-col :span="16" :offset="4">
            <el-row justify="center" :gutter="80">
              <el-col :span="10" :offset="2">
                <h6>3. Điểm BSC bộ phận</h6>
                <el-form-item prop="bcsDepartment">
                  <el-input placeholder="Nhập điểm BSC bộ phận(%)" v-model.number="mark.bcsDepartment"></el-input>
                </el-form-item>
              </el-col>   
              <el-col :span="10">
                <h6>4. Hoạt động chung</h6>
                <el-form-item prop="jointActivities">
                  <el-input placeholder="Nhập điểm hoạt động chung" type="number" v-model="mark.jointActivities"></el-input>
                </el-form-item>
              </el-col>   
            </el-row>
          </el-col>
      </el-row>
      <h2>Mục III. Đào tạo và phát triển</h2>
      <h4>5. Đào tạo</h4>
      <el-row >
        <el-col :span="16" :offset="4">
            <el-row justify="center" :gutter="80">
              <el-col :span="10" :offset="2">
                <h6>5.1. Người đào tạo</h6>
                <el-form-item >
                  <el-input placeholder="Nhập điểm DGC" type="number" v-model="mark.train"></el-input>
                </el-form-item>
              </el-col>   
              <el-col :span="10">
                <h6>5.2. Người tham gia đào tạo</h6>
                <el-form-item prop="trainStaff">
                  <el-input placeholder="Nhập điểm đào tạo(%)" type="number" v-model="mark.trainStaff"></el-input>
                </el-form-item>
              </el-col>   
            </el-row>
          </el-col>
          <el-col :span="16" :offset="4">
            <el-row justify="center" :gutter="80">
              <el-col :span="10" :offset="2">
                <h6>5.3. Chương trình phát triển cùng VMG</h6>
                <el-form-item prop="trainVmg">
                  <el-input placeholder="Nhập điểm" type="number" v-model="mark.trainVmg"></el-input>
                </el-form-item>
              </el-col>    
            </el-row>
          </el-col>
      </el-row>
      <h2>Mục IV. Sáng tạo</h2>
      <h4>6. Cải tiến đổi mới</h4>
      <el-row>
        <el-col :span="16" :offset="4">
            <el-row :gutter="80">
              <el-col :span="10" :offset="2">
                <el-form-item  class="item-left">
                  <el-checkbox v-model="mark.improve">Nhân sự có ý tưởng cải tiến đổi mới tháng</el-checkbox>
                </el-form-item>
              </el-col>   
            </el-row>
          </el-col>
      </el-row>
      <h2>Mục V. Tuân thủ</h2>
      <h4>7. Tôi yêu VMG</h4>
      <el-row >
        <el-col :span="16" :offset="4">
          <el-row justify="center" :gutter="80">
            <el-col :span="10" :offset="2">
              <el-form-item prop="loveVmg">
                <el-input type="number" v-model="mark.loveVmg" placeholder="Nhập điểm test(%)" ></el-input>
              </el-form-item>
            </el-col>   
          </el-row>
        </el-col>
      </el-row>
        <h4>8. Kỷ luật</h4>
        <el-row>
          <el-col :span="16" :offset="4">
            <el-row justify="center" :gutter="80">
              <el-col :span="10" :offset="2">
                <h6>8.1. Vi phạm kỷ luật</h6>
                <el-form-item prop="disciplineViolate">
                  <el-input type="number" v-model="mark.disciplineViolate" placeholder="Nhập điểm"></el-input>
                </el-form-item>
              </el-col> 
            <el-col :span="10">
              <h6>8.2. Điểm thưởng</h6>
              <el-form-item prop="disciplineBonus">
                <el-input placeholder="Nhập điểm thưởng" type="number" v-model="mark.disciplineBonus"></el-input>
              </el-form-item>    
            </el-col>     
          </el-row>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button type="primary" v-on:click.prevent="onSubmit('mark')">Create</el-button>
        <el-button>Cancel</el-button>
      </el-form-item>
    </el-form>
  </el-main>
</template>
<script>

import markService from "../../service/mark-service";
import  {UserService as userService} from '../../service/user-service';

  export default {
    name: 'add-mark',
    data() {
      var checkPercent = (rule, value, callback) => {
        console.log(value);
        value = Number(value);
        setTimeout(() => {
          if (value < 0) {
            callback(new Error('Phải lớn hơn 0'));
          } else {
            callback();
          }
        }, 1000);
      };
      var checkJointActivities = (rule, value, callback) => {
        value = Number(value);
        setTimeout(() => {
          if (-0.5 > value || value > 4) {
            callback(new Error('Giá trị phải từ -0.5 đến 4'));
          } else {
            callback();
          }
        }, 1000)
      };
      var checkNumber = (rule, value, callback) => {
        setTimeout(() => {
          if ( value && !Number.isInteger(value)) {
            callback(new Error('Hãy nhập số'));
          } else {
            callback();
          }
        }, 1000);
      };
      return {
        labelPosition: 'top',
        rules: {
          kpi: [
            {validator: checkPercent, trigger: 'blur' }
          ],
          bcsDepartment: [
            {validator: checkPercent, trigger: 'blur' }
          ],
          jointActivities: [
            {validator: checkJointActivities, trigger: 'blur' }
          ],
          train: [
            {validator: checkNumber, trigger: 'blur'}
          ],
          trainStaff: [
            {validator: checkPercent, trigger: 'blur' }
          ],
          trainVmg: [
            {validator: checkPercent, trigger: 'blur' }
          ],
          loveVmg: [
            {validator: checkPercent, trigger: 'blur' }
          ],
          disciplineBonus: [
            {validator: checkPercent, trigger: 'blur' }
          ],
          disciplineViolate: [
            {validator: checkPercent, trigger: 'blur' }
          ],
        },
        user: {
          department: '',
          name: '',
          staff_id: '',
          id: ''
        },
        date: null,
        mark: {
          staff_id: '',
          kpiID: 1,
          kpi: null,
          disciplineBonus: null,
          bestDepartmentID: 2,
          bestDepartmentMonth: '',
          jointActivitiesID: 4,
          jointActivities: null,
          bcsDepartmentID: 3,
          bcsDepartment: null,
          trainID: 5,
          train: null,
          improveID: 6,
          improve: false,
          loveVmgID: 7,
          loveVmg: null,
          trainStaffID: 11,
          trainStaff: null,
          bestDepartmentQuarter: null,
          bestDepartmentYear: null,
          excellentDepartmentMonthID: 9,
          excellentDepartmentMonth: false,
          excellentDepartmentYearID: 10,
          excellentDepartmentYear: false,
          trainVmgID: 12,
          trainVmg: null,
          disciplineBonusID: 13,
          disciplineViolateID: 8,
          disciplineViolate: null,
          month: null,
          year: null
        },
        bestDepartmentMonth: [
          {
            value: 'DCQ',
            label: 'Đề cử quý'
          },
          {
            value: 'Q',
            label: 'Xuất sắc quý các hạng mục'
          },
          {
            value: 'NSQ',
            label: 'Viên xuất sắc quý'
          }
        ],
        bestDepartmentYear: [
          {
            value: 'DCN',
            label: 'Đề cử năm'
          },
          {
            value: 'N',
            label: 'Xuất sắc năm các hạng mục'
          },
          {
            value: 'NSN',
            label: 'Viên xuất sắc năm'
          }
        ],
      };
    },
    methods: {
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.mark.bestDepartmentMonth = this.mark.bestDepartmentMonth ? "T" : '';
            if (this.date === null) {
              let nowDate = Date.now();
              this.mark.year = nowDate.getFullYear();
              this.mark.month = nowDate.getMonth();
            }
            let date = new Date(this.date);
            this.mark.month = date.getMonth();
            this.mark.year = date.getFullYear();
            this.mark.staff_id = this.user.staff_id;
            markService.create(this.mark)
              .then(result => {
                console.log(result.data);
              })
              .catch(error => {
                console.log(error);
              })  
          } else {
            console.log(valid);
            return false;
          }
        })
      },
      getUser(id) {
        userService.getUserById(id)
          .then(response => {
            this.user.staff_id = response.data.staff_id;
            this.user.department = response.data.department;
            this.user.name = response.data.name;
            this.mark.staff_id = this.user.staff_id;
          })
          .catch (error => {
            console.log(error);
          })
      },
      handleData(data) {
        console.log("heehehe");
        data = new Date(data)
        this.mark.month = data.getMonth() + 1;
        this.mark.year = data.getFullYear();
        console.log("UserId: " + this.user.id);
        console.log(this.user.id);
        let dataReq = {
          userId: this.user.id,
          month: this.mark.month,
          year: this.mark.year
        }
        console.log(dataReq);
        markService.getMarkByUserAndDate(dataReq)
          .then(response => {
            this.mark = response.data;
            console.log(response.data);
          })
          .catch (error => {
            console.log(error);
          })
      },
      handleInput(value) {
        console.log(value);
      }
    },
    mounted() {
      this.getUser(this.$route.params.id);
      this.handleData(this.date);
    },
    created() {
      this.date = Date.now();
      this.user.id = this.$route.params.id;
    }
  }
</script>
<style scoped>
  h2, h4, h6 {
    text-align: left;
  }
  h2{
    margin-left: 50px;
  }
  h4 {
    margin-left: 60px;
  }
  .item-left{
    display: flex;
  }
  .select-item {
    width: 100%;
  }
</style>
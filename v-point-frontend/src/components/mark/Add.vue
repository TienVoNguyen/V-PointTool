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
        </el-row>
        <el-row >
          <el-col :span="4" :offset="10">
            <el-form-item label="Ngày" class="date-input">
              <el-date-picker
                type="month"
                v-model="date"
                @change="handleData"
                placeholder="Chọn ngày"
                required
                style="width: 100%"
                >
              </el-date-picker>
            </el-form-item>    
          </el-col>
        </el-row>
      </el-form-item>
      <el-row>
        <el-col :offset="2">
          <p class="text-header">Mục I. Hiệu suất công việc</p>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6" :offset="4">
          <el-form-item prop="kpi" >
            <p class="text-content">1. KPI cá nhân</p>
            <el-input placeholder="Nhập KPI(%)" type="number" v-model="mark.kpi" ></el-input>
          </el-form-item>    
        </el-col>
      </el-row>
      <el-row justify="space-around">
          <el-col >
            <el-row >
              <el-col :offset="4">
                <p class="text-content">2. Nhân viên xuất sắc & Bộ phận xuất sắc</p>
              </el-col>
              <el-col :span="6" :offset="4">
                <el-form-item >
                  <h6>2.1 Nhân viên xuất sắc tháng</h6>
                  <el-checkbox v-model="mark.bestDepartmentMonth" class="checkbox-form">Nhân viên xuất sắc tháng</el-checkbox>
                </el-form-item>
              </el-col>   
              <el-col :span="6" :offset="6">
                <el-form-item >
                  <h6>3.1 Bộ phận xuất sắc quý</h6>
                  <el-checkbox v-model="mark.excellentDepartmentMonth" class="checkbox-form">Bộ phận xuất sắc quý</el-checkbox>
                </el-form-item>
              </el-col>   
            </el-row>
          </el-col>
          <el-col>
            <el-row  >
              <el-col :span="6" :offset="4">
                <el-form-item >
                  <h6>2.2 Nhân viên xuất sắc quý</h6>
                  <el-select v-model="mark.bestDepartmentQuarter" clearable placeholder="Nhân viên xuất sắc" class="select-item">
                  <el-option
                    v-for="item in bestDepartmentMonth"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
                </el-form-item>
              </el-col>   
              <el-col :span="6" :offset="6">
                <el-form-item >
                  <h6>3.2 Bộ phận xuất sắc năm</h6>
                  <el-checkbox v-model="mark.excellentDepartmentYear" class="checkbox-form">Bộ phận xuất sắc năm</el-checkbox>
                </el-form-item>
              </el-col>
              <el-col :span="6" :offset="4">
                <el-form-item >
                  <h6>2.3 Nhân viên xuất sắc năm</h6>
                  <el-select v-model="mark.bestDepartmentYear" clearable placeholder="Nhân viên xuất sắc" class="select-item">
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
      <el-row>
        <el-col :offset="2">
          <p class="text-header">Mục II. Làm việc nhóm</p>
        </el-col>
      </el-row>
      <el-row justify="space-around">
        <el-col>
            <el-row>
              <el-col :span="6" :offset="4">
                <el-form-item prop="bcsDepartment"  >
                  <p class="text-content">3. Điểm BSC bộ phận</p>
                  <el-input placeholder="Nhập điểm BSC bộ phận(%)" v-model.number="mark.bcsDepartment"></el-input>
                </el-form-item>
              </el-col>   
              <el-col :span="6" :offset="6">
                <p class="text-content">4. Hoạt động chung</p>
                <el-form-item prop="jointActivities" >
                  <el-input placeholder="Nhập điểm hoạt động chung" type="number" v-model="mark.jointActivities"></el-input>
                </el-form-item>
              </el-col>   
            </el-row>
          </el-col>
      </el-row>
      <el-row>
        <el-col :offset="2">
          <p class="text-header">Mục III. Đào tạo và phát triển</p>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
            <el-row >
              <el-col :offset="4">
                <p class="text-content">5. Đào tạo</p>
              </el-col>
              <el-col :span="6" :offset="4">
                <el-form-item >
                  <h6>5.1. Người đào tạo</h6>
                  <el-input placeholder="Nhập điểm DGC" type="number" v-model="mark.train"></el-input>
                </el-form-item>
              </el-col>   
              <el-col :span="6" :offset="6">
                <el-form-item prop="trainStaff" >
                  <h6>5.2. Người tham gia đào tạo</h6>
                  <el-input placeholder="Nhập điểm đào tạo(%)" type="number" v-model="mark.trainStaff"></el-input>
                </el-form-item>
              </el-col>   
              <el-col :span="6" :offset="4">
                <h6>5.3. Chương trình phát triển cùng VMG</h6>
                <el-form-item prop="trainVmg" >
                  <el-input placeholder="Nhập điểm" type="number" v-model="mark.trainVmg"></el-input>
                </el-form-item>
              </el-col>    
            </el-row>
          </el-col>
      </el-row>
      <el-row>
        <el-col :offset="2">
          <p class="text-header">Mục IV. Sáng tạo</p>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
            <el-row>
              <el-col :span="6" :offset="4">
                <el-form-item  >
                  <p class="text-content">6. Cải tiến đổi mới</p>
                  <el-checkbox v-model="mark.improve">Nhân sự có ý tưởng cải tiến đổi mới tháng</el-checkbox>
                </el-form-item>
              </el-col>   
            </el-row>
          </el-col>
      </el-row>
      <el-row>
        <el-col :offset="2">
          <p class="text-header">Mục V. Tuân thủ</p>
        </el-col>
      </el-row>
      <el-row justify="space-around">
        <el-col>
          <el-row >
            <el-col :span="6" :offset="4">
              <el-form-item prop="loveVmg" >
                <p class="text-content">7. Tôi yêu VMG</p>
                <el-input type="number" v-model="mark.loveVmg" placeholder="Nhập điểm test(%)" ></el-input>
              </el-form-item>
            </el-col>   
          </el-row>
        </el-col>
      </el-row>
        <el-row justify="space-around">
          <el-col>
            <el-row>
              <el-col :offset="4">
                <p class="text-content">8. Kỷ luật</p>
              </el-col>
              <el-col :span="6" :offset="4">
                <el-form-item prop="disciplineViolate" >
                  <h6>8.1. Vi phạm kỷ luật</h6>
                  <el-input type="number" v-model="mark.disciplineViolate" placeholder="Nhập điểm"></el-input>
                </el-form-item>
              </el-col> 
            <el-col :span="6" :offset="6">
              <el-form-item prop="disciplineBonus" >
                <h6>8.2. Điểm thưởng</h6>
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
        value = Number(value);
        setTimeout(() => {
          if (value < 0) {
            callback(new Error('Phải lớn hơn 0'));
          } else {
            callback();
          }
        }, 500);
      };
      var checkJointActivities = (rule, value, callback) => {
        value = Number(value);
        setTimeout(() => {
          if (-0.5 > value || value > 4) {
            callback(new Error('Giá trị phải từ -0.5 đến 4'));
          } else {
            callback();
          }
        }, 500)
      };
      var checkNumber = (rule, value, callback) => {
        // value = Number(value);
        setTimeout(() => {
          if ( value && !Number.isInteger(value)) {
            callback(new Error('Hãy nhập số'));
          } else {
            callback();
          }
        }, 500);
      };
      var checkTrainVmg = (rule, value, callback) => {
        value = Number(value);
        setTimeout(() => {
          if( value < 0 || value > 50) {
            callback(new Error('Giá trị phải từ 0 - 50'));
          } else {
            callback();
          }
        }, 500)
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
            {validator: checkTrainVmg, trigger: 'blur' }
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
          bestDepartmentMonthID: 2,
          bestDepartmentMonth: false,
          bestDepartmentQuarterID: 16,
          bestDepartmentQuarter: null,
          bestDepartmentYearID: 17,
          bestDepartmentYear: null,
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
        data = new Date(data)
        this.mark.month = data.getMonth();
        this.mark.year = data.getFullYear();
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
      handleInput() {
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
  p {
    text-align: left;
  }

  .text-header{
    /* margin-left: 50px; */
    font-family: 'Roboto';
    font-style: normal;
    font-size: 25px;
    line-height: 29px;
    font-weight: 600;

    /* đỏ */

    color: #ED1C24;
  }

  .text-content {
    font-family: 'Inter';
    font-style: normal;
    font-size: 23px;
    line-height: 28px;
    color: #000;
    padding-top: 15px;
    font-weight: 550;
  }
  .item-left{
    display: flex;
  }
  .select-item {
    width: 100%;
  }

  .text-title {
    padding-left: 90px;
  }

  .checkbox-form {
    left: -25px;
    color: #000;
  }
  h6 {
    text-align: start;
    font-family: 'Inter';
    font-style: normal;
    font-weight: 400;
    font-size: 20px;
    line-height: 24px;

    color: #000000;
  }
</style>
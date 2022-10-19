<template>
  <el-main>
    <div style="margin: 20px;"></div>
    <el-form :label-position="labelPosition" label-width="100px" :model="formLabelAlign">
      <el-form-item class="inline profile">
        <el-row :gutter="20" type="flex" justify="space-around">
          <el-col :span="4">
          <el-form-item label="Mã nhân viên">
            <el-input v-model="mark.staff_id"></el-input>
          </el-form-item>    
          </el-col>
          <el-col :span="4">
            <el-form-item label="Tên nhân viên">
              <el-input v-model="formLabelAlign.name"></el-input>
            </el-form-item>    
          </el-col>
          <el-col :span="4">
            <el-form-item label="Bộ phận">
              <el-input v-model="formLabelAlign.name"></el-input>
            </el-form-item>    
          </el-col>
        </el-row>
      </el-form-item>
      <h2>Hiệu suất công việc</h2>
      <h4>1. KPI cá nhân</h4>
      <el-row>
        <el-col :span="4" :offset="2">
            <el-form-item label="">
              <el-input placeholder="Nhập KPI(%)" v-model="mark.kpi"></el-input>
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
                  <el-checkbox v-model="checked">Nhân viên xuất sắc tháng</el-checkbox>
                </el-form-item>
              </el-col>   
              <el-col :span="10">
                <h6>3.1 Bộ phận xuất sắc quý</h6>
                <el-form-item label="" class="item-left">
                  <el-checkbox v-model="checked">Bộ phận xuất sắc quý</el-checkbox>
                </el-form-item>
              </el-col>   
            </el-row>
          </el-col>
          <el-col :span="16" :offset="4">
            <el-row justify="center" :gutter="80">
              <el-col :span="10" :offset="2">
                <h6>2.2 Nhân viên xuất sắc quý</h6>
                <el-form-item label="">
                  <el-input placeholder="" v-model="formLabelAlign.name"></el-input>
                </el-form-item>
              </el-col>   
              <el-col :span="10">
                <h6>3.2 Bộ phận xuất sắc năm</h6>
                <el-form-item label="" class="item-left">
                  <el-checkbox v-model="checked">Bộ phận xuất sắc năm</el-checkbox>
                </el-form-item>
              </el-col>   
            </el-row>
          </el-col>
          <el-col :span="16" :offset="4">
            <el-row justify="center" :gutter="80">
              <el-col :span="10" :offset="2">
                <h6>2.3 Nhân viên xuất sắc năm</h6>
                <el-form-item label="">
                  <el-input placeholder="" v-model="formLabelAlign.name"></el-input>
                </el-form-item>
              </el-col>     
            </el-row>
          </el-col>
      </el-row>
      <h2>Làm việc nhóm</h2>
      <el-row justify="center">
        <el-col :span="16" :offset="4">
            <el-row justify="center" :gutter="80">
              <el-col :span="10" :offset="2">
                <h6>3. Điểm BSC bộ phận</h6>
                <el-form-item label="">
                  <el-input placeholder="" v-model="formLabelAlign.name"></el-input>
                </el-form-item>
              </el-col>   
              <el-col :span="10">
                <h6>4. Hoạt động chung</h6>
                <el-form-item label="">
                  <el-input placeholder="" v-model="formLabelAlign.name"></el-input>
                </el-form-item>
              </el-col>   
            </el-row>
          </el-col>
      </el-row>
    </el-form>
  </el-main>
</template>
<script>
  export default {
    name: 'add-mark',
    data() {
      return {
        labelPosition: 'top',
        formLabelAlign: {
          name: '',
          region: '',
          type: ''
        },
        mark: {
          staff_id: "VMG_2022",
          kpiID: 1,
          kpi: null,
          bestDepartmentID: 2,
          bestDepartmentMonth: '',
          jointActivitiesID: 4,
          jointActivities: null,
          bcsDepartmentID: 3,
          bcsDepartment: null,
          trainID: 5,
          train: null,
          improveID: 6,
          improve: null,
          loveVmgID: 7,
          loveVmg: null,
          trainStaffID: 11,
          trainStaff: null,
          bestDepartmentQuarter: null,
          bestDepartmentYear: null,
          excellentDepartmentMonthID: 9,
          excellentDepartmentMonth: null,
          excellentDepartmentYearID: 10,
          excellentDepartmentYear: null,
          trainVmgID: 12,
          trainVmg: null,
          disciplineBonusID: 13,
          disciplineBonus: null,
          disciplineViolateID: 8,
          disciplineViolate: null,
          month: 12,
          year: 2022
        }
      };
    }
  }
</script>
<style scoped>
  /* .profile{
    display: flex;
    justify-content: space-around;
  } */
  h2, h4, h6 {
    text-align: left;
  }
  h2{
    margin-left: 50px;
  }
  h4 {
    margin-left: 60px;
  }
  h6 {
    /* margin-left: 70px; */
  }

  .item-left{
    display: flex;
  }

</style>
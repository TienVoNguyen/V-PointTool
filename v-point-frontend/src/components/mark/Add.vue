<template>
  <el-main>
    <div style="margin: 20px;"></div>
    <h1>Thêm mới dữ liệu tính điểm V-Point</h1>
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
              <el-input ></el-input>
            </el-form-item>    
          </el-col>
          <el-col :span="4">
            <el-form-item label="Bộ phận">
              <el-input ></el-input>
            </el-form-item>    
          </el-col>
          <el-col :span="2">
            <el-form-item label="Chọn năm">
              <el-input placeholder="Năm" v-model="mark.year"></el-input>
            </el-form-item>    
          </el-col>
          <el-col :span="2">
            <el-form-item label="Chọn tháng">
              <el-input placeholder="Tháng" v-model="mark.month"></el-input>
            </el-form-item>    
          </el-col>
        </el-row>
      </el-form-item>
      <h2>Mục I. Hiệu suất công việc</h2>
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
                  <el-checkbox v-model="mark.bestDepartmentMonth">Nhân viên xuất sắc tháng</el-checkbox>
                </el-form-item>
              </el-col>   
              <el-col :span="10">
                <h6>3.1 Bộ phận xuất sắc tháng</h6>
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
                <el-form-item label="">
                  <el-input placeholder="Nhân viên xuất sắc quý" v-model="mark.bestDepartmentQuarter"></el-input>
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
                  <el-input placeholder="Nhân viên xuất sắc năm" v-model="mark.bestDepartmentYear"></el-input>
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
                <el-form-item label="">
                  <el-input placeholder="Nhập điểm BSC bộ phận(%)" v-model="mark.bcsDepartment"></el-input>
                </el-form-item>
              </el-col>   
              <el-col :span="10">
                <h6>4. Hoạt động chung</h6>
                <el-form-item label="">
                  <el-input placeholder="Nhập điểm hoạt động chung" v-model="mark.jointActivities"></el-input>
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
                <el-form-item label="">
                  <el-input placeholder="Nhập điểm DGC" v-model="mark.train"></el-input>
                </el-form-item>
              </el-col>   
              <el-col :span="10">
                <h6>5.2. Người tham gia đào tạo</h6>
                <el-form-item label="">
                  <el-input placeholder="Nhập điểm đào tạo(%)" v-model="mark.trainStaff"></el-input>
                </el-form-item>
              </el-col>   
            </el-row>
          </el-col>
          <el-col :span="16" :offset="4">
            <el-row justify="center" :gutter="80">
              <el-col :span="10" :offset="2">
                <h6>5.3. Chương trình phát triển cùng VMG</h6>
                <el-form-item label="">
                  <el-input placeholder="Nhập điểm" v-model="mark.trainVmg"></el-input>
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
                <el-form-item label="" class="item-left">
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
                <el-form-item label="">
                  <el-input placeholder="Nhập điểm test(%)" v-model="mark.loveVmg"></el-input>
                </el-form-item>
              </el-col>   
              <!-- <el-col :span="10">
              </el-col>    -->
            </el-row>
          </el-col>
          </el-row>
          <h4>8. Kỷ luật</h4>
          <el-row>
          <el-col :span="16" :offset="4">
            <el-row justify="center" :gutter="80">
              <el-col :span="10" :offset="2">
                <h6>8.1. Vi phạm nội quy, quy định</h6>
                <el-form-item label="">
                  <el-input placeholder="Nhập điểm" v-model="mark.disciplineBonus"></el-input>
                </el-form-item>
              </el-col> 
              <el-col :span="10">
                <h6>8.2. Khen thưởng nóng</h6>
                <el-form-item label="">
                  <el-input placeholder="Nhập điểm" v-model="mark.disciplineViolate"></el-input>
                </el-form-item>
              </el-col>     
            </el-row>
          </el-col>
      </el-row>
      <el-form-item class="btn-form">
        <el-button type="primary" @click="submitForm('ruleForm')">Submit</el-button>
        <el-button @click="resetForm('ruleForm')">Reset</el-button>
      </el-form-item>
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

  .btn-form {
    margin-top: 40px;
  }
</style>
<template>
  <div class="hello">
    <input type="file" @change="addFile($event)" placeholder="Upload file"
           accept=".csv,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
  </div>
</template>

<script>
const ExcelJS = require('exceljs');

export default {
  name: 'HelloWorld',
  methods: {
    addFile(event) {
      let reader = new FileReader()
      reader.onloadend = async () => {
        let workbook = new ExcelJS.Workbook()
        await workbook.xlsx.load(reader.result)
        const worksheet = workbook.getWorksheet('ImportMark')
        worksheet.eachRow((row, index) =>{
          if(index > 2) {
            let mark = {
              staff_id: row.getCell(2).value,
              department: row.getCell(3).value,
              fullName: row.getCell(4).value,
              year: row.getCell(5).value,
              month: row.getCell(6).value,
              kpi: row.getCell(7).value,
              kpiID: 1,
              bestDepartmentID: 2,
              bestDepartmentMonth: row.getCell(8).value,
              bestDepartmentQuarter: row.getCell(9).value,
              bestDepartmentYear: row.getCell(10).value,
              excellentDepartmentMonthID: 2,
              excellentDepartmentMonth: row.getCell(11).value,
              excellentDepartmentYearID: 2,
              excellentDepartmentYear: row.getCell(12).value,
              bcsDepartmentID: 3,
              bcsDepartment: row.getCell(13).value,
              jointActivitiesID: 4,
              jointActivities: row.getCell(14).value,
              trainStaffID: 5,
              trainStaff: row.getCell(15).value,
              trainID: 5,
              train: row.getCell(16).value,
              stt: row.getCell(17).value,
              trainVmgID: 7,
              trainVmg: row.getCell(18).value,
              bonus: row.getCell(19).value,
              phat: row.getCell(20).value,
            }
            console.log(mark)
          }
        })
      }
      reader.readAsArrayBuffer(event.target.files[0])
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>

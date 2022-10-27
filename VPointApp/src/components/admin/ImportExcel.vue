<template>
  <div class="pl-4">
    <b-form-file @change="addFile($event)" accept=".csv,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,
                            application/vnd.ms-excel"
                 v-model="file" class="mb-2 w-100"></b-form-file>
    <div class="mt-2">
      <b-button variant="danger" @click="addMark" :disabled="!addStatus" class="mr-2">Thêm điểm</b-button>
      <b-button variant="success" @click="file = null; items=[]; addStatus=false">Reset</b-button>
      <b-button variant="success" @click="exportFile">export</b-button>
    </div>

    <b-table v-if="!visibleTable" sticky-header head-variant="light" class="mt-4" responsive :items="items" :fields="fields" style="max-height: 23rem">
      <template #cell(staff_id)="data">
        <b-form-input style="min-width: 7rem" v-if="items[data.index].isEdit && selectedCell === 'staff_id'"
                      type="text" v-model="items[data.index].staff_id"
                      name="example-input-1"
                      v-validate="{required: true, min: 3}"
                      :state="validateState('example-input-1')"
                      aria-describedby="input-1-live-feedback"
                      data-vv-as="Ma NV"></b-form-input>
        <span v-else :class="data.value.length<3?'text-danger':''" @click="editCellHandler(data, 'staff_id')">{{data.value}}</span>
      </template>
      <template #cell(fullName)="data">
        <b-form-input style="min-width: 7rem" v-if="items[data.index].isEdit && selectedCell === 'fullName'" type="text" v-model="items[data.index].fullName" class="form-control"></b-form-input>
        <span v-else @click="editCellHandler(data, 'fullName')">{{data.value}}</span>
      </template>
      <template #cell(department)="data">
        <b-form-input style="min-width: 7rem" v-if="items[data.index].isEdit && selectedCell === 'department'"
                      type="text" v-model="items[data.index].department"
                      name="example-input-2"
                      v-validate="{required: true, min: 3}"
                      :state="validateState('example-input-2')"
                      aria-describedby="input-2-live-feedback"
                      data-vv-as="Bộ phận"></b-form-input>
        <span v-else :class="data.value.length<3?'text-danger':''" @click="editCellHandler(data, 'department')">{{data.value}}</span>
      </template>
      <template #cell(year)="data">
        <b-form-input style="min-width: 5rem" v-if="items[data.index].isEdit && selectedCell === 'year'"
                      type="number" v-model="items[data.index].year"
                      name="example-input-3"
                      v-validate="{required: true, min: 3}"
                      :state="validateState('example-input-3')"
                      aria-describedby="input-3-live-feedback"
                      data-vv-as="Nam"></b-form-input>
        <span v-else :class="data.value.length<4?'text-danger':''" @click="editCellHandler(data, 'year')">{{data.value}}</span>
      </template>
      <template #cell(month)="data">
        <b-form-input style="min-width: 5rem" v-if="items[data.index].isEdit && selectedCell === 'month'"
                      type="number" v-model="items[data.index].month"
                      name="example-input-4"
                      v-validate="{required: true}"
                      :state="validateState('example-input-4')"
                      aria-describedby="input-4-live-feedback"
                      data-vv-as="Thang"></b-form-input>
        <span v-else style="min-width: 3rem" :class="data.value.length<1?'text-danger':''"
              @click="editCellHandler(data, 'month')">
          {{data.value}}</span>
      </template>
      <template #cell(kpi)="data">
        <b-form-input style="min-width: 5rem" v-if="items[data.index].isEdit && selectedCell === 'kpi'"
                      type="number" v-model="items[data.index].kpi" class="form-control"
                      name="example-input-5"
                      v-validate="{required: true}"
                      :state="validateState('example-input-5')"
                      aria-describedby="input-5-live-feedback"
                      data-vv-as="KPI"></b-form-input>
        <span v-else @click="editCellHandler(data, 'kpi')">{{data.value}}</span>
      </template>
      <template #cell(bestDepartmentMonth)="data">
        <b-form-input v-if="items[data.index].isEdit && selectedCell === 'bestDepartmentMonth'"
                      type="text" v-model="items[data.index].bestDepartmentMonth" class="form-control"
                      name="example-input-6"
                      v-validate="{required: true}"
                      :state="validateState('example-input-6')"
                      aria-describedby="input-6-live-feedback"
                      data-vv-as="NVXS"></b-form-input>
        <span v-else @click="editCellHandler(data, 'bestDepartmentMonth')">{{data.value}}</span>
      </template>
      <template #cell(bestDepartmentQuarter)="data">
        <b-form-input style="min-width: 7rem" v-if="items[data.index].isEdit && selectedCell === 'bestDepartmentQuarter'"
                      type="text" v-model="items[data.index].bestDepartmentQuarter"
                      name="example-input-7"></b-form-input>
        <span v-else @click="editCellHandler(data, 'bestDepartmentQuarter')">{{data.value}}</span>
      </template>
      <template #cell(bestDepartmentYear)="data">
        <b-form-input style="min-width: 7rem" v-if="items[data.index].isEdit && selectedCell === 'bestDepartmentYear'"
                      type="text" v-model="items[data.index].bestDepartmentYear"></b-form-input>
        <span v-else @click="editCellHandler(data, 'bestDepartmentYear')">{{data.value}}</span>
      </template>
      <template #cell(excellentDepartmentMonth)="data">
        <b-form-input style="min-width: 7rem" v-if="items[data.index].isEdit && selectedCell === 'excellentDepartmentMonth'" type="text" v-model="items[data.index].excellentDepartmentMonth"></b-form-input>
        <span v-else @click="editCellHandler(data, 'excellentDepartmentMonth')">{{data.value}}</span>
      </template>
      <template #cell(excellentDepartmentYear)="data">
        <b-form-input style="min-width: 7rem" v-if="items[data.index].isEdit && selectedCell === 'excellentDepartmentYear'" type="text" v-model="items[data.index].excellentDepartmentYear" class="form-control"></b-form-input>
        <span v-else @click="editCellHandler(data, 'excellentDepartmentYear')">{{data.value}}</span>
      </template>
      <template #cell(bcsDepartment)="data">
        <b-form-input style="min-width: 7rem" v-if="items[data.index].isEdit && selectedCell === 'bcsDepartment'" type="number" v-model="items[data.index].bcsDepartment"></b-form-input>
        <span v-else @click="editCellHandler(data, 'bcsDepartment')">{{data.value}}</span>
      </template>
      <template #cell(jointActivities)="data">
        <b-form-input style="min-width: 5rem" v-if="items[data.index].isEdit && selectedCell === 'jointActivities'" type="number" v-model="items[data.index].jointActivities"></b-form-input>
        <span v-else @click="editCellHandler(data, 'jointActivities')">{{data.value}}</span>
      </template>
      <template #cell(train)="data">
        <b-form-input style="min-width: 5rem" v-if="items[data.index].isEdit && selectedCell === 'train'" type="number"  v-model="items[data.index].train" class="form-control"></b-form-input>
        <span v-else @click="editCellHandler(data, 'train')">{{data.value}}</span>
      </template>
      <template #cell(trainStaff)="data">
        <b-form-input style="min-width: 5rem" v-if="items[data.index].isEdit && selectedCell === 'trainStaff'" type="number" v-model="items[data.index].trainStaff"></b-form-input>
        <span v-else @click="editCellHandler(data, 'trainStaff')">{{data.value}}</span>
      </template>
      <template #cell(improve)="data">
        <b-form-input style="min-width: 5rem" v-if="items[data.index].isEdit && selectedCell === 'improve'" type="text" v-model="items[data.index].improve"></b-form-input>
        <span v-else @click="editCellHandler(data, 'improve')">{{data.value}}</span>
      </template>
      <template #cell(loveVmg)="data">
        <b-form-input style="min-width: 5rem" v-if="items[data.index].isEdit && selectedCell === 'loveVmg'" type="number" v-model="items[data.index].loveVmg"></b-form-input>
        <span v-else @click="editCellHandler(data, 'loveVmg')">{{data.value}}</span>
      </template>
      <template #cell(trainVmg)="data">
        <b-form-input style="min-width: 5rem" v-if="items[data.index].isEdit && selectedCell === 'trainVmg'" type="number" v-model="items[data.index].trainVmg" class="form-control"></b-form-input>
        <span v-else @click="editCellHandler(data, 'trainVmg')">{{data.value}}</span>
      </template>
      <template #cell(disciplineBonus)="data">
        <b-form-input style="min-width: 5rem" v-if="items[data.index].isEdit && selectedCell === 'disciplineBonus'" type="number" v-model="items[data.index].disciplineBonus"></b-form-input>
        <span v-else @click="editCellHandler(data, 'disciplineBonus')">{{data.value}}</span>
      </template>
      <template #cell(disciplineViolate)="data">
        <b-form-input style="min-width: 5rem" v-if="items[data.index].isEdit && selectedCell === 'disciplineViolate'" type="number" v-model="items[data.index].disciplineViolate"></b-form-input>
        <span v-else @click="editCellHandler(data, 'disciplineViolate')">{{data.value}}</span>
      </template>

    </b-table>
    <b-table v-if="visibleTable" sticky-header head-variant="light" class="mt-4" responsive :items="listMark" :fields="fields2" style="max-height: 23rem"></b-table>
  </div>
</template>

<script>
import ExcelService from "@/service/excel.service";

const ExcelJS = require('exceljs');
import * as fs from 'file-saver';
export default {
  name: "ImportExcel",
  data() {
    return {
      successfully: false,
      items: [],
      file: null,
      fields: [
        { key: "staff_id", label: "Mã NV"},
        { key: "fullName", label: "Tên"},
        { key: "department", label: "Bộ Phận", required: true },
        { key: "month", label: "Tháng" },
        { key: "year", label: "Năm" },
        { key: "kpi", label: "KPI" },
        { key: "bestDepartmentMonth", label: "NVXS Tháng" },
        { key: "bestDepartmentQuarter", label: "NVXS Quý"},
        { key: "bestDepartmentYear", label: "NVXS Năm" },
        { key: "excellentDepartmentMonth", label: "BPXS Tháng" },
        { key: "excellentDepartmentYear", label: "BPXS Năm" },
        { key: "bcsDepartment", label: "BCS BP"},
        { key: 'jointActivities', label: 'HDC'},
        { key: "train", label: "TG ĐT" },
        { key: 'trainStaff', label: 'GV ĐT'},
        { key: "improve", label: "Sáng Tạo" },
        { key: "loveVmg", label: "I Love VMG" },
        { key: 'trainVmg', label: 'PT VMG'},
        { key: "disciplineBonus", label: "Thưởng"},
        { key: "disciplineViolate", label: "Phạt" },
        { key: 'edit', label: ''}
      ],
      fields2: [
        { key: "staff_id", label: "Mã NV"},
        { key: "fullName", label: "Tên"},
        { key: "department", label: "Bộ Phận", required: true },
        { key: "month", label: "Tháng" },
        { key: "year", label: "Năm" },
        { key: "pointKPI", label: "KPI" },
        { key: "pointBestDepartmentMonth", label: "NVXS Tháng" },
        { key: "pointBestDepartmentQuarter", label: "NVXS Quý"},
        { key: "pointBestDepartmentYear", label: "NVXS Năm" },
        { key: "pointExcellentDepartmentMonth", label: "BPXS Tháng" },
        { key: "pointExcellentDepartmentYear", label: "BPXS Năm" },
        { key: "pointBCSDepartment", label: "BCS BP"},
        { key: 'pointJointActivities', label: 'HDC'},
        { key: "pointTrain", label: "TG ĐT" },
        { key: 'pointTrainStaff', label: 'GV ĐT'},
        { key: "pointImprove", label: "Sáng Tạo" },
        { key: "pointLoveVmg", label: "I Love VMG" },
        { key: 'pointTrainVmg', label: 'PT VMG'},
        { key: "pointDisciplineBonus", label: "Thưởng"},
        { key: "pointDisciplineViolate", label: "Phạt" }
      ],
      addStatus: false,
      listMark: [],
      visibleTable: false
    }
  },
  methods: {
    validateState(ref) {
      if (
          this.veeFields[ref] &&
          (this.veeFields[ref].dirty || this.veeFields[ref].validated)
      ) {
        return !this.veeErrors.has(ref);
      }
      return null;
    },
    addFile(event) {
      let reader = new FileReader()
      reader.onloadend = async () => {
        let workbook = new ExcelJS.Workbook()
        await workbook.xlsx.load(reader.result)
        const worksheet = workbook.getWorksheet('ImportMark')
        worksheet.eachRow((row, index) => {
          if (index > 2) {
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
              excellentDepartmentMonthID: 9,
              excellentDepartmentMonth: row.getCell(11).value,
              excellentDepartmentYearID: 10,
              excellentDepartmentYear: row.getCell(12).value,
              bcsDepartmentID: 3,
              bcsDepartment: row.getCell(13).value,
              jointActivitiesID: 4,
              jointActivities: row.getCell(14).value,
              trainStaffID: 11,
              trainStaff: row.getCell(15).value,
              trainID: 5,
              train: row.getCell(16).value,
              improveID: 6,
              improve: row.getCell(17).value,
              trainVmgID: 12,
              trainVmg: row.getCell(21).value,
              loveVmgID: 7,
              loveVmg: row.getCell(18).value,
              disciplineBonusID: 13,
              disciplineBonus: row.getCell(19).value,
              disciplineViolateID: 8,
              disciplineViolate: row.getCell(20).value,
            }
            this.items.push(mark)
          }
        })
        console.log(this.items.length)
        if (this.items.length > 0) {
          this.addStatus = true
        }
      }
      reader.readAsArrayBuffer(event.target.files[0])
    },
    exportFile(){
      const mark = [{
        staff_id: 'VMG01',
        department: 'PTPM',
        fullName: 'NTV',
        year: 2022,
        month: 10,
        kpi: 6,
        nvxs: 6,
        bsc: 8,
        hdc: 9,
        dt: 1,
        stt: 3,
        love: 8,
        kyluat: 0,
        tong: 222
      },
        {
          staff_id: 'VMG02',
          department: 'PTPM',
          fullName: 'NTT',
          year: 2022,
          month: 10,
          kpi: 6,
          nvxs: 6,
          bsc: 8,
          hdc: 9,
          dt: 5,
          stt: 3,
          love: 8,
          kyluat: 0,
          tong: 222
        }]
      const title = 'He Thong Quan Ly Diem VPoint';
      const header = ['STT', 'Mã NHÂN SỰ', 'BỘ PHẬN', 'HỌ VÀ TÊN', 'NĂM', 'THÁNG', 'KPI',
              'NVXS, BPXS', 'BSC BỘ PHẬN', 'HOẠT ĐỘNG CHUNG', 'ĐÀO TẠO', 'SÁNG TẠO THÁNG',	'I LOVE VMG', 'KỶ LUẬT', 'TỔNG']
      let workbook = new ExcelJS.Workbook()
      let worksheet = workbook.addWorksheet('VPOINT')
      let titleRow = worksheet.addRow([title])
      titleRow.font = { name: 'Time New Roman', family: 4, size: 16, bold: true }
      worksheet.addRow([])
      const subTitle = ['', '', '', '', '', '','HIỆU SUẤT CÔNG VIỆC', '', 'LÀM VIỆC NHÓM','', 'ĐÀO TẠO',
                                'SÁNG TẠO', 'TUÂN THỦ']
      let subtitle = worksheet.addRow(subTitle)
      subtitle.font = {
        name: 'Time New Roman'
      }
      subtitle.eachCell((cell) => {
        cell.fill = {
          type: 'pattern',
          pattern: 'solid',
          fgColor: { argb: 'FFFFFF00' },
          bgColor: { argb: 'FF0000FF' }
        }
        cell.border = { top: { style: 'thin' }, left: { style: 'thin' }, bottom: { style: 'thin' }, right: { style: 'thin' } }
      });
      let headerRow = worksheet.addRow(header)
      headerRow.font = {
        name: 'Time New Roman'
      }
      worksheet.mergeCells('G3:H3')
      worksheet.mergeCells('I3:J3')
      worksheet.mergeCells('M3:N3')
      headerRow.eachCell((cell) => {
        cell.fill = {
          type: 'pattern',
          pattern: 'solid',
          fgColor: { argb: 'FFFFFF00' },
          bgColor: { argb: 'FF0000FF' }
        }
        cell.border = { top: { style: 'thin' }, left: { style: 'thin' }, bottom: { style: 'thin' }, right: { style: 'thin' } }
      });
      let count = 1
      mark.forEach(d => {
        let row = worksheet.addRow([count++,...Object.values(d)]);
        row.fill = {
          type: 'pattern',
          pattern: 'solid',
          fgColor: {
            argb: 'FFFFFFFF'
          }
        };
        row.font = {
          color: {
            argb: '00000000',
          },
          bold: false
        }
        row.eachCell((cell) => {
          cell.border = {
            top: {
              style: 'thin'
            },
            left: {
              style: 'thin'
            },
            bottom: {
              style: 'thin'
            },
            right: {
              style: 'thin'
            }
          };
        });
      });
      worksheet.getColumn(1).width = 5;
      worksheet.getColumn(2).width = 10;
      worksheet.getColumn(3).width = 15;
      worksheet.getColumn(4).width = 20;
      worksheet.getColumn(5).width = 20;
      worksheet.getColumn(6).width = 20;
      worksheet.getColumn(7).width = 20;
      worksheet.getColumn(8).width = 20;
      worksheet.getColumn(9).width = 20;
      worksheet.getColumn(10).width = 20;
      worksheet.getColumn(11).width = 20;
      worksheet.getColumn(12).width = 20;
      worksheet.getColumn(13).width = 20;
      worksheet.getColumn(14).width = 20;
      worksheet.getColumn(15).width = 20;
      workbook.xlsx.writeBuffer().then((mark) => {
        let blob = new Blob([mark], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
        fs.saveAs(blob, 'CarData.xlsx');
      });
    },
    addMark() {
      const markList = this.items
      this.items = []
      markList.forEach(mark => {
        ExcelService.addMark(mark).then(response => {
          console.log(response)
          let newMark = response.data
          newMark.fullName = mark.fullName
          newMark.department = mark.department
          newMark.staff_id = mark.staff_id
          newMark.year = mark.year
          newMark.month = mark.month
          this.listMark.push(newMark)
          if (this.listMark.length > 0) {
            this.addStatus = false
            this.visibleTable = true
          }
        }).catch(error => {
          console.log(error)
        })
      })

    },
    editCellHandler(data, name) {
      this.items = this.items.map(item => ({...item, isEdit: false}));
      this.items[data.index].isEdit = true;
      this.selectedCell = name
    }
  },
  mounted() {
    this.items = this.items.map(item => ({...item, isEdit: false}));
  },
}
</script>

<style scoped>

</style>
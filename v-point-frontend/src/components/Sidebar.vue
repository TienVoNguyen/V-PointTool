<template>
  <el-row class="tac">
      <el-menu
          default-active="2"
          class="el-menu-vertical-demo "
          @open="handleOpen"
          @close="handleClose"
          background-color="#dc3545"
          text-color="#fff"
          active-text-color="#ffd04b">
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-plus"></i>
            <span>Thêm điểm V-Point</span>
          </template>

            <el-menu-item index="1-1"><i class="el-icon-user"></i>Thêm theo tên</el-menu-item>
            <el-menu-item index="1-2"><i class="el-icon-folder-opened"></i>Import file excel</el-menu-item>


        </el-submenu>
        <el-menu-item index="2">
          <i class="el-icon-user-solid"></i>
          <span>Thêm nhân viên mới</span>
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-document"></i>
          <span>Navigator Three</span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-setting"></i>
          <span>Navigator Four</span>
        </el-menu-item>
      </el-menu>
  </el-row>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Sidebar",
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped>

</style>

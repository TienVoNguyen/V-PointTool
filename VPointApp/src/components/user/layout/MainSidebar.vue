<template>
  <!-- Main Sidebar -->
  <aside class="main-sidebar col-12 col-md-3 col-lg-2 px-0">
    <div class=" main-navbar ">
      <nav class="navbar align-items-stretch navbar-light bg-white flex-md-nowrap border-bottom p-0">
        <a class="navbar-brand w-100 mr-0" href="#" style="line-height: 25px;">
          <div class="d-table m-auto">
            <img id="main-logo" class="d-inline-block align-top mr-1" style="max-width: 20%;"
                 src="https://vmgmedia.vn/Content/images/logo/01.png" alt="Shards Dashboard">
          </div>
        </a>
        <a class="toggle-sidebar d-sm-inline d-md-none d-lg-none">
          <i class="material-icons">&#xE5C4;</i>
        </a>
      </nav>
    </div>
    <form action="#" class="main-sidebar__search w-100 border-right d-sm-flex d-md-none d-lg-none">
      <div class="input-group input-group-seamless ml-3">
        <div class="input-group-prepend">
          <div class="input-group-text">
            <i class="fas fa-search"></i>
          </div>
        </div>
        <input class="navbar-search form-control" type="text" placeholder="Search for something..."
               aria-label="Search"></div>
    </form>
    <div class="nav-wrapper text-left">
      <el-menu
          background-color="#DC143C"
          text-color="#FFFFFF"
          active-text-color="#FFFF00"
          default-active="1"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose">
        <router-link to="/user/home" style="text-decoration: none;">
        <el-menu-item index="1">
          <i class="el-icon-s-home"></i>
          <span>Trang chủ</span>
        </el-menu-item>
        </router-link>
        <router-link to="importexcel" style="text-decoration: none;">
          <el-menu-item index="3">
            <i class="el-icon-folder-add"></i>
            <span>Chi tiết điểm</span>
          </el-menu-item>
        </router-link>

      </el-menu>
    </div>
  </aside>
</template>

<script>
export default {
  name: "MainSidebar"
}
</script>

<style scoped>
.nav-wrapper {
  background-color: #DC143C;
  color: #FFFFFF;
}
</style>
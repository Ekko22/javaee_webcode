<template>
  <el-menu style="height: 100vh;
  overflow: hidden;" default-active="/index" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
           :collapse="$store.state.isCollapse" background-color="#2F3138" text-color="#fff"
           active-text-color="#F2E473" router>
    <el-menu-item style="height: 65px">
      <h2 slot="default">图书管理系统</h2>
    </el-menu-item>
    <el-menu-item index="/index">
      <i class="el-icon-menu"></i>
      <span slot="title">首页</span>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-location"></i>
        <span slot="title">用户服务</span>
      </template>
      <el-menu-item-group>
        <span slot="title">图书服务</span>
        <el-menu-item index="SearchBook">借阅归还</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="管理员服务">
        <el-menu-item index="/BookServe">图书管理</el-menu-item>
        <el-menu-item index="/UserServe">用户管理</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-menu-item index="/about">
      <i class="el-icon-setting"></i>
      <span slot="title">关于本站</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "AsideNav",
  data() {
    return {};
  },
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

<style lang="less" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
  height: 100vh;
  overflow: hidden;
  border: none;
}

.el-menu--collapse {
  border: none;
  h2 {
    display: none;
  }
}
a {
  color: #fff;
//  取消文字下划线
  text-decoration: none;
}
a.router-link-exact-active {
  color: #F2E473;
}
</style>
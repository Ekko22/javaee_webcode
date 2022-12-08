<template>
  <el-container>
    <el-aside width="auto">
      <AsideNav/>
    </el-aside>
    <el-container style="padding: 0">
      <el-header>
        <TopNav/>
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import AsideNav from "@/components/AsideNav";
import TopNav from "@/components/TopNav";

export default {
  name: "HomePage",
  components: {TopNav, AsideNav},
  comments: {
    AsideNav,
    TopNav
  },
  data() {
    return {
      isCollapse: false
    };
  },
  created() {
    if (this.$store.state.user.name === ""){
      this.$message({
        message: "还未登录，请重新登录",
        type: "info"
      });
      this.$router.push("/login")
    }
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
.el-header{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
  padding: 0;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
  border: none;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
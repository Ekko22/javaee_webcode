<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
           background-color="#3B3D44" text-color="#fff"
           active-text-color="#F2E473">
    <el-button @click.prevent="changeAsideState" type="info" icon="el-icon-s-fold" style="float: left;"></el-button>
    <el-submenu index="1" style="float: right">
      <template slot="title">我的账号</template>
      <el-menu-item index="2-1" @click="changePassword" >修改密码</el-menu-item>
      <el-menu-item index="2-2" @click="exit">退出登录</el-menu-item>
    </el-submenu>
    <el-dialog title="修改用户信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" :label-width="formLabelWidth">
          <el-input v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="ensurePassword">确 定</el-button>
      </div>
    </el-dialog>
  </el-menu>
</template>

<script>
import axios from "axios";

export default {
  name: "TopNav",
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1',
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        name: '',
        password: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      formLabelWidth: '120px'
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    changeAsideState(){
      this.$store.state.isCollapse=!this.$store.state.isCollapse
    },
    changePassword(){
      this.form.name=this.$store.state.user.name
      this.form.password=this.$store.state.user.password
      this.dialogFormVisible = true
    },
    ensurePassword(){
      if(this.form.password==this.$store.state.user.password && this.form.name==this.$store.state.user.name){
        this.$message({
          message: '未修改任何信息',
          type: 'info'
        });
        this.dialogFormVisible = false
      }else if(this.form.password=='' || this.form.name==''){
        this.$message({
          message: '用户名或密码不能为空',
          type: 'error'
        });
      }else{
        axios.put("/users?id="+this.$store.state.user.id,{
          name:this.form.name,
          password:this.form.password
        }).then(res=>{
          if(res.data){
            this.$message({
              message: '修改成功',
              type: 'success'
            });
            this.dialogFormVisible = false
            this.$store.state.user.name=this.form.name
            this.$store.state.user.password=this.form.password
          }else{
            this.$message({
              message: '修改失败',
              type: 'error'
            });
          }
        })
      }

    },
    exit(){
    //  返回登录页面
      this.$router.push({
        path: '/login'
      })
    }
  }
}
</script>

<style lang="less" scoped>
.el-button{
  padding: 12px;
  margin: 12px;
}

</style>
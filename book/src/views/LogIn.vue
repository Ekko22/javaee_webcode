<template>
  <div class="body">
    <div class="head">
      <span>图书管理系统</span>

    </div>
    <div class="body" rout>
      <div class="container" id="num01">
        <!-- 设定登录的入口 -->
        <div class="tit">
          <p>登录</p>
        </div>
        <form class="form">
          <input type="text"  v-model="$store.state.user.name" placeholder="账号/用户名"
                 @blur="getName" ><br>
          <span >{{msg1}}</span>
          <input type="password" v-model="$store.state.user.password"
                 @blur="getPwd"  placeholder="密码"  @keydown.enter="login" ><br>
          <span >{{msg2}}</span>
        </form>
        <button @click="login" >登录</button>
        <span style="color: #333333">没有账号？<a @click="signUp">去注册</a></span>

      </div>
      <!-- 页面的动画效果 -->
      <div class="square">
        <ul>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
        </ul>
      </div>
      <div class="circle">
        <ul>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
        </ul>
      </div>
    </div>


  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "LogIn",
  data(){//函数式
    return{
      userName: '',
      userPassword: '',
      user:{
        name: '',
        password: ''
      },
      msg1: '',
      msg2:''
    }
  },
  methods: {

    signUp(){
      this.$router.push('/signUp')
    },
    getName() {
      var pat= /^[a-zA-Z0-9_]{3,10}$/;
      if (this.$store.state.user.name.length <= 0) {
        this.msg1 = '用户名不能为空'
      } else  if(!pat.test(this.$store.state.user.name)){
        this.msg1 = '用户名为3-10位(字母，数字，下划线)'
      }
      else{
        this.msg1=''
      }
    },
    getPwd() {
      var pat = /^(?=.*[a-z])(?=.*\d)[^]{3,14}$/;
      if (this.$store.state.user.password.length <= 0) {
        this.msg2 = '密码不能为空'
      } else if (!pat.test(this.$store.state.user.password)) {
        this.msg2 = '密码至少包含小写字母和数字且长度不能低于3位'
      } else {
        this.msg2 = ''
      }
    },
    login() {
      //用于区别实例对象

      let that = this;
      if (this.$store.state.user.name.length <= 0) {
        alert("用户名不能为空")
      } else if (this.$store.state.user.password.length <= 0) {
        alert("密码不能为空")
      } else if(this.msg1 !=''||this.msg2!='') {
        alert("请修改账户和密码")
      }else
      {
        axios.post('/users', this.$store.state.user).then(function (dat) {
          if (dat.data) {
            //存储用户信息
            axios.get("users/name?name=" + that.$store.state.user.name).then(dat=> {
              that.$store.state.user = dat.data[0];
              console.log(that.$store.state.user.id);
            })
            that.$message({
              message: '登录成功',
              type: 'success'
            });
            that.$router.push({
              path: '/index'
            })
          } else {
            that.$message({
              message: '登录失败',
              type: 'error'
            });
          }

        })
      }

    }
  },

}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

.body {
  width: 100%;
  height: 100%;
  /* 渐变背景 */
  background: linear-gradient(200deg, #24abf3, #1de9f8);
  /* 溢出隐藏 */
  overflow: hidden;
}

.head {
  display: flex;
  align-items: center;
}

.head span {
  margin: 40px auto;
  font-size: 50px;
  font-family: "华文仿宋";
  font-weight: bolder;
  color: rgb(59, 60, 61);
  text-shadow: 5px 5px 5px cornflowerblue;
}

.container {
  /* 弹性布局 居中 */
  /* display: flex; */
  /* justify-content: center;*/
  /* align-items: center; */
  float: right;
  /* 相对定位 */
  position: relative;
  z-index: 1;
  background-color: #fff;
  border-radius: 15px;
  /* 弹性布局 垂直排列 */
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 350px;
  height: 500px;
  /* 阴影 */
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.4);
  margin-right: 580px;

}

.container .tit {
  font-size: 40px;
  margin: 65px auto 70px auto;

}
 .form{
   width: 260px;
   height: 110px;
 }
.container form {
  margin-left: 30px;
}

.container input {
  width: 250px;
  height: 30px;
  text-indent: 8px;
  border: none;
  border-bottom: 1px solid #ddd;
  outline: none;
  margin: 12px auto;

}

.container button {
  width: 280px;
  height: 40px;
  margin: 50px auto 40px auto;
  border: none;
  background: linear-gradient(-200deg, #fac0e7, #aac2ee);
  color: #fff;
  font-weight: bold;
  letter-spacing: 8px;
  border-radius: 10px;
  cursor: pointer;
  /* 动画过渡 */
  transition: 0.5s;

}

.container button:hover {
  background: linear-gradient(-200deg, #aac2ee, #fac0e7);
  background-position-x: -280px;
}

.container span {
  font-size: 10px;
  color: red;
}

.container a {
  color: plum;
  text-decoration: none;
  cursor: pointer;
}

ul li {
  position: absolute;
  border: 1px solid #fff;
  background-color: #fff;
  width: 30px;
  height: 30px;
  list-style: none;
  opacity: 0;
}

.square li {
  top: 40vh;
  left: 60vw;
  /* 执行动画：动画名 时长 线性的 无限次播放 */
  animation: square 10s linear infinite;
}

.square li:nth-child(2) {
  top: 80vh;
  left: 10vw;
  /* 设置动画延迟时间 */
  animation-delay: 4s;
}

.square li:nth-child(3) {
  top: 80vh;
  left: 85vw;
  animation-delay: 6s;
}

.square li:nth-child(4) {
  top: 10vh;
  left: 10vw;
  animation-delay: 8s;
}

.square li:nth-child(5) {
  top: 80vh;
  left: 10vw;
  animation-delay: 10s;
}

.circle li {
  bottom: 0;
  left: 15vw;
  animation: circle 10s linear infinite;
}

.circle li:nth-child(2) {
  left: 55vw;
  animation-delay: 2s;
}

.circle li:nth-child(3) {
  left: 70vw;
  animation-delay: 6s;
}

.circle li:nth-child(4) {
  left: 85vw;
  animation-delay: 4s;
}

.circle li:nth-child(5) {
  left: 75vw;
  animation-delay: 8s;
}

/* 定义动画 */
@keyframes square {
  0% {
    transform: scale(0) rotateY(0deg);
    opacity: 1;
  }

  100% {
    transform: scale(5) rotateY(1000deg);
    opacity: 0;
  }
}

@keyframes circle {
  0% {
    transform: scale(0) rotateY(0deg);
    opacity: 1;
    bottom: 0;
    border-radius: 0;
  }

  100% {
    transform: scale(5) rotateY(10deg);
    opacity: 0;
    bottom: 90vh;
    border-radius: 50%;
  }
}
</style>
<template>
  <div class="body">
    <div class="head">
      <span>图书管理系统</span>

    </div>
    <div class="bodyy">
      <div class="container" id="root">
        <!-- 设定登录的入口 -->
        <div class="tit">注册</div>
        <form>
          <div class="lable">用户名：
            <input type="text" placeholder="请输入你的用户名" v-model="user.name" @blur="getName"><br>
            <span>{{ mesName }}</span>
          </div>
          <div class="lable2">
            密&nbsp;&nbsp;&nbsp;码：
            <input type="password" placeholder="密码不能少于六位" v-model="user.password" @blur="getPwd"><br>
            <span>{{ mesPwd }}</span>
          </div>
          <button @click.prevent="register">注册</button>
        </form>

        <span><a @click="login">返回登录</a></span>

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
    <div class="bottom"></div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "SignUp",
  data() {
    return {
      user: {
            name: '',
            password: ''
          }
      ,
      mesName: '', //存储用户名校验提示内容
      mesPwd: '',  //存储密码校验提示内容

    }
  },
  methods: {
    login() {
      this.$router.push("/login");
    },
    // 校验用户名输入是否为空
    getName() {
      var pat = /^[a-zA-Z0-9_]{3,10}$/;
      if (!this.user.name) {
        this.mesName = '用户名不能为空'
      } else if (!pat.test(this.user.name)) {
        this.mesName = '用户名为3-10位(字母，数字，下划线)'
      } else {
        this.mesName = ''
      }
    },
    // 校验密码输入是否符合要求
    getPwd() {
      var pat = /^(?=.*[a-z])(?=.*\d)[^]{3,14}$/;
      if (!this.user.password) {
        this.mesPwd = '密码不能为空'
      } else if (!pat.test(this.user.password)) {
        this.mesPwd = '密码至少包含小写字母和数字且长度不能低于三位'
      } else {
        this.mesPwd = ''
      }
    },
    register() {
      const that = this;
      if(this.mesName !=''||this.mesPwd!='') {
        alert("请填写符合要求的账户和密码")
      } else {
        axios.post('/users/add', this.user).then(res => {
          if (res) {
            this.$router.push("/login");
            that.$message({
              message: '注册成功',
              type: 'success'
            });
          } else {
            that.$message({
              message: '注册失败',
              type: 'error'
            });
          }
        }).catch(err => {
          console.log(err);
          that.$message({
            message: '注册失败,已经存在该用户',
            type: 'error'
          });
        });
      }
    }
  }
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
  /*font-style: oblique;*/
  font-weight: bolder;
  color: rgb(59, 60, 61);
  text-shadow: 5px 5px 5px burlywood;
}

.container {
  /* 弹性布局 居中 */
  /* display: flex; */
  /* justify-content: center; */
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
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
  margin-right: 580px;
}

.container .tit {
  font-size: 40px;
  margin: 65px auto 60px auto;

}

.container form {
  margin-left: 30px;
}

.lable {
  width: 250px;
  height: 60px;
  margin:10px auto;

}



.lable2 {
  width: 250px;
  height: 60px;
  margin:10px auto;
}

.container span {
  color: red;
}

.container input {
  /* width: 220px;
    height: 30px; */
  text-indent: 8px;
  border: none;
  border-bottom: 1px solid #ddd;
  outline: none;
  margin: 15px auto;

}

.container button {
  width: 280px;
  height: 40px;
  margin: 35px auto 40px auto;
  border: none;
  background: linear-gradient(-200deg, #f37f0b, #155ee6);
  color: #fff;
  font-weight: bold;
  letter-spacing: 8px;
  border-radius: 10px;
  cursor: pointer;
  /* 动画过渡 */
  transition: 0.5s;

}

.container button:hover {
  background: linear-gradient(-200deg, #ec9d0b, #2ea7e4);
  background-position-x: -280px;
}

.container span {
  font-size: 14px;

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
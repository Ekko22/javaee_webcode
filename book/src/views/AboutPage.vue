<template>
<div>
    <h1>
        项目说明:图书管理系统
    </h1>
    <h2>实现功能</h2>
  <ul>
    <li>1.用户模块增删改查 :实现用户登录，注册，用户信息的校验;</li>
    <li>2.图书模块增删改查 :对图书的增删改查，分页查询，模糊查询;</li>
    <li>3.图书借阅的完整业务逻辑 :图书借阅状态的判断，轮播图显示;</li>
  </ul>
    <h2>技术选型</h2>
    <p>  前端:<br>
        三件套:html+css+Vue.js<br>
        框架:VueCli,axios<br>
        技术: Vue全家桶(Vuex+VueRouter)+ElementUI组件库<br>
    </p>
    <p>
        后端: <br>
        java:后端代码的使用语言+<br>
        SpringBoot+java+Spring+Mybatis <br>
    </p>
    <h2>小组分工:</h2>

    <p>
        陈申楠:实现前端登录和注册页面，利用正则在前端进行用户数据校验，及前端页面美化<br>
        彭&nbsp;&nbsp;&nbsp;芋:实现框架的构建，界面布局设计，前端交互逻辑，及前后端数据交互和测试<br>
        丁&nbsp;&nbsp;&nbsp;天:实现后端增删改查接口，数据库的设计和建立，及其他模块功能后端代码<br>
    </p>


</div>
</template>

<script>
export default {
  name: "AboutPage"
}
</script>

<style scoped>

</style>
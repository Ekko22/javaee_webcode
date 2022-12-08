<template>
  <el-carousel :interval="4000" type="card" height="60vh" width="90vh" >
    <el-carousel-item v-for="item in items" :key="item.id">

      <img :src="item.img"/>
     <p class="swipe_title">{{item.title}}</p>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  name: "IndexBox",
  data() {
    return {
      items: [
        {
          id: '1',
          title: "登录校验",
          img: require("../assets/登录校验.jpg"),
        },
        {
          id: '2',
          title: "数据库表",
          img: require("../assets/数据库表.jpg"),
        },
        {
          id: '3',
          title: "借阅图书",
          img: require("../assets/借阅图书.jpg"),
        },
        {
          id: '4',
          title: "图书查找",
          img: require("../assets/图书查找.jpg"),
        },
        {
          id: '5',
          title: "图书管理",
          img: require("../assets/图书管理.jpg"),
        },
        {
          id: '6',
          title: "图书删除",
          img: require("../assets/图书删除.jpg"),
        }
      ],
    }
  }
}
</script>

<style lang="less" scoped>

.el-carousel__item img {
  width: 100%;
  height: 100%;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.swipe_title {
  position: absolute;
  line-height: 60px;
  height: 60px;
  width: 76%;
  z-index: 20;
  text-align: left;
  /* background-color: #da4f49; */
  color: #FF507BEA;
  font-size: 50px;
  margin-left: 100px;
  bottom: 0;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap
}
</style>
<template>
  <el-container style="height: 100%">
    <el-main>
      <el-table
          :data="tableData"
          style="width: 100%;">
        <el-table-column
            label="ISBN"
            prop="ISBN">
        </el-table-column>
        <el-table-column
            label="书名"
            prop="name"
            align="center">
        </el-table-column>
        <el-table-column
            label="详细"
            align="center">
          <template slot-scope="scope">
            <el-popover trigger="hover" placement="top">
              <p>出版社: {{ scope.row.publisher }}</p>
              <p>作者: {{ scope.row.author }}</p>
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.states }}</el-tag>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
            align="center" width="320px">
          <template slot="header">
            <input type="text" v-model="searchContent" placeholder="输入要查询的书名" style="width: 100%;" @keydown.enter="searchBook">
          </template>
          <template slot-scope="scope">
            <el-button
                size="middle"
                type="primary"
                @click="borrowBook(scope.$index)">借阅
            </el-button>
            <el-button
                size="middle"
                type="success"
                @click="returnBook(scope.$index)">归还
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <el-footer>
      <div class="block">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[2,5,10]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            style="text-align: center; padding: 14px">
          >
        </el-pagination>
      </div>
    </el-footer>
  </el-container>
</template>

<script>
import axios from "axios";

export default {
  name: "SearchBook",
  data() {
    return {
      searchContent: "",
      tableData: [],
      total: 0,
      currentPage: 1,
      pageSize: 5,
      books: {
        book_isbn: "",
        book_name: "",
        book_publisher: "",
        book_author: "",
      }
    }
  },
  created() {
    this.loadData();
  },
  watch:{
  //  当输入框有内容变化时，触发该函数
    searchContent(){
      if (this.searchContent.length > 0){
        this.searchBook();
        return;
      }else {
        this.loadData();
      }
    }
  },
  methods: {
    searchBook() {
      this.currentPage = 1;
      this.loadData()
    },
    returnBook(index) {
      this.id = this.tableData[index].id;
      if (this.tableData[index].states == "不可借阅") {
        axios.delete("/borrows", {
          params: {
            bookId: this.id,
            userId: this.$store.state.user.id
          }
        }).then(res => {
          if (res.data) {
            axios.put("/books/" + this.id, {
              states: "可借阅"
            }).then(res => {
              console.log(res.data)
              this.$message({
                message: "归还成功",
                type: "success"
              });
              this.loadData();
            }).catch(error => {
              console.log(error)
              this.$message({
                message: "归还失败",
                type: "error"
              });
            })
          }
        })
      }
    },
    borrowBook(index) {
      this.id = this.tableData[index].id;
      console.log(this.id);
      if (this.tableData[index].states == "可借阅") {
        axios.put("/books/" + this.id, {
          states: "不可借阅"
        }).then(response => {
          console.log(response);
          axios.post("/borrows", {
            bookId: this.id,
            userId: this.$store.state.user.id
          }).then(response => {
            console.log(response);
            this.$message({
              message: "借阅成功",
              type: "success"
            });
            this.loadData();
          }).catch(error => {
            console.log(error);
            this.$message({
              message: "借阅失败",
              type: "error"
            });
          });
        });
      } else {
        this.$message({
          message: "该书已被借阅",
          type: "error"
        });
      }
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.loadData();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.loadData();
    },
    loadData() {
      //  传递空对象，不传递参数，则查询所有
      console.log("调用了");
      axios.post("/books/page?" + "currentPage=" + this.currentPage + "&pageSize=" + this.pageSize, {
        name:this.searchContent
      }).then(response => {
        this.tableData = response.data.list;
        console.log(this.tableData)
        this.total = response.data.totalCount;
      });
    },
  }
}
</script>

<style lang="less" scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
}

.el-main {
  color: #333;
  text-align: center;
  height: 80vh;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-pagination {
  margin: 0 auto;
  text-align: center;
  color: #333;
  font: bold 24px/1.5 "Microsoft YaHei";
}

.header-row {
  background-color: #656870;
}
</style>
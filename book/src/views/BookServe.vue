<template>
  <el-container style="height: 100%">
    <el-main>
      <h1>图书管理</h1>
      <!--搜索表单-->
      <el-form :inline="true" :model="book" class="demo-form-inline">

        <el-form-item label="当前状态">
          <el-select v-model="book.states" placeholder="状态" style="width: 110px">
            <el-option label="可借阅" value="可借阅"></el-option>
            <el-option label="不可借阅" value="不可借阅"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="ISBN">
          <el-input v-model="book.ISBN" placeholder="ISBN" style="width: 110px"></el-input>
        </el-form-item>

        <el-form-item label="图书名称">
          <el-input v-model="book.name" placeholder="图书名称" style="width: 130px"></el-input>
        </el-form-item>

        <el-form-item label="出版商">
          <el-input v-model="book.publisher" placeholder="出版商" style="width: 150px"></el-input>
        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="book.author" placeholder="作者" style="width: 110px"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>

      <!--按钮-->
      <el-row>

        <el-button type="danger" plain @click="deleteByIds">批量删除</el-button>
        <el-button type="primary" plain @click="dialogVisible = true">新增</el-button>

      </el-row>

      <!--添加数据对话框表单-->
      <el-dialog
          title="编辑图书"
          :visible.sync="dialogVisible"
          width="30%"
      >

        <el-form ref="form" :model="book" label-width="80px">
          <el-form-item label="ISBN">
            <el-input v-model="book.ISBN"></el-input>
          </el-form-item>

          <el-form-item label="图书名称">
            <el-input v-model="book.name"></el-input>
          </el-form-item>

          <el-form-item label="出版商">
            <el-input v-model="book.publisher"></el-input>
          </el-form-item>

          <el-form-item label="作者">
            <el-input v-model="book.author"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="addBook">提交</el-button>
            <el-button @click="dialogVisible = false">取消</el-button>
          </el-form-item>
        </el-form>

      </el-dialog>

<!--      修改数据对话框-->
      <el-dialog
          title="编辑图书"
          :visible.sync="dialogVisibleUP"
          width="30%"
      >

        <el-form ref="form" :model="book" label-width="80px">
          <el-form-item label="ISBN">
            <el-input v-model="book.ISBN"></el-input>
          </el-form-item>

          <el-form-item label="图书名称">
            <el-input v-model="book.name"></el-input>
          </el-form-item>

          <el-form-item label="出版商">
            <el-input v-model="book.publisher"></el-input>
          </el-form-item>

          <el-form-item label="作者">
            <el-input v-model="book.author"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="updateBook">提交</el-button>
            <el-button @click="dialogVisible = false">取消</el-button>
          </el-form-item>
        </el-form>

      </el-dialog>

      <!--表格-->
      <template>
        <el-table
            :data="tableData"
            style="width: 100%"
            :row-class-name="tableRowClassName"
            @selection-change="handleSelectionChange"
        >
          <el-table-column
              type="selection"
              width="55">
          </el-table-column>
          <el-table-column
              type="index"
              width="50">
          </el-table-column>

          <el-table-column
              prop="ISBN"
              label="ISBN"
              align="center"
          >
          </el-table-column>
          <el-table-column
              prop="name"
              label="图书名称"
              align="center"
          >
          </el-table-column>
          <el-table-column
              prop="publisher"
              align="center"
              label="出版商">
          </el-table-column>
          <el-table-column
              prop="author"
              align="center"
              label="作者">
          </el-table-column>
          <el-table-column
              prop="states"
              align="center"
              label="当前状态">
          </el-table-column>

          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-button type="primary" @click="updateById(scope.row)">修改</el-button>
              <el-button type="danger" @click="deleteById(scope.row)">删除</el-button>
            </template>
          </el-table-column>

        </el-table>
      </template>

      <!--分页工具条-->
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="5"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalCount ">
      </el-pagination>
      </el-main>
  </el-container>


</template>

<script>
import axios from "axios";

export default {
  name: "BookServe",
  mounted() {
    //当页面加载完之后，发送异步信息，请求数据
    this.selectAll();
    console.log(this.book);
  },
  methods: {
    updateBook(){
      let _this = this;
      axios({
        method: "put",
        url: "http://localhost:8081/books/" + this.updateId,
        data: this.book
      }).then(function (resp) {
        _this.updateId = -1;
        if (resp.data === true) {
          _this.dialogVisibleUP = false;
          _this.$message({
            message: '恭喜你，修改成功',
            type: 'success'
          });
          _this.selectAll();
          _this.book = {
            id: '',
            ISBN: '',
            name: '',
            publisher: "",
            author: "",
            states:""
          }
        }
      })
    },
    deleteById(row){
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let _this = this;
        axios({
          method: "delete",
          url: "http://localhost:8081/books/" + row.id,
        }).then(function (resp) {
          if (resp.data === "success") {
            _this.$message({
              message: '恭喜你，删除成功',
              type: 'success'
            });
          }
          _this.selectAll();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    updateById(row){
      this.dialogVisibleUP = true;
      this.book.publisher = row.publisher;
      this.book.author = row.author;
      this.book.ISBN = row.ISBN;
      this.book.name = row.name;
      this.book.states = row.states;
      this.updateId = row.id;
    },
    deleteByIds() {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let _this = this;
        axios({
          method: "delete",
          url: "http://localhost:8081/books",
          data: _this.selectedIds
        }).then(function (resp) {
          if (resp.data === "success") {
            _this.$message({
              message: '恭喜你，删除成功',
              type: 'success'
            });
          }
          _this.selectAll();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    // 复选框选中后执行的方法
    handleSelectionChange(val) {
      this.multipleSelection = val;
      // console.log(this.multipleSelection)
      for (let i = 0; i < this.multipleSelection.length; i++) {
        this.selectedIds[i] = this.multipleSelection[i].id;
      }
    },
    // 查询方法
    onSubmit() {
      // console.log(this.brand);
      this.currentPage = 1;
      // console.log(this.book);
      this.selectAll();
    },
    // 添加数据
    addBook() {
      let _this = this;
      axios({
        method: "post",
        url: "http://localhost:8081/books",
        data: _this.book
      }).then(function (resp) {
        if (resp.data === true) {
          _this.dialogVisible = false;
          _this.$message({
            message: '恭喜你，添加成功',
            type: 'success'
          });
          _this.book = {
            id: '',
            ISBN: '',
            name: '',
            publisher: "",
            author: "",
            states:""
          };
          _this.selectAll();
        }
      })

    },
    selectAll() {
      let _this = this;
      axios({
        method: "post",
        url: "http://localhost:8081/books/page/?currentPage="
            + this.currentPage + "&pageSize=" + this.pageSize,
        data: this.book
      }).then(function (resp) {
        //resp.data 这样获得异步请求的数据
        _this.tableData = resp.data.list;
        // console.log(_this.tableData);
        _this.totalCount = resp.data.totalCount;
      })
    },

    //分页
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.selectAll();
    },
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.selectAll();
    }

  },
  data() {
    return {
      updateId:"",
      dialogVisibleUP:false,
      pageSize: 5,
      //总记录数
      totalCount: 0,
      //被选中的id
      selectedIds: [],
      // 当前页码
      currentPage: 1,
      // 添加数据对话框是否展示的标记
      dialogVisible: false,
      tableRowClassName:"",

      // 品牌模型数据
      book: {
        id: '',
        ISBN: '',
        name: '',
        publisher: "",
        author: "",
        states:""
      },
      // 复选框选中数据集合
      multipleSelection: [],
      // 表格数据
      tableData: [],

    }
  }
}
</script>

<style scoped>

</style>
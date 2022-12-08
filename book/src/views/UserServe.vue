<template>
  <el-container style="height: 100%">
    <el-main>
        <!-- 按钮 -->
        <el-button type="primary" plain @click="addUser" style="float: left;margin:12px 0">新增用户</el-button>
      <template>
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
            <el-button type="primary" @click="ensureUserMsg">确 定</el-button>
          </div>
        </el-dialog>
        <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName"
                  @selection-change="handleSelectionChange" @click="onRowClick">
          <el-table-column type="selection" width="55">
          </el-table-column>
          <el-table-column prop="id" width="50" label="用户ID">
          </el-table-column>
          <el-table-column prop="name" label="用户名称" align="center">
          </el-table-column>
          <el-table-column prop="password" label="用户密码" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="primary" @click="changeUserMsg(scope.$index)">修改</el-button>
              <el-button type="danger" @click="deleteUserMsg(scope.$index)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
    </el-main>
    <el-footer>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="currentPage" :page-sizes="[2,5]" :page-size="100"
                     layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
import axios from "axios";

export default {
  name: "UserServe",
  created() {
    this.dataLoad();
  },
  methods: {
    changeUserMsg(index) {
      this.id=this.tableData[index].id;
      axios.get("/users/"+this.id).then(res => {
        this.form.name = res.data[0].name;
        this.form.password = res.data[0].password;
      });
      this.dialogFormVisible = true;
    },
    addUser(){
      this.dialogFormVisible = true;
    },
    ensureUserMsg(){
      const msg = {
        name: this.form.name,
        password: this.form.password
      };
      if (this.id){
        axios.put("/users/?id="+this.id,msg).then(res => {
          if (res.data){
            this.dialogFormVisible = false;
            this.$message({
              message: "修改成功",
              type: "success"
            });
            this.dataLoad();
          }else {
            this.$message({
              message: "修改失败",
              type: "error"
            });
          }
        });
      }else {
        axios.post("/users/add",msg).then(res => {
          if (res.data){
            this.dialogFormVisible = false;
            this.form.name = "";
            this.form.password = "";
            this.$message({
              message: "添加成功",
              type: "success"
            });
            this.dataLoad();
          }else {
            this.$message({
              message: "添加失败",
              type: "error"
            });
          }
        });
      }
    },
    deleteUserMsg(index) {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.id=this.tableData[index].id;
        axios.delete("/users/"+this.id).then(res => {
          if (res.data){
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.dataLoad();
          }else {
            this.$message({
              message: "删除失败",
              type: "error"
            });
          }
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    dataLoad() {
      axios.get("/users/page?page=" + this.currentPage + "&size=" + this.pageSize).then(res => {
        this.tableData = res.data;
      });
      axios.get("/users/count").then(res => {
        this.total = res.data;
      });
    },
    //分页工具的方法
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.dataLoad();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.dataLoad();
    },
    tableRowClassName({row, rowIndex}) {
      //把每一行的索引放进row
      row.index = rowIndex;
    },
    onRowClick(row) {
      console.log(row)
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    onSubmit() {
      console.log('submit!');
    },
    addBrand() {
      console.log('submit!');
    }
  },
  data() {
    return {
      //当前页数
      currentPage: 1,
      //每页条数
      pageSize: 2,
      total: 0,
      //品牌数据模型
      brand: {
        status: '',
        companyName: '',
        brandName: '',
        description: '',
        id: '',
      },
      tableData: [],
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
      formLabelWidth: '120px',
      index: -1,
    }
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
<template>
    <div class="main" v-title data-title="编辑">
        <el-page-header @back="goBack" content=""></el-page-header>
        <el-row type="flex" justify="left" class="edit-row">
            <el-button type="primary" @click="dialogFormVisible = true">添加成员</el-button>
        </el-row>

        <el-row type="flex" justify="center">
            <div style="width: 60%">
                <el-table :data="studentList" stripe style="width: 100%">
                    <el-table-column prop="studentId" label="学号"></el-table-column>
                    <el-table-column prop="name" label="姓名"></el-table-column>
                    <el-table-column prop="sex" label="性别"></el-table-column>
                    <el-table-column label="操作">
                        <template slot-scope="scope">
                            <el-button @click="editStudent(scope.row)" type="text" size="small">编辑</el-button>
                            <el-button @click="deleteStudent(scope.row)" type="text" size="small">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </el-row>
        <!-- 添加学生对话框 -->
        <el-dialog title="添加学生" :visible.sync="dialogFormVisible">
            <el-form :model="student" :rules="rules" ref="studentForm">
                <el-form-item label="学号" prop="studentId" :label-width="formLabelWidth">
                    <el-input :rows="7" v-model="student.studentId"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name" :label-width="formLabelWidth">
                    <el-input v-model="student.name"></el-input>
                </el-form-item>
                <el-form-item label="性别" :label-width="formLabelWidth">
                    <el-select v-model="student.sex" placeholder="请选择">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addStudent">确 定</el-button>
            </div>
        </el-dialog>
        <!-- 编辑学生对话框 -->
        <el-dialog title="编辑学生" :visible.sync="editFormVisible">
            <el-form :model="editItem" :rules="rules" ref="editForm">
                <el-form-item label="学号" prop="studentId" :label-width="formLabelWidth">
                    <el-input :rows="7" v-model="editItem.studentId"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name" :label-width="formLabelWidth">
                    <el-input v-model="editItem.name"></el-input>
                </el-form-item>
                <el-form-item label="性别" :label-width="formLabelWidth">
                    <el-select v-model="editItem.sex" placeholder="请选择">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateEdit">确 定</el-button>
            </div>
        </el-dialog>
        <!-- 删除学生对话框 -->
        <el-dialog title="删除" :visible.sync="deleteDialogVisible" width="30%">
            <span>确定删除 {{ deleteItem.name }} ?</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="deleteDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirmDelete">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data() {
        return {
            gradeId: "",
            student: {
                studentId: "",
                name: "",
                sex: ""
            },
            editItem: Object,
            deleteItem: Object,
            studentList: [],
            dialogFormVisible: false,
            editFormVisible: false,
            deleteDialogVisible: false,
            formLabelWidth: '120px',
            rules: {
                studentId: [
                    {required: true, message: '请输入学号'},
                    { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
                ],
                name: [
                    {required: true, message: "请输入姓名"},
                    {min: 1, max: 15, messsage: '长度在 1 到 15 个字符', trigger: 'blur'}
                ]
            },
            
        };
    },
    methods: {
        goBack() {
            this.$router.push({
                name: "pick", 
                params: {
                    gradeId: this.gradeId
                }
            });
        },
        editStudent(row) {
            this.editFormVisible = true;
            this.editItem = JSON.parse(JSON.stringify(row));
        },
        updateEdit() {           
            this.$refs["editForm"].validate(valid => {
                if (valid) {
                    this.$axios.post("http://localhost:8080/updateStudent", {
                        id: this.editItem.id,
                        studentId: this.editItem.studentId,
                        name: this.editItem.name,
                        sex: this.editItem.sex
                    })
                    .then(response => {
                        if (response.data.code == 1) {
                            let retData = response.data.data;
                            for (let item of this.studentList) {
                                if (item.id == retData.id) {
                                    item.studentId = retData.studentId;
                                    item.name = retData.name;
                                    item.sex = retData.sex;
                                    break;
                                }
                            }
                            this.$message({
                                message: "修改成功",
                                type: "success"
                            })
                        } else {
                            this.$message.error("更新失败");
                        }
                    })
                    this.editFormVisible = false;
                } else {
                    this.$message.error("请检查条件是否符合要求");
                    return;
                }
            })
        },
        deleteStudent(row) {
            this.deleteDialogVisible = true;
            this.deleteItem = row;
        },
        confirmDelete() {
            this.$axios.post("http://localhost:8080/deleteStudent", {
                id: this.deleteItem.id,
                studentId: this.deleteItem.studentId,
                name: this.deleteItem.name,
                sex: this.deleteItem.sex
            })
            .then(response => {
                if (response.data.code == 1) {
                    this.getAllStudents(this.gradeId);
                    this.$message({
                        message: "添加成功",
                        type: "success"
                    })
                } else {
                    this.$message.error("删除失败");
                }
            })
            .catch(error => {
                this.$message.error("系统异常");
            })
            this.deleteDialogVisible = false;
        },
        addStudent() {
            this.$refs["studentForm"].validate(valid => {
                if (valid) {
                    this.$axios.post("http://localhost:8080/addStudent", {
                        gradeId: this.gradeId,
                        student: this.student
                    })
                    .then(response => {
                        if (response.data.code == 1) {
                            this.studentList.push(response.data.data);
                            this.$message({
                                message: "添加成功",
                                type: "success"
                            })
                        } else {
                            this.$message.error("系统异常");
                        }
                    })
                    .catch(error => {
                        this.$message.error("系统异常");
                    })
                    this.student.studentId = "";
                    this.student.name = "";
                    this.student.sex = "";
                    this.dialogFormVisible = false;
                } else {
                    this.$message.error("请检查条件是否符合要求");
                    return;
                }
            })
        },
        getAllStudents(gradeId) {
            this.$axios.get("http://localhost:8080/getStudents", {
                params: {
                    gradeId: gradeId
                }
            })
            .then(response => {
                this.studentList = response.data.data;
            })
            .catch(error => {
                this.$message.error("获取数据失败");
            })
        }
    },
    mounted: function() {
        this.gradeId = this.$route.params.gradeId;
        this.getAllStudents(this.gradeId);
    }

};
</script>

<style scoped>
.edit-row {
    margin-top: 32px;
    margin-left: 20%;
    margin-bottom: 12px;
}
</style>
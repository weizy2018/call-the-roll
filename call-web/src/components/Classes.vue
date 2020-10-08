<template>
    <div class="main" v-title data-title="班级">
        <el-row type="flex" justify="center">
            <el-row :gutter="20" class="row-style">
                <el-col :span="6" v-for="(item) in grades" :key="item.id" >
                    <el-card shadow="hover" @click.native="pickOne(item.id)">
                        <div class="content">
                            {{item.name}}
                        </div>
                    </el-card>
                </el-col>
                <el-col :span='6'>
                    <el-card shadow="hover" @click.native="dialogFormVisible = true">
                        <div class="content">
                            <i style="font-size: 40px" class="el-icon-circle-plus-outline"></i>
                        </div>
                    </el-card>
                </el-col>
                
            </el-row>
        </el-row>

        <el-dialog title="添加班级" :visible.sync="dialogFormVisible">
            <el-form :model="gradeInfo" :rules="rules" ref="gradeForm">
                <el-form-item label="班级名称" prop="gradeName" >
                    <el-input v-model="gradeInfo.gradeName"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addGrade">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data () {
        return {
            userId: "",
            grades: [],
            dialogFormVisible: false,
            gradeInfo: {
                gradeName: ""
            },
            rules: {
                gradeName: [
                    {required: true, message: '请输入班级名称'},
                    { min: 1, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
                ],
            },
        }
    },
    methods: {
        getGrade(userId) {
            this.$axios.get("http://localhost:8080/getGrades", {
                params: {
                    userId: this.userId
                }
            })
            .then((response) => {
                this.grades = response.data.data;
            })
            .catch(error => {
                this.$message.error("数据获取失败");
            })
        },
        pickOne(gradeId) {
            this.$router.push({
                name: "pick", 
                params: {
                    gradeId: gradeId
                }
            });
        },
        addGrade() {
            this.$refs["gradeForm"].validate(valid => {
                if (valid) {
                    this.$axios.get("http://localhost:8080/addGrade", {
                        params: {
                            userId: this.userId,
                            gradeName: this.gradeInfo.gradeName
                        }
                    })
                    .then((response) => {
                        if (response.data.code == 1) {
                            this.grades.push(response.data.data);
                            this.$message({
                                message: "添加成功",
                                type: "success"
                            })
                        } else {
                            this.$message.error("添加失败");
                        }
                        
                    })
                    .catch(error => {
                        console.log(error);
                    })
                    this.dialogFormVisible = false;
                    this.gradeInfo.gradeName = "";
                } else {
                    return;
                }
            })
        }
    },
    mounted: function() {
        this.userId = JSON.parse(window.sessionStorage.getItem("user")).id;
        this.getGrade(this.userId);
    }
};
</script>

<style scoped>
.row-style {
    width: 800px;
}
.content {
    width: 100%;
    height: 160px;
    line-height: 180px;
    margin: '8px';
    display: block;
}


</style>
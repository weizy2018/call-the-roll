<template>
    <div class="main" v-title data-title="点名">
        <el-page-header @back="goBack" content=""></el-page-header>
        <el-row type="flex" justify="start" class="edit-row">
            <el-link type="primary" style="font-size: 20px" @click="studentDetail">查看成员</el-link>
        </el-row>
        <el-row type="flex" justify="center" style="margin-top: 80px">
            <el-card shadow="never">
                <div class="content">
                    {{ luckyOne }}
                </div>
            </el-card>
        </el-row>
        <el-row type="flex" justify="center">
            <el-button type="primary" @click="catchOne">抓一个</el-button>
        </el-row>        
    </div>
</template>

<script>
export default {
    data () {
        return {
            gradeId: "",
            students: [],
            luckyOne: "幸运选手"
        }
    },
    methods: {
        goBack() {
            this.$router.push({
                name: "classes", 
            });
        },
        getAllStudents(gradeId) {
            this.$axios.get("http://localhost:8080/getStudents", {
                params: {
                    gradeId: gradeId
                }
            })
            .then(response => {
                this.students = response.data.data;
            })
            .catch(error => {
                this.$message.error("获取数据失败");
            })
        },
        studentDetail() {
            this.$router.push({
                name: "students", 
                params: {
                    gradeId: this.gradeId
                }
            });
        },
        sleep(time) {
            return new Promise((resolve) => setTimeout(resolve, time));
        },
        sleep2(time) {
            var start = new Date().getTime();
            while (true) {
                if (new Date().getTime() - start > time) {
                    break;
                }
            }
        },
        catchOne() {
            let totalStudent = this.students.length;
            if (totalStudent < 1) {
                this.$message({
                    message: '当前学生数量为0',
                    type: 'warning'
                });
                return;
            }
            let luckyNum = Math.floor(Math.random() * totalStudent);
            this.luckyOne = this.students[luckyNum].name;
        }
    },
    mounted: function() {
        this.gradeId = this.$route.params.gradeId;
        this.getAllStudents(this.gradeId);
    }
};
</script>

<style scoped>
.content {
    width: 320px;
    height: 54px;
    line-height: 54px;
    font-size: 42px;
    display: block;
}
.edit-row {
    margin-top: 32px;
    margin-left: 32px;
    margin-bottom: 0px;
}

.el-row {
    margin-bottom: 100px;
}
</style>
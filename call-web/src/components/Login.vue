<template>
    <div class="main" v-title data-title="登录">
        <el-row type="flex" justify="center">
            <el-col :span="20">
                <h1>六景民中点名系统</h1>
            </el-col>
        </el-row>
        <el-row type="flex" justify="center">
            <el-col :span="6">
                <el-form :model="user" :rules="rules" ref="userForm" class="demo-ruleForm">
                    <el-form-item label="账号" prop="name">
                        <el-input v-model="user.name"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="user.password" show-password>></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm('userForm')">登陆</el-button>
                        <el-button @click="resetForm('userForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
    name: 'HelloWorld',
    data () {
        return {
            user: {
                name: '',
                password: ''
            },
            rules: {
                name: [
                    {required: true, message: '请输入账号'}
                ],
                password: [
                    {required: true, message: '请输入密码'}
                ]
            }
        }
    },
    methods: {
        submitForm (formName) {
            this.$refs[formName].validate((valid) => {
                console.log("user: ", this.user);
                if (valid) {
                    this.$message({
                        message: '登陆成功',
                        type: 'success'
                    });
                    this.$router.push('/classes');
                } else {
                    this.$message.error('用户名或者密码错误');
                    this.password = '';
                    return;
                }
            })
        },
        resetForm (formName) {
            this.$refs[formName].resetFields();
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
    font-weight: normal;
}
.bg-purple {
    background: #d3dce6;
}
</style>

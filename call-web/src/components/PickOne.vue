<template>
    <div class="main" v-title data-title="点名">
        <el-page-header @back="goBack" content=""></el-page-header>
        <el-row type="flex" justify="start" class="edit-row">
            <el-link type="primary" style="font-size: 20px">编辑成员</el-link>
        </el-row>
        <el-row type="flex" justify="center" style="margin-top: 120px">
            <el-card shadow="never">
                <div class="content">
                    {{ name }}
                </div>
            </el-card>
        </el-row>
        <el-row type="flex" justify="center">
            <el-button type="primary">抓一个</el-button>
        </el-row>        
    </div>
</template>

<script>
export default {
    data () {
        return {
            name: "张三"
        }
    },
    methods: {
        goBack() {
            console.log("go back");
        }
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
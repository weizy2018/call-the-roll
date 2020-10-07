<template>
    <div class="main" v-title data-title="编辑">
        <el-page-header @back="goBack" content=""></el-page-header>
        <el-row type="flex" justify="left" class="edit-row">
            <el-button type="primary">添加成员</el-button>
        </el-row>

        <el-row type="flex" justify="center">
            <div style="width: 60%">
                <el-table :data="tableData" stripe style="width: 100%">
                    <el-table-column prop="date" label="日期"></el-table-column>
                    <el-table-column prop="name" label="姓名"></el-table-column>
                    <el-table-column prop="address" label="地址"></el-table-column>
                    <el-table-column label="操作">
                        <template slot-scope="scope">
                            <el-button @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
                            <el-button @click="handleClick(scope.row)" type="text" size="small">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </el-row>
    </div>
</template>

<script>
export default {
    data() {
        return {
            tableData: [
                {
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
                },
                {
                date: '2016-05-04',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1517 弄'
                },
                {
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1519 弄'
                },
                {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1516 弄'
            }]
        };
    },
    methods: {
        goBack() {
            console.log("go back");
        },
        handleClick(row) {
            console.log(row);
        }
    },
};
</script>

<style scoped>
.edit-row {
    margin-top: 32px;
    margin-left: 20%;
    margin-bottom: 12px;
}
</style>
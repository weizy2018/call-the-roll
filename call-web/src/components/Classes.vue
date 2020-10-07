<template>
    <div class="main" v-title data-title="班级">
        <el-row type="flex" justify="center">
            <el-row :gutter="20" class="row-style">
                <el-col :span="6" v-for="(index) in 10" :key="index" >
                    <el-card shadow="hover" @click.native="pickName(index)">
                        <div class="content">
                            1303
                        </div>
                    </el-card>
                </el-col>
                <el-col :span='6'>
                    <el-card shadow="hover" @click.native="pickName(1)">
                        <div class="content">
                            <i style="font-size: 40px" class="el-icon-circle-plus-outline"></i>
                        </div>
                    </el-card>
                </el-col>
                
            </el-row>
        </el-row>
    </div>
</template>

<script>
export default {
    data () {
        return {
            
        }
    },
    methods: {
        pickName (index) {
            console.log("click: ", index);
        }
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
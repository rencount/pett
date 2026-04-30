<script setup lang="ts">
    import { defineAsyncComponent,reactive,ref,toRefs,onMounted,computed } from 'vue';
    import { toRaw } from "@vue/reactivity";
    import { Key } from '@/stores/auth';
    import { Session } from '@/utils/storage';
    import request from "@/utils/request";
    import {notify,confirm} from '@/utils/element';
    import { isgrAuth } from '@/utils/utils';
    import {exportExcel} from '@/utils/exportExcel';
    import printJS from 'print-js'

    const AddEdit=defineAsyncComponent(()=>import('@/views/chongwuguanli/add-edit.vue'));
    const DiseaseUpload=defineAsyncComponent(()=>import('@/views/jibingshangchuan/add-edit.vue'));



        







    const state=reactive({
        query:{
          userid:"",
                                                                            chongwumingcheng:'',
                                                                    xingbie:'',
                                                xihao:'',
                                                                                                                                            
    },
    page:{
        current:1,
                size:10,
                total:0
    },
                    tableList:[],
            ids:[],
            isdel:true,
            diagnosisDialogVisible: false,
            diagnosisList: [],
            currentPetName: '',
        
            })
    const { query,tableList,page,isdel,diagnosisDialogVisible,diagnosisList,currentPetName
                                  } = {...toRefs(state)};

    //进入执行
 let sessionTable = Session.get("tableName")
        request({
            url: sessionTable + '/session',
            method: "get"
        }).then((
                data
        ) => {
            if (data && data.code === 0) {

            state.query.userid=data.data.id;
            getDateList();
        } else {
            notify(data.msg,{type:'error'});
        }
    });
    //获取列表
    function getDateList(){
        const params={
            page: state.page.current,
            limit: state.page.size,
            userid:state.query.userid
                                }


                                                                            if (state.query.chongwumingcheng != '' && state.query.chongwumingcheng != undefined) {
                    params['chongwumingcheng'] = '%' + state.query.chongwumingcheng + '%'
                }
                                                                    if (state.query.xingbie != '' && state.query.xingbie != undefined) {
                    params['xingbie'] = '%' + state.query.xingbie + '%'
                }
                                                if (state.query.xihao != '' && state.query.xihao != undefined) {
                    params['xihao'] = '%' + state.query.xihao + '%'
                }
                                                                                                                                                    request({
            url:'chongwuguanli/page',
            method:'get',
            params
        }).then(({data})=>{

            state.page.total=data.total;
        state.tableList=data.list;
            })
    }
    //查询
    function handleQuery(){
        getDateList();
    }

    function download(file) {
        window.open(`${file}`)
    }

    //删除

    function handleDelete(row:any){

        request({
            url:`chongwuguanli/delete`,
            method:'post',
            data:[row.id]
        }).then(({data})=>{
            notify("删除成功",{type:'success'});
        getDateList();

    })
    }

    const multipleSelection = ref<[]>([]);

    function handleSelectionChange(val:any){
        multipleSelection.value=val;
        const list=toRaw(multipleSelection.value)
        list.forEach((item)=>{
            state.ids.push(item.id);
    })

        if(list.length==0){
            state.ids=[];
            state.isdel=true
        }else{
            state.isdel=false
        }

    }


    function listdel(){
        const ids=toRaw(state.ids)
        request({
            url:`chongwuguanli/delete`,
            method:'post',
            data:ids
        }).then(({data})=>{
            notify("删除成功",{type:'success'});
        getDateList();

    })
    }


    const users=Session.get(Key.userInfoKey);

    const editRef=ref();
    const diseaseUploadRef=ref();

    function add(){
        editRef.value.open('新增','','add');
    }
    function handleEdit(row:any){
        editRef.value.open('修改',row.id,'up',row);
    }
    
    // 上传疾病信息
    function uploadDisease(row: any) {
        // 打开疾病上传对话框，并预填宠物名称
        diseaseUploadRef.value.open('上传疾病信息', '', 'add', {chongwumingcheng: row.chongwumingcheng});
    }

    // 显示诊断结果（显示疾病上传信息）
    function showDiagnosis(row: any) {
        state.currentPetName = row.chongwumingcheng;
        // 根据宠物名称查询疾病上传信息
        request({
            url: 'jibingshangchuan/page',
            method: 'get',
            params: {
                page: 1,
                limit: 100,
                chongwumingcheng: row.chongwumingcheng,
                sfsh: '是'  // 只显示审核通过的信息
            }
        }).then(({data}) => {
            state.diagnosisList = data.list;
            state.diagnosisDialogVisible = true;
        }).catch(() => {
            notify('获取诊断信息失败', {type: 'error'});
        });
    }

        

        


        
    

</script>

<template>
  <div class="bg-gray-60">
    <div class="container mx-auto px-4 py-8 max-w-8xl">
    <el-card shadow="never" class="index">
        <template #header>
            <div class="card_header">

                <b>列表</b>
            </div>
        </template>
        <div class="layout-padding">
            <el-form inline :model="query" label-suffix=":">
                <el-form-item label="用户ID"  prop="yonghuming">
                            <el-input readonly="true" v-model="query.userid" maxlength="100" placeholder="用户ID" clearable style="width: 200px;"/>
                        </el-form-item>
                                                                                                            
                        <el-form-item label="宠物名称"  prop="yonghuming">
                            <el-input v-model="query.chongwumingcheng" maxlength="100" placeholder="请输入宠物名称" clearable style="width: 200px;"/>
                        </el-form-item>
                                                                                            
                        <el-form-item label="性别"  prop="yonghuming">
                            <el-input v-model="query.xingbie" maxlength="100" placeholder="请输入性别" clearable style="width: 200px;"/>
                        </el-form-item>
                                                        
                        <el-form-item label="喜好"  prop="yonghuming">
                            <el-input v-model="query.xihao" maxlength="100" placeholder="请输入喜好" clearable style="width: 200px;"/>
                        </el-form-item>
                                                                                                                                                                                                                                                                            <el-form-item>
                    <el-button icon="ele-Search" @click="handleQuery()" type="primary">查询</el-button>
                    <el-button v-if="isgrAuth('chongwuguanli','删除')" icon="ele-Delete" @click="listdel()" :disabled="isdel" type="danger">多选删除</el-button>
                    <el-button v-if="isgrAuth('chongwuguanli','新增')" icon="ele-Plus" type="success" @click="add()">新增</el-button>
                                                                            </el-form-item>
            </el-form>

            <el-table
                    class="w100"
                    ref="tableListRef"
                    :data="tableList"
                    border
                    stripe
                    @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55" />



                                                                                                                                                                                                <el-table-column header-align="center"  align="center" prop="chongwumingcheng" label="宠物名称">

                                    <template #default="{row}">
                                        {{row.chongwumingcheng}}
                                    </template>

                                </el-table-column>

                                                                                                                                    

                            <el-table-column header-align="center"  align="center" prop="tupian" label="图片">

                                <template #default="{row}">
                                    <img v-if="row.tupian" :src="row.tupian" width="100" height="100">
                                    <div v-else>无图片</div>
                                </template>

                            </el-table-column>
                                                                                                                                                                    <el-table-column header-align="center"  align="center" prop="xingbie" label="性别">

                                    <template #default="{row}">
                                        {{row.xingbie}}
                                    </template>

                                </el-table-column>

                                                                                                                                                                                                <el-table-column header-align="center"  align="center" prop="xihao" label="喜好">

                                    <template #default="{row}">
                                        {{row.xihao}}
                                    </template>

                                </el-table-column>

                                                                                                                                                                                                <el-table-column header-align="center"  align="center" prop="xiangqing" label="详情">

                                    <template #default="{row}">
                                        {{row.xiangqing}}
                                    </template>

                                </el-table-column>

                                                                                                                                                                                                <el-table-column header-align="center"  align="center" prop="userid" label="用户id">

                                    <template #default="{row}">
                                        {{row.userid}}
                                    </template>

                                </el-table-column>

                                                                                                                                                                                                <el-table-column header-align="center"  align="center" prop="clicktime" label="最近点击时间">

                                    <template #default="{row}">
                                        {{row.clicktime}}
                                    </template>

                                </el-table-column>

                                                                                                                                                                                                <el-table-column header-align="center"  align="center" label="疾病" width="120">

                                    <template #default="{row}">
                                        <el-button type="success" size="small" @click="uploadDisease(row)">上传</el-button>
                                    </template>

                                </el-table-column>

                                                                                                                                                                                                <el-table-column header-align="center"  align="center" label="诊断结果" width="120">

                                    <template #default="{row}">
                                        <el-button type="primary" size="small" @click="showDiagnosis(row)">诊断结果</el-button>
                                    </template>

                                </el-table-column>

                                                                                                        <el-table-column fixed="right" align="center" label="操作" width="160">
                    <template #default="{row}">
                        <el-button v-if="isgrAuth('chongwuguanli','修改')" icon="ele-Edit" @click.stop="handleEdit(row)" type="warning" link>修改</el-button>
                        <el-popconfirm  v-if="isgrAuth('chongwuguanli','删除')"  width="auto" @confirm="handleDelete(row)"  :title="`确定永久删除吗？`">
                            <template #reference>
                                <el-button icon="ele-Delete" type="danger" link>删除</el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>

            <!-- 分页 -->
            <m-page :page="page" @pageChange="getDateList"/>

            <AddEdit ref="editRef" @refresh="getDateList"/>
            <DiseaseUpload ref="diseaseUploadRef" @refresh="getDateList"/>
        </div>
    </el-card>

    <!-- 诊断结果对话框（显示疾病上传信息） -->
    <el-dialog v-model="diagnosisDialogVisible" :title="'宠物【' + currentPetName + '】的诊断信息'" width="80%" destroy-on-close>
        <el-table :data="diagnosisList" border stripe v-if="diagnosisList.length > 0">
            <el-table-column prop="chongwumingcheng" label="宠物名称" align="center" width="120"/>
            <el-table-column prop="bingqingmiaoshu" label="病情描述" align="center" min-width="200" show-overflow-tooltip/>
            <el-table-column prop="tupianshangchuan" label="图片" align="center" width="120">
                <template #default="{row}">
                    <el-image v-if="row.tupianshangchuan" :src="row.tupianshangchuan" style="width: 80px; height: 80px;" fit="cover" :preview-src-list="[row.tupianshangchuan]" preview-teleported/>
                    <span v-else>无图片</span>
                </template>
            </el-table-column>
            <el-table-column prop="fabushijian" label="发布时间" align="center" width="180"/>
            <el-table-column prop="sfsh" label="审核状态" align="center" width="100">
                <template #default="{row}">
                    <el-tag type="success">已通过</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="shhf" label="医生回复" align="center" min-width="200" show-overflow-tooltip/>
        </el-table>
        <el-empty v-else description="暂无诊断信息" />
    </el-dialog>

    </div>
  </div>
</template>

<style scoped>

</style>

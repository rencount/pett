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

    const AddEdit=defineAsyncComponent(()=>import('@/views/jibingshangchuan/add-edit.vue'));



        







    const state=reactive({
        query:{
          userid:"",
                                                                            chongwumingcheng:'',
                                                bingqingmiaoshu:'',
                                                                                        fabushijian:'',

    },
    page:{
        current:1,
                size:10,
                total:0
    },
                    tableList:[],
            ids:[],
            isdel:true,
                        sfshVisiable:false,
                formData:{},
        doctorInfo:{} as any,

            })
    const { query,tableList,page,isdel
                                    ,sfshVisiable,
            formData
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

            // 如果是宠物医生，保存医生信息用于生成诊断报告
            if (sessionTable === 'chongwuyisheng') {
                state.doctorInfo = data.data;
            } else {
                state.query.userid=data.data.id;
            }
            getDateList();
        } else {
            notify(data.msg,{type:'error'});
        }
    });
    //获取列表
    function getDateList(){
        const params={
            page: state.page.current,
            limit: state.page.size
                                }
        
        // 只有非宠物医生角色才按userid过滤
        if (state.query.userid) {
            params.userid = state.query.userid;
        }
        
        // 宠物医生只查看审核通过的信息
        let sessionTable = Session.get("tableName")
        if (sessionTable === 'chongwuyisheng') {
            params.sfsh = '是';
        }


                                                                            if (state.query.chongwumingcheng != '' && state.query.chongwumingcheng != undefined) {
                    params['chongwumingcheng'] = '%' + state.query.chongwumingcheng + '%'
                }
                                                if (state.query.bingqingmiaoshu != '' && state.query.bingqingmiaoshu != undefined) {
                    params['bingqingmiaoshu'] = '%' + state.query.bingqingmiaoshu + '%'
                }
                                                                                        if (state.query.fabushijian != '' && state.query.fabushijian != undefined) {
                    params['fabushijian'] = '%' + state.query.fabushijian + '%'
                }
                                                                                        request({
            url:'jibingshangchuan/page',
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
            url:`jibingshangchuan/delete`,
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
            url:`jibingshangchuan/delete`,
            method:'post',
            data:ids
        }).then(({data})=>{
            notify("删除成功",{type:'success'});
        getDateList();

    })
    }


    const users=Session.get(Key.userInfoKey);

    const editRef=ref();

    function add(){
        editRef.value.open('新增','','add');
    }
    function handleEdit(row:any){
        editRef.value.open('修改',row.id,'up',row);
    }

        
        function shDialog(row:any){
            state.sfshVisiable=true;
            state.formData=row;
            // 宠物医生只能回复，不能修改审核状态
            // 如果没有回复内容，初始化为空字符串
            if (!state.formData.shhf) {
                state.formData.shhf = '';
            }
            if (!state.formData.zhengzhuang) {
                state.formData.zhengzhuang = '';
            }
        }

        function shHandler(){
            confirm("确认要提交回复吗？").then(()=>{

                request({
                            url:'jibingshangchuan/update',
                            method:'post',
                            data:state.formData
        }).then((data)=>{
            // 宠物医生回复后，同时生成诊断报告
            const sessionTable = Session.get('tableName');
            if (sessionTable === 'chongwuyisheng') {
              const today = new Date().toISOString().split('T')[0];
              const zhenduanData = {
                chongwumingcheng: state.formData.chongwumingcheng,
                zhengzhuang: state.formData.zhengzhuang || '',
                zhiliaojianyi: state.formData.shhf,
                zhenduanshijian: today,
                zhenduanren: state.doctorInfo.nicheng || state.doctorInfo.zhanghao || '',
                lianxifangshi: state.doctorInfo.lianxifangshi || '',
                userid: state.formData.userid
              };
              request({
                url:'zhenduan/save',
                method:'post',
                data: zhenduanData
              }).then(() => {
                state.sfshVisiable=false;
                notify("回复成功，诊断报告已生成",{type:'success'});
                getDateList();
              });
            } else {
              state.sfshVisiable=false;
              notify("回复成功",{type:'success'});
              getDateList();
            }
        })

        })
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
                <el-form-item v-if="query.userid" label="用户ID"  prop="yonghuming">
                            <el-input readonly="true" v-model="query.userid" maxlength="100" placeholder="用户ID" clearable style="width: 200px;"/>
                        </el-form-item>
                                                                                                            
                        <el-form-item label="宠物名称"  prop="yonghuming">
                            <el-input v-model="query.chongwumingcheng" maxlength="100" placeholder="请输入宠物名称" clearable style="width: 200px;"/>
                        </el-form-item>
                                                        
                        <el-form-item label="病情描述"  prop="yonghuming">
                            <el-input v-model="query.bingqingmiaoshu" maxlength="100" placeholder="请输入病情描述" clearable style="width: 200px;"/>
                        </el-form-item>
                                                                                                                                
                        <el-form-item label="发布时间"  prop="yonghuming">
                            <el-input v-model="query.fabushijian" maxlength="100" placeholder="请输入发布时间" clearable style="width: 200px;"/>
                        </el-form-item>
                                                                                                                                                                <el-form-item>
                    <el-button icon="ele-Search" @click="handleQuery()" type="primary">查询</el-button>
                    <el-button v-if="isgrAuth('jibingshangchuan','删除')" icon="ele-Delete" @click="listdel()" :disabled="isdel" type="danger">多选删除</el-button>
                    <el-button v-if="isgrAuth('jibingshangchuan','新增')" icon="ele-Plus" type="success" @click="add()">新增</el-button>
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

                                                                                                                                                                                                <el-table-column header-align="center"  align="center" prop="bingqingmiaoshu" label="病情描述">

                                    <template #default="{row}">
                                        {{row.bingqingmiaoshu}}
                                    </template>

                                </el-table-column>

                                                                                                                                                                                                                    

                            <el-table-column header-align="center"  align="center" prop="tupianshangchuan" label="图片上传">

                                <template #default="{row}">
                                    <img v-if="row.tupianshangchuan" :src="row.tupianshangchuan" width="100" height="100">
                                    <div v-else>无图片</div>
                                </template>

                            </el-table-column>
                                                                                                                                                                                                                                                    <el-table-column header-align="center"  align="center" prop="fabushijian" label="发布时间">

                                    <template #default="{row}">
                                        {{row.fabushijian}}
                                    </template>

                                </el-table-column>

                                                                                                                                                                                                <el-table-column header-align="center"  align="center" prop="userid" label="用户id">

                                    <template #default="{row}">
                                        {{row.userid}}
                                    </template>

                                </el-table-column>

                                                                                                                                                                
                                <el-table-column header-align="center"  align="center" prop="sfsh" label="审核状态">

                                    <template #default="{row}">
                                        <el-tag type="success">已通过</el-tag>
                                    </template>

                                </el-table-column>
                                <el-table-column header-align="center"  align="center" label="回复操作" width="120">

                                    <template #default="{row}">
                                        <el-button type="primary" size="small" @click="shDialog(row)">回复
                                        </el-button>
                                    </template>

                                </el-table-column>


                                                                                                                                                                                                <el-table-column header-align="center"  align="center" prop="shhf" label="审核回复">

                                    <template #default="{row}">
                                        {{row.shhf}}
                                    </template>

                                </el-table-column>

                                                                                                        <el-table-column fixed="right" align="center" label="操作" width="160">
                    <template #default="{row}">
                        <el-button v-if="isgrAuth('jibingshangchuan','修改')" icon="ele-Edit" @click.stop="handleEdit(row)" type="warning" link>修改</el-button>
                        <el-popconfirm  v-if="isgrAuth('jibingshangchuan','删除')"  width="auto" @confirm="handleDelete(row)"  :title="`确定永久删除吗？`">
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
        </div>
    </el-card>

    
        <el-dialog
                title="回复疾病信息"
                v-model="sfshVisiable"
                width="600px">
            <el-form ref="form" :model="formData" label-width="100px">
                <el-form-item label="宠物名称">
                    <el-input v-model="formData.chongwumingcheng" disabled></el-input>
                </el-form-item>
                <el-form-item label="病情描述">
                    <el-input type="textarea" :rows="3" v-model="formData.bingqingmiaoshu" disabled></el-input>
                </el-form-item>
                <el-form-item label="症状">
                    <el-input type="textarea" :rows="4" v-model="formData.zhengzhuang" placeholder="请输入症状诊断..."></el-input>
                </el-form-item>
                <el-form-item label="治疗建议">
                    <el-input type="textarea" :rows="6" v-model="formData.shhf" placeholder="请输入治疗建议..."></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="sfshVisiable = false">取消</el-button>
                    <el-button type="primary" @click="shHandler">提交回复</el-button>
                </span>
            </template>
        </el-dialog>

    

    </div>
  </div>
</template>

<style scoped>

</style>

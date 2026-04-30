<script setup lang='ts'>
    import { defineAsyncComponent,reactive,ref,toRefs } from 'vue';
    import { toRaw } from "@vue/reactivity";
    import { Key } from '@/stores/auth';
    import { Session } from '@/utils/storage';
    import request from "@/utils/request";
    import {notify} from '@/utils/element';
    import { isAuth } from '@/utils/utils'
    import { ElLoading } from 'element-plus'
    const AddEdit=defineAsyncComponent(()=>import('@/views/zixun/add-edit.vue'));
    const state=reactive({

        dataList: [],
        searchForm:{
            biaoti:'',

        },
                page:{
            current:1,
            size:10,
            total:0
        },
        showFlag:true,
    })

    const ragRef=ref();
    const ragRefInstance=ref();
    const {dataList,searchForm,page,showFlag
            } = {...toRefs(state)};

    getDataList()

        
        
    function download(file) {
        window.open(`${file}`)
    }
        
        function getDataList(){

            ragRefInstance.value = ElLoading.service({
                target: ragRef.value,
                body: true,
                text: 'Loading...',
            })
            const params={
                page: state.page.current,
                limit: state.page.size,
            }


                                                                                                        if (state.searchForm.chongwuyisheng != '' && state.searchForm.chongwuyisheng != undefined) {
                        params['chongwuyisheng'] = '%' + state.searchForm.chongwuyisheng + '%'
                    }
                                                                                            if (state.searchForm.xingbie != '' && state.searchForm.xingbie != undefined) {
                        params['xingbie'] = '%' + state.searchForm.xingbie + '%'
                    }
                                                                                                                

            request({
                url:'zixun/list',
                method:'get',
                params
            }).then((data)=>{
                ragRefInstance.value.close()
            state.page.total=data.data.total;
            state.dataList=data.data.list;

        })
        }
            function handleQuery(){
        getDataList()
    }

    const editRef=ref();

    function add(){
        editRef.value.open('新增','add');
    }
    function handleEdit(row:any){
        editRef.value.open('修改','up',row);
    }
    function handleDelete(row:any){

        request({
            url:`zixun/delete`,
            method:'post',
            data:[row.id]
        }).then(({data})=>{
            notify("删除成功",{type:'success'});
          getDataList();

    })
    }

</script>

<template ref="ragRefInstance">



  <div class="min-h-screen bg-gradient-to-b from-blue-50 via-indigo-50 to-purple-50">
    <!-- 在线咨询 -->
    <div class="py-20">
      <div class="max-w-7xl mx-auto px-4">
        <h2 class="text-4xl font-bold text-center mb-4 text-indigo-600">💬 在线咨询服务</h2>
        <p class="text-gray-600 text-center mb-12 text-lg">专业医生在线解答，为您的爱宠提供贴心建议</p>
        <div class="max-w-4xl mx-auto mb-12">
          <div class="flex items-center justify-center gap-4">
            <!-- 搜索框 -->
            <input 
              v-model="searchForm.chongwuyisheng" 
              type="text" 
              placeholder="请输入医生姓名" 
              class="px-6 py-3 rounded-full border-2 border-indigo-200 focus:border-indigo-400 focus:outline-none w-64 shadow-md transition-all"
              @keyup.enter="handleQuery()"
            />
            <input 
              v-model="searchForm.xingbie" 
              type="text" 
              placeholder="请输入性别" 
              class="px-6 py-3 rounded-full border-2 border-indigo-200 focus:border-indigo-400 focus:outline-none w-48 shadow-md transition-all"
              @keyup.enter="handleQuery()"
            />
            <button @click="handleQuery()" class="px-8 py-3 rounded-full bg-gradient-to-r from-indigo-400 to-purple-400 text-white hover:from-indigo-500 hover:to-purple-500 transition-all hover:scale-105 whitespace-nowrap font-semibold shadow-lg">
              <i class="fas fa-search mr-2"></i>搜索咨询
            </button>
          </div>
        </div>
        <div class="grid grid-cols-4 gap-8">
          <div v-for="(item, index) in dataList" :key="index" class="group cursor-pointer">
            <div class="overflow-hidden rounded-2xl shadow-lg border-2 border-indigo-100 hover:border-indigo-300 transition-all duration-300 bg-white hover:shadow-2xl hover:scale-105">
              <router-link :to="`/zixundetail/`+item.id">
                <div class="relative">
                  <img :src="item.touxiang" class="w-full h-64 object-cover object-center transition-transform duration-300 group-hover:scale-110">
                  <!-- 医生姓名叠加层 -->
                  <div class="absolute bottom-0 left-0 right-0 bg-gradient-to-t from-black/70 to-transparent p-4">
                    <h3 class="text-white text-xl font-bold drop-shadow-lg">{{ item.chongwuyisheng }}</h3>
                    <p class="text-white/90 text-sm">{{ item.xingbie }}</p>
                  </div>
                </div>
              </router-link>
            </div>
          </div>
        </div>
      </div>
      <m-page :page="page" style="text-align: center;" @pageChange="getDataList"/>
    </div>

  </div>


    
        </template>

<style  scoped>
    .el-row.is-justify-end {
        justify-content: center;
    }

</style>

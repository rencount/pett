<script setup lang='ts'>
    import { defineAsyncComponent,reactive,ref,toRefs } from 'vue';
    import { toRaw } from "@vue/reactivity";
    import { Key } from '@/stores/auth';
    import { Session } from '@/utils/storage';
    import request from "@/utils/request";
    import {notify} from '@/utils/element';
    import { isAuth } from '@/utils/utils'
    import { ElLoading } from 'element-plus'
    const AddEdit=defineAsyncComponent(()=>import('@/views/xuexiguanli/add-edit.vue'));
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


                                                                                                        if (state.searchForm.biaoti != '' && state.searchForm.biaoti != undefined) {
                        params['biaoti'] = '%' + state.searchForm.biaoti + '%'
                    }
                                                                                                                                                                                                                                                                                        

            request({
                url:'xuexiguanli/list',
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
            url:`xuexiguanli/delete`,
            method:'post',
            data:[row.id]
        }).then(({data})=>{
            notify("删除成功",{type:'success'});
          getDataList();

    })
    }

</script>

<template ref="ragRefInstance">



  <div class="min-h-screen bg-gradient-to-b from-purple-50 via-blue-50 to-indigo-50 pt-16">
    <!-- 学习管理 -->
    <div class="py-20">
      <div class="max-w-7xl mx-auto px-4">
        <h2 class="text-4xl font-bold text-center mb-4 text-purple-600">📚 宠物护理知识学堂</h2>
        <p class="text-gray-600 text-center mb-12 text-lg">学习科学的宠物护理知识，成为更好的铲屎官</p>
        <div class="max-w-4xl mx-auto mb-12">
          <div class="flex items-center gap-4">

                                                                                                                                                                                                                                                      <div class="relative flex-1">
              <input type="text" placeholder="搜索课程标题" v-model="searchForm.biaoti" class="w-full px-6 py-3 border-2 border-purple-200 rounded-full focus:outline-none focus:border-purple-400 pl-12 text-base shadow-md">
              <i class="fas fa-book absolute left-5 top-1/2 -translate-y-1/2 text-purple-400 text-lg"></i>
            </div>

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
            <button @click="handleQuery()" class="px-8 py-3 rounded-full bg-gradient-to-r from-purple-400 to-blue-400 text-white hover:from-purple-500 hover:to-blue-500 transition-all hover:scale-105 whitespace-nowrap font-semibold shadow-lg">
              <i class="fas fa-search mr-2"></i>搜索课程
            </button>
          </div>
        </div>
        <div class="grid grid-cols-4 gap-8">
          <div v-for="(item, index) in dataList" :key="index" class="group cursor-pointer">
            <div class="overflow-hidden rounded-2xl shadow-lg border-2 border-purple-100 hover:border-purple-300 transition-all duration-300 bg-white hover:shadow-2xl">





  <router-link :to="`/xuexiguanlidetail/`+item.id">
              <img :src="item.tupian" class="w-full h-64 object-cover object-center transition-transform duration-300 group-hover:scale-110">
</router-link>
            </div>
                                                                                            <h3 class="mt-4 text-lg font-semibold text-purple-700 group-hover:text-purple-500 transition-colors">{{ item.biaoti }}</h3>
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

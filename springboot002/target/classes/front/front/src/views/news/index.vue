<script setup lang='ts'>
    import { defineAsyncComponent,reactive,ref,toRefs } from 'vue';
    import { toRaw } from "@vue/reactivity";
    import { Key } from '@/stores/auth';
    import { Session } from '@/utils/storage';
    import request from "@/utils/request";
    import {notify} from '@/utils/element';
    import { isAuth } from '@/utils/utils'
    import { ElLoading } from 'element-plus'



    const state=reactive({

        dataList: [],
        searchForm:{
            title:'',

        },

        page:{
            current:1,
            size:10,
            total:0
        },
        showFlag:true
    })

    const ragRef=ref();
    const ragRefInstance=ref();
    const {dataList,searchForm,page,showFlag} = {...toRefs(state)};

    getDataList()







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



                if (state.searchForm.title != '' && state.searchForm.title != undefined) {
                    params['title'] = '%' + state.searchForm.title + '%'
                }



        request({
            url:'news/list',
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


</script>

<template ref="ragRefInstance">



  <div class="min-h-screen bg-gradient-to-b from-pink-50 via-purple-50 to-blue-50 py-20">
    <div class="max-w-7xl mx-auto px-4">
      <h2 class="text-4xl font-bold text-center mb-4 text-pink-600">📰 宠物健康资讯</h2>
      <p class="text-gray-600 text-center mb-12 text-lg">了解最新的宠物健康知识和养护技巧</p>
      <div class="grid grid-cols-2 gap-8">



        <div class="bg-white rounded-2xl shadow-lg overflow-hidden hover:shadow-2xl hover:scale-105 transition-all duration-300 border-2 border-pink-100"  v-for="(item, index) in dataList" :key="index">
          <div class="flex">
            <div class="w-1/3">
              <img :src="item.picture" alt="" class="w-full h-full object-cover">
            </div>
            <div class="w-2/3 p-6">
              <div class="flex items-center mb-3">
                <span class="text-pink-500 text-sm">📅 {{item.addtime}}</span>
              </div>
              <h3 class="text-xl font-bold mb-3 text-purple-700">{{item.title}}</h3>
              <p class="text-gray-600 line-clamp-2">{{item.introduction}}</p>
              <router-link :to="`/newsdetail/`+item.id">
                <button class="mt-4 px-4 py-2 bg-gradient-to-r from-pink-400 to-purple-400 text-white rounded-full hover:from-pink-500 hover:to-purple-500 flex items-center text-sm transition-all font-semibold">
                  阅读更多 <i class="fas fa-arrow-right ml-2"></i>
                </button>
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
    /*@import '@/assets/news.css';*/
    .el-row.is-justify-end {
        justify-content: center;
    }

</style>

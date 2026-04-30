
<!-- 代码已包含 CSS：使用 TailwindCSS , 安装 TailwindCSS 后方可看到布局样式效果 -->
<template>
  <div class="min-h-screen bg-gray-50">
    <div class="mx-auto max-w-7xl p-8">
      <div class="rounded-xl bg-white p-8 shadow-lg">

        <div class="mb-12 flex gap-12">

          <div class="relative w-2/5 overflow-hidden" @click="handleImagePreview">
                                                                                                                                                                                                                                                                                  <img :src="detail.touxiang" alt="" class="h-[400px] w-full rounded-lg object-cover object-top cursor-pointer hover:opacity-90 transition-opacity"/>
                                                                                                                                                                                                                                                                                                            
          </div>

          <div class="w-3/5">
                                                                                                                            <h1 class="mb-6 text-4xl font-bold text-gray-900">{{detail.chongwuyisheng}}</h1>
                                                                                                      <h1 class="mb-6 text-4xl font-bold text-gray-900">{{detail.xingbie}}</h1>
                                                                                                                                                           <div class="mb-6 space-y-2">
                                                  <div class="flex items-center gap-2">
                <span class="font-bold text-gray-700">宠物医生：</span>
                <span class="text-gray-600">{{detail.chongwuyisheng}}</span>
              </div>
                                                        <div class="flex items-center gap-2">
                <span class="font-bold text-gray-700">性别：</span>
                <span class="text-gray-600">{{detail.xingbie}}</span>
              </div>
                                        <div class="flex items-center gap-2">
                <span class="font-bold text-gray-700">联系方式：</span>
                <span class="text-gray-600">{{detail.lianxifangshi}}</span>
              </div>
                                        <div class="flex items-start gap-2">
                <span class="font-bold text-gray-700 whitespace-nowrap">详情：</span>
                <div class="text-gray-600 prose prose-sm max-w-none">{{ formattedXiangqing }}</div>
              </div>
                                                  </div>

            <div class="mb-6 flex items-center gap-4">

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                                    


                             


                                                                  <button @click="wd" class="flex items-center gap-2 rounded-lg bg-red-500 px-4 py-2 text-white hover:bg-red-600 transition-colors !rounded-button whitespace-nowrap">
                                    <i class="fas fa-share-alt"></i>
                                    <span>问 诊</span>
                                  </button>
                                                                                    

                                                                                                                                                


            </div>

          </div>
        </div>
        <!-- 详情区移到了上方 -->
        <!-- 评论区 -->

        <div class="mt-12">
          <h2 class="mb-6 text-2xl font-bold text-gray-900">评论区</h2>
          <!-- 评论输入框 -->
          <div class="mb-8 flex gap-4">
            <div class="h-10 w-10 flex-shrink-0 flex items-center justify-center">
              <i class="fas fa-user text-gray-400 text-xl"></i>
            </div>
            <div class="flex-grow">
              <textarea v-model="formData.content" class="min-h-[100px] w-full rounded-lg border border-gray-200 p-4 text-gray-700 focus:border-blue-500 focus:outline-none" placeholder="写下你的评论..."></textarea>
              <button @click="addpl()" class="mt-4 flex items-center gap-2 rounded-lg bg-blue-500 px-6 py-2 text-white hover:bg-blue-600 transition-colors !rounded-button whitespace-nowrap">
                <i class="fas fa-paper-plane"></i>
                <span>发表评论</span>
              </button>
            </div>
          </div>
          <!-- 评论列表 -->
          <div class="space-y-8">
            <div class="flex gap-4" v-for="item in pinglunDate " :key="item">
              <div class="h-10 w-10 flex-shrink-0 flex items-center justify-center">
                <i class="fas fa-user text-gray-400 text-xl"></i>
              </div>
              <div class="flex-grow">
                <div class="flex items-center gap-2">
                  <span class="font-bold text-gray-900">{{item.nickname}}</span>
                </div>
                <p class="mt-2 text-gray-700">{{item.content}}</p>
                <div class="mt-4 rounded-lg bg-gray-50 p-4" v-if="item.reply">
                  <div class="space-y-4">
                    <div class="flex gap-3">
                      <div class="h-8 w-8 flex-shrink-0 flex items-center justify-center">
                        <i class="fas fa-user text-gray-400 text-sm"></i>
                      </div>
                      <div>
                        <div class="flex items-center gap-2">
                          <span class="font-semibold text-gray-900">管理员</span>
                        </div>
                        <p class="mt-1 text-sm text-gray-700">{{item.reply}}</p>
                      </div>
                    </div>

                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>

        

      </div>
    </div>
    <!-- 图片预览弹窗 -->
    <div v-if="showPreview" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-75" @click="closePreview">
      <img :src="detail.tupian" alt="预览图" class="max-h-[90vh] max-w-[90vw] object-contain"/>
    </div>
  </div>




        

        
             
                  <Wd ref="WdRef"></Wd>
                </template>


<script setup>



    import { defineAsyncComponent,reactive,ref,toRefs,computed } from 'vue';
    import { toRaw } from "@vue/reactivity";
    import { Key } from '@/stores/auth';
    import { Session } from '@/utils/storage';
    import request from "@/utils/request";
    import {notify,confirm} from '@/utils/element';
    import { isAuth } from '@/utils/utils'
    import { ElLoading } from 'element-plus'
            const showPreview = ref(false);
    const handleImagePreview = () => {
      showPreview.value = true;
    };
    const closePreview = () => {
      showPreview.value = false;
    };

    import { useRoute } from 'vue-router'

                const Wd=defineAsyncComponent(()=>import('@/views/wd/index.vue'));
        const WdRef=ref();
        


        
    const route = useRoute()

        
                function wd() {
          WdRef.value.openaddOrupdate(state.detail);
        }
        
        
    // 打印
    const id=route.params.id
    request({
        url: `zixun/info/${id}`,
        method: "get"
    }).then((data) => {
        if (data && data.code === 0) {
        state.detail=data.data;
                
            getpllist()

            } else {
        notify(data.msg,{type:'error'});
    }
    });

            const state=reactive({
                detail:{},
        user:{},
        pinglunDate:[],
                                detailTable:'zixun',
        formData:{
            userid:"",
            nickname:"",
            refid:"",
            content:""
        },
        detailFlag:false,
    })


    const {detail,user,pinglunDate,detailFlag,
                                        detailTable,
        formData,
    } = {...toRefs(state)};

    // 计算属性：处理详情内容，去除两端字符及 HTML 标签，只保留输入的文本
    const formattedXiangqing = computed(() => {
        const rawData = state.detail.xiangqing || '';
        if (!rawData) return '';
        
        // 1. 先将常见转义字符反转义
        let decoded = rawData.replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&amp;/g, '&').replace(/&quot;/g, '"');
        
        // 2. 利用 DOMParser 获取纯文本
        const parser = new DOMParser();
        const doc = parser.parseFromString(decoded, 'text/html');
        return doc.documentElement.textContent || '';
    });

                

        






    let sessionTable = Session.get("tableName")
    request({
        url: sessionTable + '/session',
        method: "get"
    }).then((
            data
    ) => {
        if (data && data.code === 0) {
        state.user = data.data;
    } else {
        notify(data.msg,{type:'error'});


    }
    });




    function download(file) {
        window.open(`${file}`)
    }


    function init(id){
        state.detailFlag=true;
        info(id);
            }
    function info(id) {
        request({
            url: `zixun/info/${id}`,
            method: "get"
        }).then((data) => {
            if (data && data.code === 0) {
            state.detail=data.data;
        } else {
            notify(data.msg,{type:'error'});
        }
    });
    }





        
        function getpllist() {
            request({
                url: `discusszixun/list`,
                method: "get",
                params: {
                    refid: state.detail.id,
                }
            }).then((data) => {
                if (data && data.code === 0) {
                state.pinglunDate=data.data.list;
            } else {
                notify(data.msg,{type:'error'});
            }
        });
        }





        function addpl(){
            state.formData.nickname=Session.get("adminName")
            state.formData.userid=state.user.id
            state.formData.refid=state.detail.id

            request({
                url: `discusszixun/save`,
                method: "post",
                data:state.formData
            }).then((data) => {
                if (data && data.code === 0) {
                notify("评论成功",{type:'success'});

                getpllist();
            } else {
                notify(data.msg,{type:'error'});
            }
        });
        }

        
        

        
        


        
        
        

</script>

<style scoped>

        

                @import '@/assets/pinglun.css';
                
        
        
</style>

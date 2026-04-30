<script setup lang='ts'>
    import { defineAsyncComponent,reactive,ref,toRefs,onMounted,onUnmounted } from 'vue';
    import { toRaw } from "@vue/reactivity";
    import { Key } from '@/stores/auth';
    import { Session } from '@/utils/storage';
    import request from "@/utils/request";
    import {notify} from '@/utils/element';
    import { isAuth } from '@/utils/utils'

    // const indexlunbo=defineAsyncComponent(()=>import('@/layout/indexlunbo.vue'));
    // const indexwz=defineAsyncComponent(()=>import('@/layout/indexwz.vue'));
    // const indexleibiao=defineAsyncComponent(()=>import('@/layout/indexliebiao.vue'));


    const state=reactive({

                        xuexiguanliList: [],
                        zixunList: [],
                                chongwuguanlirecommendList: [],
                        xuexiguanlirecommendList: [],
                             newsList:[],
                  username:'',
        user:{}
    })

    const {
                        xuexiguanliList,
                        zixunList,
                                chongwuguanlirecommendList,
                        xuexiguanlirecommendList,
                          newsList,
                username,
        user

    } = {...toRefs(state)};

                getxuexiguanli()
                getzixun()
            getnews()
                function getnews(){
          const params={
            page: 0,
            limit: 8,
            sort: 'id',
          }
          request({
            url:'news/list',
            method:'get',
            params
          }).then((data)=>{
            state.newsList=data.data.list;
          })
        }

        

                function getxuexiguanli(){
            const params={
                page: 0,
                limit: 8,
                sort: 'id',
            }


            request({
                url:'xuexiguanli/list',
                method:'get',
                params
            }).then((data)=>{
                state.xuexiguanliList=data.data.list;
        })
        }




                function getzixun(){
            const params={
                page: 0,
                limit: 8,
                sort: 'id',
            }


            request({
                url:'zixun/list',
                method:'get',
                params
            }).then((data)=>{
                state.zixunList=data.data.list;
        })
        }




        


    state.username=Session.get("adminName");
    if(state.username){
        let sessionTable = Session.get("tableName")
        request({
            url: sessionTable + '/session',
            method: "get"
        }).then((data) => {
            if (data && data.code === 0) {
            state.user = data.data;

                            request({
                    url:'chongwuguanli/recommendList',
                    method:'POST',
                    params:{
                        num: 8,
                        userId:data.data.id,
                    }
                }).then((data)=>{
                    state.chongwuguanlirecommendList=data.data;
            })

                            request({
                    url:'xuexiguanli/recommendList',
                    method:'POST',
                    params:{
                        num: 8,
                        userId:data.data.id,
                    }
                }).then((data)=>{
                    state.xuexiguanlirecommendList=data.data;
            })

            

        } else {
            notify(data.msg,{type:'error'});
        }
    });



    }


    import { Swiper, SwiperSlide } from 'swiper/vue';
    import { Pagination, Autoplay } from 'swiper/modules';
    const swiperModules = [Pagination, Autoplay];
    const showBackToTop = ref(false);
    const handleScroll = () => {
      showBackToTop.value = window.scrollY > 300;
    };
    const scrollToTop = () => {
      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      });
    };
    onMounted(() => {
      window.addEventListener('scroll', handleScroll);
    });
    onUnmounted(() => {
      window.removeEventListener('scroll', handleScroll);
    });

</script>

<template>



  <div class="min-h-screen bg-gradient-to-b from-orange-50 via-pink-50 to-purple-50">

    <!-- 欢迎横幅 -->
    <div class="py-16 bg-gradient-to-r from-pink-100 via-purple-100 to-blue-100">
      <div class="max-w-7xl mx-auto px-4 text-center">
        <h1 class="text-5xl font-bold mb-6 bg-gradient-to-r from-pink-500 via-purple-500 to-blue-500 bg-clip-text text-transparent">
          🐾 宠物疾病诊断系统 🐾
        </h1>
        <p class="text-2xl text-gray-700 mb-4">用爱守护每一个毛孩子的健康</p>
        <p class="text-lg text-gray-600">专业、温馨、贴心的宠物医疗服务平台</p>
      </div>
    </div>

    <!-- 服务特色 -->
    <div class="py-20 bg-white">
      <div class="max-w-7xl mx-auto px-4">
        <h2 class="text-4xl font-bold text-center mb-4 text-purple-600">🌟 为什么选择我们</h2>
        <p class="text-gray-600 text-center mb-16 text-lg">专业的宠物医疗团队，为您的爱宠提供全方位健康守护</p>
        <div class="space-y-20">
          <div class="flex items-center gap-16">
            <div class="w-1/2">
              <img src="https://images.unsplash.com/photo-1450778869180-41d0601e046e?w=800" alt="专业医疗" class="w-full h-[400px] object-cover rounded-2xl shadow-2xl hover:shadow-pink-200 transition-shadow duration-300">
            </div>
            <div class="w-1/2">
              <div class="bg-gradient-to-br from-pink-50 to-purple-50 p-8 rounded-2xl">
                <h3 class="text-3xl font-bold mb-6 text-purple-600">🏥 专业医疗团队</h3>
                <p class="text-gray-700 text-lg leading-relaxed mb-4">
                  我们拥有经验丰富的宠物医生团队，配备先进的诊断设备，为您的爱宠提供准确、及时的疾病诊断服务。
                </p>
                <p class="text-gray-700 text-lg leading-relaxed">
                  24小时在线咨询，随时随地关注您爱宠的健康状况，让每一个毛孩子都能得到最贴心的照顾。
                </p>
              </div>
            </div>
          </div>

          <div class="flex items-center gap-16">
            <div class="w-1/2">
              <div class="bg-gradient-to-br from-blue-50 to-indigo-50 p-8 rounded-2xl">
                <h3 class="text-3xl font-bold mb-6 text-blue-600">💬 实时医生咨询</h3>
                <p class="text-gray-700 text-lg leading-relaxed mb-4">
                  专业宠物医生在线实时解答，为您的宠物提供快速、准确的疾病诊断和治疗建议。
                </p>
                <p class="text-gray-700 text-lg leading-relaxed">
                  支持在线上传宠物症状图片和视频，医生会根据实际情况给出专业的诊断意见，让您第一时间了解爱宠的健康状况。
                </p>
              </div>
            </div>
            <div class="w-1/2">
              <img src="https://images.unsplash.com/photo-1530281700549-e82e7bf110d6?w=800" alt="实时咨询" class="w-full h-[400px] object-cover rounded-2xl shadow-2xl hover:shadow-blue-200 transition-shadow duration-300">
            </div>
          </div>

          <div class="flex items-center gap-16">
            <div class="w-1/2">
              <img src="https://images.unsplash.com/photo-1415369629372-26f2fe60c467?w=800" alt="诊断记录" class="w-full h-[400px] object-cover rounded-2xl shadow-2xl hover:shadow-green-200 transition-shadow duration-300">
            </div>
            <div class="w-1/2">
              <div class="bg-gradient-to-br from-green-50 to-teal-50 p-8 rounded-2xl">
                <h3 class="text-3xl font-bold mb-6 text-green-600">📋 诊断记录管理</h3>
                <p class="text-gray-700 text-lg leading-relaxed mb-4">
                  系统自动保存每一次的诊断详情，包括症状描述、医生诊断意见、治疗方案等完整信息。
                </p>
                <p class="text-gray-700 text-lg leading-relaxed">
                  随时查看历史诊断记录，方便追踪宠物的健康变化趋势，为后续治疗提供重要参考依据。
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 数据统计 -->
    <div class="py-20 bg-gradient-to-br from-purple-50 to-pink-50">
      <div class="max-w-7xl mx-auto px-4">
        <h2 class="text-4xl font-bold text-center mb-4 text-purple-600">📊 我们的成绩</h2>
        <p class="text-gray-600 text-center mb-16 text-lg">用数据说话，用专业守护</p>
        <div class="grid grid-cols-4 gap-8">
          <div class="bg-white p-8 rounded-2xl shadow-lg hover:shadow-2xl transition-all duration-300 text-center border-2 border-pink-100">
            <div class="text-5xl font-bold text-pink-500 mb-4">10000+</div>
            <div class="text-xl text-gray-700 font-semibold">服务宠物数量</div>
            <div class="text-gray-500 mt-2">累计为上万只宠物提供服务</div>
          </div>
          <div class="bg-white p-8 rounded-2xl shadow-lg hover:shadow-2xl transition-all duration-300 text-center border-2 border-purple-100">
            <div class="text-5xl font-bold text-purple-500 mb-4">50+</div>
            <div class="text-xl text-gray-700 font-semibold">专业医生团队</div>
            <div class="text-gray-500 mt-2">经验丰富的宠物医疗专家</div>
          </div>
          <div class="bg-white p-8 rounded-2xl shadow-lg hover:shadow-2xl transition-all duration-300 text-center border-2 border-blue-100">
            <div class="text-5xl font-bold text-blue-500 mb-4">98%</div>
            <div class="text-xl text-gray-700 font-semibold">用户满意度</div>
            <div class="text-gray-500 mt-2">获得用户高度认可</div>
          </div>
          <div class="bg-white p-8 rounded-2xl shadow-lg hover:shadow-2xl transition-all duration-300 text-center border-2 border-green-100">
            <div class="text-5xl font-bold text-green-500 mb-4">24/7</div>
            <div class="text-xl text-gray-700 font-semibold">全天候服务</div>
            <div class="text-gray-500 mt-2">随时随地守护爱宠健康</div>
          </div>
        </div>
      </div>
    </div>

    <!-- 服务流程 -->
    <div class="py-20 bg-white">
      <div class="max-w-7xl mx-auto px-4">
        <h2 class="text-4xl font-bold text-center mb-4 text-indigo-600">🔄 服务流程</h2>
        <p class="text-gray-600 text-center mb-16 text-lg">简单四步，轻松获得专业诊断</p>
        <div class="grid grid-cols-4 gap-8">
          <div class="text-center">
            <div class="w-32 h-32 mx-auto mb-6 bg-gradient-to-br from-pink-400 to-purple-400 rounded-full flex items-center justify-center shadow-xl">
              <span class="text-6xl text-white font-bold">1</span>
            </div>
            <h3 class="text-2xl font-bold mb-4 text-pink-600">📝 注册登录</h3>
            <p class="text-gray-600 text-lg">创建账号，建立宠物档案</p>
          </div>
          <div class="text-center">
            <div class="w-32 h-32 mx-auto mb-6 bg-gradient-to-br from-purple-400 to-blue-400 rounded-full flex items-center justify-center shadow-xl">
              <span class="text-6xl text-white font-bold">2</span>
            </div>
            <h3 class="text-2xl font-bold mb-4 text-purple-600">📸 上传症状</h3>
            <p class="text-gray-600 text-lg">描述症状，上传相关图片</p>
          </div>
          <div class="text-center">
            <div class="w-32 h-32 mx-auto mb-6 bg-gradient-to-br from-blue-400 to-green-400 rounded-full flex items-center justify-center shadow-xl">
              <span class="text-6xl text-white font-bold">3</span>
            </div>
            <h3 class="text-2xl font-bold mb-4 text-blue-600">🔍 医生诊断</h3>
            <p class="text-gray-600 text-lg">专业医生实时诊断</p>
          </div>
          <div class="text-center">
            <div class="w-32 h-32 mx-auto mb-6 bg-gradient-to-br from-green-400 to-teal-400 rounded-full flex items-center justify-center shadow-xl">
              <span class="text-6xl text-white font-bold">4</span>
            </div>
            <h3 class="text-2xl font-bold mb-4 text-green-600">💊 获得方案</h3>
            <p class="text-gray-600 text-lg">收到治疗建议和护理方案</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 用户评价 -->
    <div class="py-20 bg-gradient-to-br from-orange-50 to-pink-50">
      <div class="max-w-7xl mx-auto px-4">
        <h2 class="text-4xl font-bold text-center mb-4 text-orange-600">💬 用户好评</h2>
        <p class="text-gray-600 text-center mb-16 text-lg">听听铲屎官们怎么说</p>
        <div class="grid grid-cols-3 gap-8">
          <div class="bg-white p-8 rounded-2xl shadow-lg hover:shadow-2xl transition-all duration-300 border-2 border-orange-100">
            <div class="flex items-center mb-6">
              <div class="w-16 h-16 bg-gradient-to-br from-pink-400 to-purple-400 rounded-full flex items-center justify-center text-white text-2xl font-bold">李</div>
              <div class="ml-4">
                <div class="font-bold text-lg text-gray-800">李女士</div>
                <div class="text-gray-500">金毛主人</div>
              </div>
            </div>
            <div class="text-yellow-400 text-2xl mb-4">⭐⭐⭐⭐⭐</div>
            <p class="text-gray-700 text-lg leading-relaxed">
              "我家金毛突然食欲不振，通过这个平台快速得到了诊断，医生非常专业，给出的治疗方案也很有效。现在狗狗已经恢复健康了，真的很感谢！"
            </p>
          </div>
          <div class="bg-white p-8 rounded-2xl shadow-lg hover:shadow-2xl transition-all duration-300 border-2 border-pink-100">
            <div class="flex items-center mb-6">
              <div class="w-16 h-16 bg-gradient-to-br from-blue-400 to-indigo-400 rounded-full flex items-center justify-center text-white text-2xl font-bold">王</div>
              <div class="ml-4">
                <div class="font-bold text-lg text-gray-800">王先生</div>
                <div class="text-gray-500">英短主人</div>
              </div>
            </div>
            <div class="text-yellow-400 text-2xl mb-4">⭐⭐⭐⭐⭐</div>
            <p class="text-gray-700 text-lg leading-relaxed">
              "24小时在线咨询太方便了！半夜猫咪突然不舒服，立刻就能联系到医生。平台的电子档案功能也很实用，所有疫苗记录都清清楚楚。"
            </p>
          </div>
          <div class="bg-white p-8 rounded-2xl shadow-lg hover:shadow-2xl transition-all duration-300 border-2 border-purple-100">
            <div class="flex items-center mb-6">
              <div class="w-16 h-16 bg-gradient-to-br from-green-400 to-teal-400 rounded-full flex items-center justify-center text-white text-2xl font-bold">张</div>
              <div class="ml-4">
                <div class="font-bold text-lg text-gray-800">张女士</div>
                <div class="text-gray-500">柯基主人</div>
              </div>
            </div>
            <div class="text-yellow-400 text-2xl mb-4">⭐⭐⭐⭐⭐</div>
            <p class="text-gray-700 text-lg leading-relaxed">
              "平台上的宠物护理知识非常丰富，学到了很多科学养宠的方法。医生团队也很负责，每次咨询都能得到详细的解答。强烈推荐给所有养宠人士！"
            </p>
          </div>
        </div>
      </div>
    </div>
    <!-- 新闻动态 -->
    <div class="py-20 bg-gradient-to-br from-pink-50 to-purple-50">
      <div class="max-w-7xl mx-auto px-4">
        <h2 class="text-4xl font-bold text-center mb-4 text-pink-600">📰 宠物健康资讯</h2>
        <p class="text-gray-600 text-center mb-12 text-lg">了解最新的宠物健康知识和养护技巧</p>
        <div class="grid grid-cols-2 gap-8">



          <div class="bg-white rounded-2xl shadow-lg overflow-hidden hover:shadow-2xl hover:scale-105 transition-all duration-300 border-2 border-pink-100"  v-for="(item, index) in newsList" :key="index">
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
                  <button class="mt-4 px-4 py-2 bg-gradient-to-r from-pink-400 to-purple-400 text-white rounded-full hover:from-pink-500 hover:to-purple-500 flex items-center text-sm transition-all">
                    阅读更多 <i class="fas fa-arrow-right ml-2"></i>
                  </button>
                </router-link>
              </div>
            </div>
          </div>




        </div>
      </div>
    </div>

      <!-- 学习管理 -->
    <div class="py-20 bg-white">
      <div class="max-w-7xl mx-auto px-4">
        <h2 class="text-4xl font-bold text-center mb-4 text-blue-600">📚 宠物护理知识学堂</h2>
        <p class="text-gray-600 text-center mb-12 text-lg">学习科学的宠物护理知识，成为更好的铲屎官</p>
        <div class="grid grid-cols-4 gap-8">
          <div v-for="(item, index) in xuexiguanliList" :key="index" class="group cursor-pointer">
            <div class="overflow-hidden rounded-2xl shadow-lg border-2 border-blue-100 hover:border-blue-300 transition-all duration-300">
              <router-link :to="`/xuexiguanlidetail/`+item.id">
              <span v-for="(value, key, ind) in item" :key="ind">
              <img v-if="ind==3" :src="value" class="w-full h-64 object-cover object-center transition-transform duration-300 group-hover:scale-110">
                </span>
              </router-link>

            </div>
            <span v-for="(value, key, ind) in item" :key="ind">
            <h3 v-if="ind==2" class="mt-4 text-lg font-semibold text-blue-700 group-hover:text-blue-500 transition-colors">{{ value }}</h3>
                </span>
          </div>
        </div>
      </div>
    </div>
    <div class="py-20 bg-gradient-to-br from-blue-50 to-indigo-50">
      <div class="max-w-7xl mx-auto px-4">
        <h2 class="text-4xl font-bold text-center mb-4 text-indigo-600">💬 在线咨询服务</h2>
        <p class="text-gray-600 text-center mb-12 text-lg">专业医生在线解答，为您的爱宠提供贴心建议</p>
        <div class="grid grid-cols-4 gap-8">
          <div v-for="(item, index) in zixunList" :key="index" class="group cursor-pointer">
            <div class="overflow-hidden rounded-2xl shadow-lg border-2 border-indigo-100 hover:border-indigo-300 transition-all duration-300 bg-white">
              <router-link :to="`/zixundetail/`+item.id">
              <span v-for="(value, key, ind) in item" :key="ind">
              <img v-if="ind==3" :src="value" class="w-full h-64 object-cover object-center transition-transform duration-300 group-hover:scale-110">
                </span>
              </router-link>

            </div>
            <span v-for="(value, key, ind) in item" :key="ind">
            <h3 v-if="ind==2" class="mt-4 text-lg font-semibold text-indigo-700 group-hover:text-indigo-500 transition-colors">{{ value }}</h3>
                </span>
          </div>
        </div>
      </div>
    </div>
      <!-- 订阅区域 -->
    <div class="py-20 bg-gradient-to-br from-pink-400 via-purple-400 to-blue-400">
      <div class="max-w-7xl mx-auto px-4 text-center">
        <h2 class="text-4xl font-bold text-white mb-4">💌 订阅宠物健康资讯</h2>
        <p class="text-white/90 mb-8 text-lg">第一时间获取宠物健康知识和养护技巧</p>
        <div class="flex items-center justify-center space-x-4">
          <input type="email" placeholder="请输入您的邮箱地址" class="px-6 py-3 w-96 rounded-full border-none focus:outline-none focus:ring-2 focus:ring-white">
          <button class="px-8 py-3 rounded-full bg-white text-pink-600 hover:bg-pink-50 transition-all hover:scale-105 whitespace-nowrap font-semibold shadow-lg">
            🐾 立即订阅
          </button>
        </div>
      </div>
    </div>
    <!-- 页脚 -->

    <!-- 回到顶部按钮 -->
    <button
        v-show="showBackToTop"
        @click="scrollToTop"
        class="fixed bottom-8 right-8 p-4 bg-gradient-to-r from-pink-400 to-purple-400 text-white rounded-full shadow-2xl hover:from-pink-500 hover:to-purple-500 transition-all hover:scale-110 z-50">
      <i class="fas fa-arrow-up text-xl"></i>
    </button>
  </div>





</template>

<style  scoped>


</style>

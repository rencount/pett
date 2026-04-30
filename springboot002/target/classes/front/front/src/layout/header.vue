
<script setup lang="ts">
import {menu} from '@/utils/menu'
import { toRaw } from "@vue/reactivity";
import { Session } from '@/utils/storage';
import { ref,reactive,toRefs,defineAsyncComponent } from "vue";
import {GetUsersToken} from '@/api/auth'
   import {useRoute,useRouter} from 'vue-router'
   import { Key } from '@/stores/auth';
const Register=defineAsyncComponent(()=>import('@/views/register/index.vue'));
const Login=defineAsyncComponent(()=>import('@/views/login/index.vue'));

   const route=useRoute();
   const router = useRouter();
const state=reactive({
  menuList: [],
  role:"",
  user: {},
islogin:''
})
state.role = Session.get('role')
const { menuList,role,user,islogin} = {...toRefs(state)};
const menus = menu.list()
state.menuList = toRaw(menus);
 if(Session.get(Key.isLoingKey)==null){
    state.islogin='0';
}else{
    state.islogin=Session.get(Key.isLoingKey);
}
console.log(state.menuList);

   function menuHandler(name:any) {
      name = '/'+name
      router.push(name)
    }

       if(state.islogin=='1'){
        getutoken();
    }


    async function getutoken(){
  try {
    const {data} = await GetUsersToken();
    state.user=data;
      } catch (error) {

     }

    }
        const WdRef=ref();
    function wd() {
      WdRef.value.openaddOrupdate();
    }
   

const LoginRef=ref();
function dl(){
  LoginRef.value.showlogin();
}
const RegisterRef=ref();
function  register(tableName) {
  RegisterRef.value.open(tableName);
}

    function tc(){
       Session.remove(Key.accessTokenKey);
       Session.remove("tableName");
        Session.remove("role");
        Session.remove("adminName");
        Session.set(Key.isLoingKey,'0');
        state.islogin='0';
       router.push("/")
    }
</script>
<template>



  <!-- 导航栏 -->
  <nav class="fixed top-0 w-full z-50 backdrop-blur-md bg-white/80 shadow-sm">
    <div class="max-w-7xl mx-auto px-4 h-16 flex items-center justify-between">
      <div class="flex items-center space-x-2">
        <i class="fas fa-mountain text-2xl text-blue-600"></i>
        <span class="text-xl font-bold">宠物疾病诊断系统</span>
      </div>
      <div class="flex items-center space-x-8" v-if="islogin=='0'">

        <div v-for="item in menuList" :key="item.roleName">

          <p style="margin-left: 0;" v-if="'游客'==item.roleName">
     <span style="cursor:pointer">
     <a style="margin-right: 24px;" @click="menuHandler(`home`)" class="hover:text-blue-600 transition-colors">首页</a>
   </span>
            <span  style="margin-right: 24px;" v-for="(menu,index1) in item.ykMenu" :key="index1">
       <a style="cursor:pointer" v-for=" (child,sort) in menu.child" :key="sort" @click="menuHandler(child.tableName)" class="hover:text-blue-600 transition-colors">{{ menu.menu}} </a>
       </span>
          </p>
        </div>
      </div>



      <div class="flex items-center space-x-8" v-if="islogin=='1'">
        <div v-for="item in menuList" :key="item.roleName">

          <p style="margin-left: 0;" v-if="role==item.roleName">
        <span style="cursor:pointer">
        <a style="margin-right: 24px;" @click="menuHandler(`home`)" class="hover:text-blue-600 transition-colors">首页</a>
      </span>
            <span  style="margin-right: 24px;" v-for="(menu,index1) in item.frontMenu" :key="index1">
          <a style="cursor:pointer" v-for=" (child,sort) in menu.child" :key="sort" @click="menuHandler(child.tableName)" class="hover:text-blue-600 transition-colors">{{ menu.menu}} </a>
          </span>
            <a  style="cursor:pointer;" @click="menuHandler('grzx')" class="hover:text-blue-600 transition-colors">个人中心</a>
                                      </p>
        </div>
      </div>
      <div class="flex items-center space-x-4">
        <button v-if="islogin=='0'" @click="dl()" class="px-4 py-2 !rounded-button bg-gradient-to-r from-blue-500 to-purple-500 text-white hover:opacity-90 transition-opacity whitespace-nowrap">登录</button>
        <button v-if="islogin=='0'" @click="register('yonghu')" class="px-4 py-2 !rounded-button border border-blue-500 text-blue-500 hover:bg-blue-50 transition-colors whitespace-nowrap">注册用户</button>
               <button v-if="islogin=='1'" @click="tc" class="px-4 py-2 !rounded-button border border-blue-500 text-blue-500 hover:bg-blue-50 transition-colors whitespace-nowrap">退出</button>
      </div>
    </div>
  </nav>


    

  <Register ref="RegisterRef" v-if="islogin=='0'"></Register>
  <Login ref="LoginRef" v-if="islogin=='0'"></Login>



</template>

<style scoped>

</style>

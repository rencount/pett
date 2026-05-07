<script setup lang="ts" name="LayoutVerticalMenu">
import {useRoute,useRouter} from 'vue-router'
import {useLayoutConfigStore} from '@/stores/layoutConfig'
import { toRaw } from "@vue/reactivity";
import { storeToRefs } from 'pinia';
import menu from '@/utils/menu'
import { Session } from '@/utils/storage';
import { reactive,toRefs,ref } from 'vue';
const route=useRoute();
const router = useRouter();
const layoutConfig=useLayoutConfigStore();
const {isCollapse}=storeToRefs(layoutConfig);
//  console.log('isCollapse',isCollapse.value);

const props= defineProps<{
  type:number
}>();


const state=reactive({
  menuList: [] as any[],
  dynamicMenuRoutes: [],
  role: '',
  icon:[
    'ele-Calendar',
    'ele-Box',
    'ele-Money',
    'ele-Refrigerator',
    'ele-Cpu',
    'ele-Football',
    'ele-Brush',
    'ele-Suitcase',
    'ele-Monitor',
    'ele-BrushFilled',
    'ele-DataBoard',
    'ele-DataLine',
    'ele-Reading',
    'ele-FirstAidKit',
    'ele-ScaleToOriginal',
    'ele-ShoppingTrolley',
    'ele-Timer',
    'ele-Sunset',
    'ele-SwitchFilled',
    'ele-Dish',
    'ele-Dessert',
    'ele-Burger',
    'ele-GobletSquare',
    'ele-Cherry',

  ]
})

const { menuList,dynamicMenuRoutes,role,icon} = {...toRefs(state)};
const menus = menu.list()
state.menuList = toRaw(menus);
console.log("菜单",state.menuList);

state.role = Session.get('role')
function menuHandler(name:any) {
  // console.log("跳转页面:"+name);

  name = '/'+name
  router.push(name)
}


const showUserMenu = ref(false);
const submenuOpen = ref({
  pets: true,
  appointments: false
});

const toggleSubmenu = (menu: string) => {
  submenuOpen.value[menu as keyof typeof submenuOpen.value] = !submenuOpen.value[menu as keyof typeof submenuOpen.value];
};

</script>

<template>
  <div class="vertical-menu-container">

    <el-menu
        :default-active="route.path"
        class="el-menu-vertical-demo"
        :collapse="isCollapse"
        :router="true"
        text-color="#303133"
        active-text-color="#d63384"
    >
      <template v-for="(item, index) in menuList" :key="item.roleName">
        <template v-if="role == item.roleName">
          <!-- 首页 -->
          <el-menu-item index="/home">
            <el-icon><ele-HomeFilled /></el-icon>
            <template #title>首页</template>
          </el-menu-item>

          <!-- 个人中心 -->
          <el-menu-item index="/center">
            <el-icon><ele-User /></el-icon>
            <template #title>个人中心</template>
          </el-menu-item>

          <!-- 动态菜单 -->
          <template v-for="(menu, menuIndex) in item.backMenu" :key="menu.menu">
            <el-sub-menu v-if="menu.child.length > 1" :index="`/${menu.child[0].tableName}`">
              <template #title>
                <el-icon><SvgIcon :name="icon[menuIndex]" /></el-icon>
                <span>{{ menu.menu }}</span>
              </template>
              <el-menu-item v-for="child in menu.child" :key="child.tableName" :index="`/${child.tableName}`">
                <el-icon><SvgIcon :name="icon[menuIndex]" /></el-icon>
                {{ child.menu }}
              </el-menu-item>
            </el-sub-menu>

            <el-menu-item v-else v-for="child in menu.child" :key="child.tableName" :index="`/${child.tableName}`">
              <el-icon><SvgIcon :name="icon[menuIndex]" /></el-icon>
              <template #title>{{ child.menu }}</template>
            </el-menu-item>
          </template>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<style scoped>
.vertical-menu-container {
  height: calc(100% - 56px); /* 减去logo高度 */
  display: flex;
  flex-direction: column;
}
.project-title-bar {
  padding: 15px 20px;
  font-size: 14px;
  font-weight: 600;
  background-color: #fce4ec;
  color: #c2185b;
  flex-shrink: 0;
}
.el-menu {
  flex-grow: 1;
  border-right: none;
  background-color: #ffffff;
}
.el-menu-item:hover {
  background-color: #f8f9fa !important;
}
.el-menu-item.is-active {
  background-color: #fce4ec !important;
  color: #d63384 !important;
}
</style>
<script setup lang="ts" name="LayoutHeader">
   import {defineAsyncComponent} from 'vue';
   const Breadcrumb=defineAsyncComponent(()=>import('@/layout/layoutHeader/breadcrumb.vue'))
   const UserDropdown=defineAsyncComponent(()=>import('@/layout/layoutHeader/userDropdown.vue'))

 const props= defineProps<{
    id:number,
    img:string,
    uname:string
  }>();

</script>

<template>
  <el-header class="layout-header">
    <!--右侧头部区域-->
      <div class="layout-header-index">
        <!--面包屑-->
     <Breadcrumb/>
     <!--右侧下拉菜单-->
     <UserDropdown :img="props.img" :uname="props.uname" :id="props.id"/>
    </div>
    </el-header>
</template>

<style scoped>
.layout-header {
  position: relative; /* 确保 z-index 生效 */
  z-index: 10;      /* 提升层级，确保在最前 */
  height: 50px;
  display: flex;
  align-items: center;
  background: rgba(255, 255, 255, 0.5) !important; /* 强制应用半透明背景 */
  backdrop-filter: blur(10px) !important; /* 强制应用背景模糊 */
  border-bottom: 1px solid rgba(255, 255, 255, 0.2);
  color: #303133;
  padding: 0 20px;
}
.layout-header-index{
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

:deep(.el-breadcrumb__inner),
:deep(.el-breadcrumb__separator) {
  color: #303133;
}
:deep(.el-dropdown) {
    color:#303133;
}
</style>
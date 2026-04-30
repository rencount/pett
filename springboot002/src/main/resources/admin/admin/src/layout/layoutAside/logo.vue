<script setup lang="ts" name="LayoutLogo">
   import {useLayoutConfigStore} from '@/stores/layoutConfig'
   import { storeToRefs } from 'pinia';
   const layoutConfig=useLayoutConfigStore();
   const {isCollapse,globalTitle}=storeToRefs(layoutConfig)
</script>

<template>
   <div class="layout-logo">
     <div class="logo-icon">🐾</div>
     <span v-if="!isCollapse" class="logo-text">{{globalTitle}}</span>
   </div>
</template>

<style scoped>
.layout-logo {
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #a8c0ff 0%, #c2e9fb 100%);
  color: #2c3e50;
  font-size: 18px;
  font-weight: bold;
  position: relative;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.layout-logo::before {
  content: '';
  position: absolute;
  top: -50%;
  right: -50%;
  width: 200%;
  height: 200%;
  background: radial-gradient(circle, rgba(255,255,255,0.1) 0%, transparent 70%);
  animation: rotate 20s linear infinite;
}

@keyframes rotate {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

.logo-icon {
  font-size: 36px;
  animation: bounce 2s ease-in-out infinite;
  position: relative;
  z-index: 1;
  filter: drop-shadow(0 2px 4px rgba(0, 0, 0, 0.1));
}

@keyframes bounce {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-5px);
  }
}

.logo-text {
  margin-left: 12px;
  white-space: nowrap;
  position: relative;
  z-index: 1;
  text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
  letter-spacing: 1px;
  font-weight: 700;
}
</style>
<script setup lang="ts" name="Home">
import { ref } from 'vue'

const username = ref('管理员')
</script>

<template>
  <div class="welcome-container">
    <div class="content-wrapper">
      <div class="text-content">
        <div class="icon-wrapper">
          <div class="pet-icon">🐾</div>
        </div>
        <h1 class="title">欢迎回来,{{ username }}!</h1>
        <p class="subtitle">
          宠物疾病诊断管理系统
        </p>
        <p class="description">
          守护每一只宠物的健康,让爱与关怀触手可及
        </p>
        <p class="guide">
          您可以在左侧的导航菜单中管理宠物诊断信息、用户数据和系统资讯
        </p>
      </div>
      <div class="image-wrapper">
        <img src="https://images.unsplash.com/photo-1450778869180-41d0601e046e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1770&q=80" alt="宠物健康" class="welcome-image" />
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.welcome-container {
  padding: 60px 40px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #a8c0ff 0%, #c2e9fb 100%);
  border-radius: 16px;
  position: relative;
  overflow: hidden;
  
  &::before {
    content: '';
    position: absolute;
    top: -50%;
    right: -50%;
    width: 200%;
    height: 200%;
    background: radial-gradient(circle, rgba(255,255,255,0.1) 0%, transparent 70%);
    animation: rotate 20s linear infinite;
  }
}

@keyframes rotate {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

.content-wrapper {
  display: flex;
  align-items: center;
  gap: 80px;
  max-width: 1200px;
  position: relative;
  z-index: 1;
}

.text-content {
  flex: 1;
  
  .icon-wrapper {
    margin-bottom: 25px;
    
    .pet-icon {
      font-size: 60px;
      display: inline-block;
      animation: bounce 2s ease-in-out infinite;
    }
  }
  
  .title {
    font-size: 42px;
    font-weight: 700;
    color: #2c3e50;
    margin-bottom: 20px;
    text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  .subtitle {
    font-size: 24px;
    color: #34495e;
    margin-bottom: 20px;
    font-weight: 600;
    letter-spacing: 1px;
  }
  
  .description {
    font-size: 16px;
    color: #5a6c7d;
    line-height: 1.8;
    margin-bottom: 15px;
    font-weight: 400;
  }
  
  .guide {
    font-size: 14px;
    color: #5a6c7d;
    line-height: 1.6;
    padding: 15px 20px;
    background: rgba(255, 255, 255, 0.5);
    border-radius: 8px;
    border-left: 4px solid #a8c0ff;
    backdrop-filter: blur(10px);
  }
}

@keyframes bounce {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-10px);
  }
}

.image-wrapper {
  flex: 1;
  
  img.welcome-image {
    width: 100%;
    max-width: 500px;
    border-radius: 20px;
    box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    
    &:hover {
      transform: scale(1.05);
      box-shadow: 0 25px 80px rgba(0, 0, 0, 0.4);
    }
  }
}
</style>

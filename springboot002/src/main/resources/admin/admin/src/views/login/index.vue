<template>
  <div class="login-container">
    <div class="login-box">
      <!-- 左侧欢迎信息 -->
      <div class="welcome-panel">
        <div class="welcome-content">
          <div class="pet-icon-large">🐾</div>
          <h2 class="title">宠物疾病诊断系统</h2>
          <p class="subtitle">Pet Disease Diagnosis System</p>
          <p class="description">守护每一只宠物的健康</p>
        </div>
      </div>

      <!-- 右侧登录表单 -->
      <div class="form-panel">
        <h3 class="form-title">账号登录</h3>
        <form @submit.prevent="handleSubmit" class="space-y-6">
          <!-- 用户名 -->
          <div class="form-group">
            <i class="fas fa-user icon"></i>
            <input type="text" v-model="loginData.username" class="form-input" placeholder="请输入账号">
          </div>
          <!-- 密码 -->
          <div class="form-group">
            <i class="fas fa-lock icon"></i>
            <input :type="showPassword ? 'text' : 'password'" v-model="loginData.password" class="form-input"
              placeholder="请输入密码">
            <button type="button" @click="togglePassword"
              class="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600">
              <i :class="showPassword ? 'fas fa-eye-slash' : 'fas fa-eye'"></i>
            </button>
          </div>
          <!-- 验证码 -->
          <div class="flex space-x-4 items-center">
            <div class="form-group flex-1">
              <i class="fas fa-shield-alt icon"></i>
              <input type="text" v-model="formData.captcha" maxlength="4" class="form-input" placeholder="验证码">
            </div>
            <div ref="captchaRef" @click="refreshCaptcha" class="captcha-container">
              <span v-for="(char, index) in captchaCode" :key="index"
                :style="{ color: getRandomColor(), transform: `rotate(${Math.random() * 20 - 10}deg)` }"
                class="captcha-char">{{ char }}</span>
              <!-- 干扰线 -->
              <div v-for="n in 0" :key="`line-${n}`" class="captcha-line"
                :style="{ background: getRandomColor(), transform: `rotate(${Math.random() * 180}deg)` }"></div>
            </div>
          </div>
          <!-- 角色选择 -->
          <div class="space-y-2">
            <label class="block text-sm font-medium text-gray-700">选择角色</label>
            <div class="flex flex-wrap gap-x-6 gap-y-2">
              <template v-for="(item, index) in menus" :key="index">
                <label v-if="item && item.roleName !== '游客' && item.roleName !== '用户'"
                  class="flex items-center space-x-2 cursor-pointer">
                  <input type="radio" :value="item.roleName" v-model="loginData.role"
                    class="h-4 w-4 text-pink-500 border-gray-300 focus:ring-pink-400">
                  <span class="text-sm text-gray-700">{{ item.roleName }}</span>
                </label>
              </template>
            </div>
          </div>

          <!-- 登录按钮 -->
          <button type="submit" :disabled="!isFormValid" class="login-button"
            :class="{ 'opacity-50 cursor-not-allowed': !isFormValid }">
            登 录
          </button>

        </form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, computed, toRefs, onMounted } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRouter, useRoute } from 'vue-router';
import { Session } from '@/utils/storage';
import menu from "@/utils/menu";

const authStore = useAuthStore();

const router = useRouter();
const route = useRoute();

const state = reactive({
  loading: false,
  loginData: {
    username: '',
    password: '',
    role: ''
  } as LoginData,
  tableName: ''
});

const menus = menu.list();
console.log('DEBUG: Loaded roles are - ', JSON.stringify(menus));

const { loading, loginData, tableName } = { ...toRefs(state) };
const showPassword = ref(false);
const formData = ref({
  captcha: ''
});
const captchaRef = ref<HTMLDivElement | null>(null);
const captchaCode = ref('');

const getRandomChar = () => {
  const chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
  return chars[Math.floor(Math.random() * chars.length)];
};
const getRandomColor = () => {
  const colors = ['#f472b6', '#60a5fa', '#818cf8', '#fb923c'];
  return colors[Math.floor(Math.random() * colors.length)];
};
const generateCaptcha = () => {
  let code = '';
  for (let i = 0; i < 4; i++) {
    code += getRandomChar();
  }
  return code;
};
const refreshCaptcha = () => {
  captchaCode.value = generateCaptcha();
  formData.value.captcha = '';
};
onMounted(() => {
  refreshCaptcha();
});
const isFormValid = computed(() => {
  return state.loginData.username &&
    state.loginData.password &&
    state.loginData.role &&
    formData.value.captcha.length === 4;
});
const togglePassword = () => {
  showPassword.value = !showPassword.value;
};
const handleSubmit = async () => {
  if (!isFormValid.value) return;
  if (formData.value.captcha.toUpperCase() !== captchaCode.value.toUpperCase()) {
    alert('验证码错误!');
    refreshCaptcha();
    return;
  }

  for (let i = 0; i < menus.length; i++) {
    if (menus[i].roleName == loginData.value.role) {
      state.tableName = menus[i].tableName;
    }
  }

  const res = await authStore.userLogin(state.loginData, `${state.tableName}/login`);
  // console.log('submitForm', res);
  // 跳转到来源地址  login?redirect=/home
  Session.set("tableName", state.tableName);
  Session.set("role", state.loginData.role);
  Session.set("adminName", state.loginData.username);
  router.replace({ path: <string>route.query?.redirect || '/' });

};

interface LoginData {
  username: string;
  password?: string;
  role: string;
}

</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');

.login-container {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #a8c0ff 0%, #c2e9fb 100%);
  padding: 2rem;
  position: relative;
  overflow: hidden;
}

.login-container::before {
  content: '🐕 🐈 🐾';
  position: absolute;
  font-size: 80px;
  opacity: 0.05;
  animation: float 30s linear infinite;
  white-space: nowrap;
}

@keyframes float {
  from {
    transform: translateX(-100%);
  }
  to {
    transform: translateX(100vw);
  }
}

.login-box {
  display: flex;
  width: 100%;
  max-width: 960px;
  min-height: 600px;
  background-color: #fff;
  border-radius: 20px;
  box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.25);
  overflow: hidden;
}

/* 左侧欢迎面板 */
.welcome-panel {
  flex-basis: 50%;
  background: linear-gradient(135deg, #a8c0ff 0%, #c2e9fb 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  color: #2c3e50;
  padding: 3rem;
  text-align: center;
  position: relative;
  overflow: hidden;
}

.welcome-panel::before {
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

.welcome-content {
  position: relative;
  z-index: 1;
}

.welcome-content .pet-icon-large {
  font-size: 100px;
  margin: 0 auto 1.5rem;
  animation: bounce 2s ease-in-out infinite;
  filter: drop-shadow(0 4px 15px rgba(0, 0, 0, 0.2));
}

@keyframes bounce {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-15px);
  }
}

.welcome-content .title {
  font-size: 2.5rem;
  font-weight: bold;
  text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
}

.welcome-content .subtitle {
  font-size: 1rem;
  opacity: 0.8;
  margin-top: 0.5rem;
  letter-spacing: 1px;
}

.welcome-content .description {
  font-size: 1.25rem;
  opacity: 0.85;
  margin-top: 1rem;
  font-weight: 500;
}


/* 右侧表单面板 */
.form-panel {
  flex-basis: 50%;
  padding: 4rem 3rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.form-title {
  font-size: 2rem;
  font-weight: 700;
  color: #333;
  margin-bottom: 2.5rem;
  text-align: center;
}

.form-group {
  position: relative;
  margin-bottom: 1.5rem;
}

.form-group .icon {
  position: absolute;
  left: 0.5rem;
  top: 50%;
  transform: translateY(-50%);
  color: #9ca3af;
  /* gray-400 */
}

.form-input {
  width: 100%;
  padding: 0.75rem 0.75rem 0.75rem 2.5rem;
  border: none;
  border-bottom: 2px solid #e5e7eb;
  /* gray-200 */
  background-color: transparent;
  outline: none;
  transition: border-color 0.3s;
  font-size: 1rem;
}

.form-input:focus {
  border-color: #f472b6;
  /* A pink color to match the theme */
}


/* 验证码样式 */
.captcha-container {
  width: 120px;
  height: 48px;
  border-radius: 8px;
  background: #f3f4f6;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  letter-spacing: 4px;
  position: relative;
  overflow: hidden;
}

.captcha-char {
  font-size: 1.5rem;
  font-weight: bold;
  user-select: none;
}

.captcha-line {
  position: absolute;
  width: 100%;
  height: 2px;
  top: 50%;
  left: 0;
}


/* 登录按钮 */
.login-button {
  width: 100%;
  padding: 0.8rem;
  border: none;
  border-radius: 8px;
  background: linear-gradient(135deg, #a8c0ff 0%, #c2e9fb 100%);
  color: #2c3e50;
  font-size: 1.1rem;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(168, 192, 255, 0.4);
  margin-top: 1rem;
}

.login-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(168, 192, 255, 0.6);
  background: linear-gradient(135deg, #c2e9fb 0%, #a8c0ff 100%);
}

.login-button:disabled {
  background: #d1d5db;
  /* gray-300 */
  box-shadow: none;
  cursor: not-allowed;
}
</style>

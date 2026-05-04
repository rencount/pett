<script setup lang="ts">
      import { Session } from '@/utils/storage';
      import {ref,onMounted,reactive,toRefs, nextTick} from 'vue';
      import request from "@/utils/request";
       import {notify} from '@/utils/element';
       import { uploadImg } from '@/api/common/media';

      import base from "@/utils/base";

      
      const state=reactive({
                ruleForm: {},
                flag: '',
                                                                                                                                                                                                                                    yonghuxingbieOptions: [],
                                                                                                                                                                                                                                                                                                                                    chongwuyishengxingbieOptions: [],
                                                                                                                })
   
      const { ruleForm,flag
                                                                                                                                                                                                                                   , yonghuxingbieOptions
                                                                                                                                                                                                                                                                                                                                   , chongwuyishengxingbieOptions
                                                                                                                 } = {...toRefs(state)};

      // 修改密码相关
      const passwordDialogVisible = ref(false);
      const passwordFormRef = ref();
      const passwordForm = ref({
          oldPassword: '',
          newPassword: '',
          confirmPassword: ''
      });

      const validateConfirmPassword = (_rule: any, value: any, callback: any) => {
          if (value === '') {
              callback(new Error('请再次输入新密码'));
          } else if (value !== passwordForm.value.newPassword) {
              callback(new Error('两次输入的新密码不一致'));
          } else {
              callback();
          }
      };

      const passwordRules = {
          oldPassword: [{ required: true, message: '请输入原密码', trigger: 'blur' }],
          newPassword: [{ required: true, message: '请输入新密码', trigger: 'blur' }],
          confirmPassword: [
              { required: true, message: '请再次输入新密码', trigger: 'blur' },
              { validator: validateConfirmPassword, trigger: 'blur' }
          ]
      };

      // 打开修改密码弹窗
      function showPasswordDialog() {
          passwordForm.value = { oldPassword: '', newPassword: '', confirmPassword: '' };
          passwordDialogVisible.value = true;
          nextTick(() => {
              passwordFormRef.value?.resetFields();
          });
      }

      // 提交修改密码
      function onSubmitPassword() {
          passwordFormRef.value?.validate((valid: boolean) => {
              if (!valid) return;
              request({
                  url: `${tableName}/updatePassword`,
                  method: 'post',
                  data: passwordForm.value
              }).then((data: any) => {
                  notify('密码修改成功', { type: 'success' });
                  passwordDialogVisible.value = false;
              });
          });
      }
     
      const tableName=Session.get("tableName");
     
      nextTick(()=>{

      state.flag = tableName;
     request({
        url:`${tableName}/session`,
        method:'GET'
      }).then((data) => { 
            state.ruleForm = data.data;
    });
   

      })





                                                                                                                                                                async function handleUploadImg(options: any){
                          // console.log('handleUploadImg', options);
                          try {
                              // loadding.value = true;
                              const form = new FormData();
                              form.append('file', options.file);
                              form.append('data', JSON.stringify({sourceType: 'goods_img'}));
                              // 开始上传
                              const data = await uploadImg(form);

                              // 上传成功  , 将上传成功的图片地址赋值回显出来
                              const name=base.get().url;
                              ruleForm.value.touxiang = name+'upload/'+ data.file;
                          } catch(e) {

                          } finally {
                              // loadding.value = false;
                          }
                      }

                                                                                                                              state.yonghuxingbieOptions = "男,女".split(',')
                      
                                                                                                                              state.chongwuyishengxingbieOptions = "男,女".split(',')
                      
                                                                                                              

    function onUpdateHandler(){


                                                                                                                if ((!state.ruleForm.zhanghao) && 'yonghu' == state.flag) {

                        notify('账号不能为空',{type:'error'})
                        return
                    }
                                                                                                                                                if ((!state.ruleForm.lianxifangshi) && 'yonghu' == state.flag) {

                        notify('联系方式不能为空',{type:'error'})
                        return
                    }
                                                                if ((!state.ruleForm.nicheng) && 'yonghu' == state.flag) {

                        notify('昵称不能为空',{type:'error'})
                        return
                    }
                                                                                                                                            if ((!state.ruleForm.zhanghao) && 'chongwuyisheng' == state.flag) {

                        notify('账号不能为空',{type:'error'})
                        return
                    }
                                                                                                                                                if ((!state.ruleForm.lianxifangshi) && 'chongwuyisheng' == state.flag) {

                        notify('联系方式不能为空',{type:'error'})
                        return
                    }
                                                                if ((!state.ruleForm.nicheng) && 'chongwuyisheng' == state.flag) {

                        notify('昵称不能为空',{type:'error'})
                        return
                    }
                                    
           request({
             url:`${tableName}/update`,
             method:'post',
             data:state.ruleForm
           }).then((data)=>{
            notify('修改信息成功',{type:'success'})
           })



    }




</script>
<template>
    <div>
        <el-form
                class="detail-form-content"
                ref="RefForm"
                :model="ruleForm"
                label-width="80px"
        >
            <el-row>

                                                                                                                                                                            <el-col :span="12">
                        <el-form-item v-if="flag=='yonghu'" label="账号" prop="zhanghao">
                                                            <el-input v-model="ruleForm.zhanghao" placeholder="账号" clearable></el-input>
                                </el-form-item>
                                </el-col>
                                                                                                                        <el-col :span="12">
                        <el-form-item v-if="flag=='yonghu'" label="头像" prop="touxiang">
                                                            <el-upload
                                        class="avatar-uploader"
                                        accept="image/png,image/jpg,image/jpeg"
                                        action="#"
                                        :show-file-list="false"
                                        :http-request="handleUploadImg"
                                >
                                    <img style="width: 100px;height: 100px;" v-if="ruleForm.touxiang" :src="ruleForm.touxiang" class="avatar" />
                                    <el-icon v-else class="avatar-uploader-icon"><ele-Plus /></el-icon>

                                </el-upload>
                                </el-form-item>
                                </el-col>
                                                                                                                        <el-col :span="12">
                        <el-form-item v-if="flag=='yonghu'" label="性别" prop="xingbie">
                                                            <el-select v-model="ruleForm.xingbie" placeholder="请选择性别">
                                    <el-option
                                            v-for="(item,index) in yonghuxingbieOptions"
                                            v-bind:key="index"
                                            :label="item"
                                            :value="item">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            </el-col>
                                                                                                                        <el-col :span="12">
                        <el-form-item v-if="flag=='yonghu'" label="联系方式" prop="lianxifangshi">
                                                            <el-input v-model="ruleForm.lianxifangshi" placeholder="联系方式" clearable></el-input>
                                </el-form-item>
                                </el-col>
                                                                                                                        <el-col :span="12">
                        <el-form-item v-if="flag=='yonghu'" label="昵称" prop="nicheng">
                                                            <el-input v-model="ruleForm.nicheng" placeholder="昵称" clearable></el-input>
                                </el-form-item>
                                </el-col>
                                                                                                                                                                                                                                                    <el-col :span="12">
                        <el-form-item v-if="flag=='chongwuyisheng'" label="账号" prop="zhanghao">
                                                            <el-input v-model="ruleForm.zhanghao" placeholder="账号" clearable></el-input>
                                </el-form-item>
                                </el-col>
                                                                                                                        <el-col :span="12">
                        <el-form-item v-if="flag=='chongwuyisheng'" label="头像" prop="touxiang">
                                                            <el-upload
                                        class="avatar-uploader"
                                        accept="image/png,image/jpg,image/jpeg"
                                        action="#"
                                        :show-file-list="false"
                                        :http-request="handleUploadImg"
                                >
                                    <img style="width: 100px;height: 100px;" v-if="ruleForm.touxiang" :src="ruleForm.touxiang" class="avatar" />
                                    <el-icon v-else class="avatar-uploader-icon"><ele-Plus /></el-icon>

                                </el-upload>
                                </el-form-item>
                                </el-col>
                                                                                                                        <el-col :span="12">
                        <el-form-item v-if="flag=='chongwuyisheng'" label="性别" prop="xingbie">
                                                            <el-select v-model="ruleForm.xingbie" placeholder="请选择性别">
                                    <el-option
                                            v-for="(item,index) in chongwuyishengxingbieOptions"
                                            v-bind:key="index"
                                            :label="item"
                                            :value="item">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            </el-col>
                                                                                                                        <el-col :span="12">
                        <el-form-item v-if="flag=='chongwuyisheng'" label="联系方式" prop="lianxifangshi">
                                                            <el-input v-model="ruleForm.lianxifangshi" placeholder="联系方式" clearable></el-input>
                                </el-form-item>
                                </el-col>
                                                                                                                        <el-col :span="12">
                        <el-form-item v-if="flag=='chongwuyisheng'" label="昵称" prop="nicheng">
                                                            <el-input v-model="ruleForm.nicheng" placeholder="昵称" clearable></el-input>
                                </el-form-item>
                                </el-col>
                                                                                                        <el-form-item v-if="flag=='users'" label="用户名" prop="username">
                    <el-input v-model="ruleForm.username"
                              placeholder="用户名"></el-input>
                </el-form-item>
                <el-col :span="24">
                    <el-form-item>
                        <el-button type="primary" @click="onUpdateHandler">修 改</el-button>
                        <el-button type="primary" @click="showPasswordDialog">修改密码</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>

        <!-- 修改密码弹窗 -->
        <el-dialog title="修改密码" v-model="passwordDialogVisible" width="450px" :close-on-click-modal="false">
            <el-form ref="passwordFormRef" :model="passwordForm" :rules="passwordRules" label-width="100px">
                <el-form-item label="原密码" prop="oldPassword">
                    <el-input v-model="passwordForm.oldPassword" type="password" placeholder="请输入原密码" show-password></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="newPassword">
                    <el-input v-model="passwordForm.newPassword" type="password" placeholder="请输入新密码" show-password></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="confirmPassword">
                    <el-input v-model="passwordForm.confirmPassword" type="password" placeholder="请再次输入新密码" show-password></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="passwordDialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="onSubmitPassword">确 定</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>


<style lang="scss" scoped>
    .avatar-uploader{
        border:1px solid #e4e6e9
    }

    .avatar-uploader .el-upload {
        border: 1px dashed var(--el-border-color);
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        transition: var(--el-transition-duration-fast);
    }

    .avatar-uploader .el-upload:hover {
        border-color: var(--el-color-primary);
    }

    .el-icon.avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        text-align: center;
    }
</style>

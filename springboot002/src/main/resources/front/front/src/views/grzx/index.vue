<script setup lang="ts">
    import { Session } from '@/utils/storage';
    import request from "@/utils/request";
    import { uploadImg } from '@/api/common/media';
    import base from "@/utils/base";
    import { defineAsyncComponent,reactive,ref,toRefs,nextTick } from 'vue';
    import {notify,confirm} from '@/utils/element';
    import {useRoute,useRouter} from 'vue-router'
    import {geren} from '@/utils/menu';
    const router = useRouter();
    const menus=geren.list();
    // console.log("个人菜单",menus)
    const grmenu=[];
    const grmenuesc=[];
 menus.forEach((item)=>{
          item.ykMenu.forEach((i)=>{
            // console.log("个人中心",i.child);
            i.child.forEach((a)=>{
                // console.log(a.tableName)
                grmenuesc.push(a.tableName);
            })
             grmenu.push(i.menu)
          });
     })
                            const state=reactive({
                gr:grmenu,
        gresc:grmenuesc,
                                                                                                                                            xingbieOptions:[],
                                                                            ruleForm: {

        },
        formInline: {},
        dataList: [],

        user:{},
                        
        
    })


    const {dataList,ordersList,ruleForm,gr,gresc,
                                                                                                                                                                    xingbieOptions,
                                                            
                formInline,
        
        

    } = {...toRefs(state)};




    init();

        
        
        
    function init(){
        let sessionTable = Session.get("tableName")
        request({
            url: sessionTable + '/session',
            method: "get"
        }).then((
                data
        ) => {
            if (data && data.code === 0) {

            state.user = data.data;
            state.ruleForm=data.data;

        } else {
            notify(data.msg,{type:'error'});
        }
    });
    }



        
    const formRef=ref();
    // 提交
    function onSubmit() {
        let sessionTable = Session.get("tableName")
        formRef.value?.validate((valid)=>{

            if(!valid) return;

        request({
            url:`${sessionTable}/${!state.ruleForm.id ? "save" : "update"}`,
            method:'post',
            data:state.ruleForm
        }).then((data)=>{
            notify("操作成功",{type:'success'});

        emit("refresh");
    })
    })

    }
        
        

        
                    
                    
                    
                                async function touxianghandleUploadImg(options: any){
                // console.log('handleUploadImg', options);
                try {
                    loadding.value = true;
                    const form = new FormData();
                    form.append('file', options.file);
                    form.append('data', JSON.stringify({sourceType: 'goods_img'}));
                    // 开始上传
                    const data = await uploadImg(form);

                    // 上传成功 , 将上传成功的图片地址赋值回显出来
                    const name=base.get().url;
                    ruleForm.value.touxiang = name+'upload/'+ data.file;
                } catch(e) {

                } finally {
                    loadding.value = false;
                }
            }

            
                    
                                                    state.xingbieOptions= "男,女".split(',')
                            
                    
                    
        
function handleClick(tab:any){
    console.log(tab.props.name)
    if(tab.props.name){
        router.push(`/${tab.props.name}list`)
    }
}

        
        </script>



<template>
  <div class="min-h-screen bg-gradient-to-b from-pink-50 via-purple-50 to-blue-50">
    <div class="container mx-auto px-4 py-8 max-w-7xl">



      <div class="">
    <div style="line-height: 50px;">
        <el-card class="box-card">
            <div slot="header" class="clearfix" style="text-align: center;">
                <span>个人中心</span>
            </div>

        </el-card>
        <div style="height: 30px;"></div>

        <!-- 用户基本信息展示卡片 -->
        <el-card class="user-info-card">
            <div class="user-info-container">
                <div class="user-avatar-section">
                    <img :src="ruleForm.touxiang || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" 
                         class="user-avatar-display" 
                         alt="用户头像" />
                </div>
                <div class="user-details-section">
                    <div class="user-info-row">
                        <div class="info-item">
                            <i class="el-icon-user"></i>
                            <span class="info-label">账号：</span>
                            <span class="info-value">{{ ruleForm.zhanghao || '未设置' }}</span>
                        </div>
                        <div class="info-item">
                            <i class="el-icon-user"></i>
                            <span class="info-label">昵称：</span>
                            <span class="info-value">{{ ruleForm.nicheng || '未设置' }}</span>
                        </div>
                    </div>
                    <div class="user-info-row">
                        <div class="info-item">
                            <i class="el-icon-male"></i>
                            <span class="info-label">性别：</span>
                            <span class="info-value">{{ ruleForm.xingbie || '未设置' }}</span>
                        </div>
                        <div class="info-item">
                            <i class="el-icon-phone"></i>
                            <span class="info-label">联系方式：</span>
                            <span class="info-value">{{ ruleForm.lianxifangshi || '未设置' }}</span>
                        </div>
                    </div>
                </div>
            </div>
        </el-card>
        
        <div style="height: 30px;"></div>

        <el-tabs  tab-position="activeName" type="border-card" style="height: 800px;" class="custom-tabs"  @tab-click="handleClick">
                
            

            
            


            

            

            
<el-tab-pane v-for="(item,index) in gr" :name="gresc[index]">
                <template #label>
                    <div class="custom-tab-label">
                        <i class="el-icon-s-grid"></i>
                        <span>{{ item }}</span>
                    </div>
                </template>
            </el-tab-pane>


            <el-tab-pane>
                <template #label>
                    <div class="custom-tab-label">
                        <i class="el-icon-user"></i>
                        <span>修改个人信息</span>
                    </div>
                </template>



                <div class="addEdit-block">
                    <el-form
                            ref="formRef" :model="ruleForm"
                            label-width="85px" label-right="right"
                            label-suffix=":" status-icon
                    >
                        <el-row>




                                                                                                                                                                                    


                                    <el-col :span="24">
                                        <el-form-item class="input" label="账号" prop="zhanghao"  :rules="{required: true, message: '账号为必填项！', trigger: 'blur'}" >
                                            <el-input v-model="ruleForm.zhanghao"
                                                      placeholder="账号" clearable></el-input>
                                        </el-form-item>
                                    </el-col>
                                                                                                                                <el-col :span="24">
                                        <el-form-item label="头像" prop="touxiang" :rules="{required: true, message: '为必填项', trigger: 'blur'}">

                                            <el-upload
                                                    class="avatar-uploader"
                                                    accept="image/png,image/jpg,image/jpeg"
                                                    action="#"
                                                    :show-file-list="false"
                                                    :http-request="touxianghandleUploadImg"
                                            >
                                                <img style="width: 100px;height: 100px;" v-if="ruleForm.touxiang" :src="ruleForm.touxiang" class="avatar" />
                                                <el-icon v-else class="avatar-uploader-icon"><ele-Plus /></el-icon>

                                            </el-upload>
                                        </el-form-item>
                                    </el-col>
                                                                                            


                                    <el-col :span="24">
                                        <el-form-item class="input" label="密码" prop="mima"  :rules="{required: true, message: '密码为必填项！', trigger: 'blur'}" >
                                            <el-input v-model="ruleForm.mima"
                                                      placeholder="密码" clearable></el-input>
                                        </el-form-item>
                                    </el-col>
                                                                                                                                <el-col :span="24">
                                        <el-form-item class="input" label="性别" prop="xingbie"  :rules="{required: true, message: '性别为必填项！', trigger: 'blur'}" >
                                            <el-select v-model="ruleForm.xingbie" placeholder="请选择性别">

                                                <el-option
                                                        v-for="(item,index) in xingbieOptions"
                                                        v-bind:key="index"
                                                        :label="item"
                                                        :value="item">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>

                                                                                            


                                    <el-col :span="24">
                                        <el-form-item class="input" label="联系方式" prop="lianxifangshi"  :rules="{required: true, message: '联系方式为必填项！', trigger: 'blur'}" >
                                            <el-input v-model="ruleForm.lianxifangshi"
                                                      placeholder="联系方式" clearable></el-input>
                                        </el-form-item>
                                    </el-col>
                                                                                            


                                    <el-col :span="24">
                                        <el-form-item class="input" label="昵称" prop="nicheng"  :rules="{required: true, message: '昵称为必填项！', trigger: 'blur'}" >
                                            <el-input v-model="ruleForm.nicheng"
                                                      placeholder="昵称" clearable></el-input>
                                        </el-form-item>
                                    </el-col>
                                                            

















                        </el-row>
                        <el-form-item style="text-align: right;">
                            <el-button type="danger" class="submit-btn-red" @click="onSubmit">修改个人信息</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-tab-pane>


        </el-tabs>
    </div>
      </div>
    </div>

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

    /* 个人中心标题卡片美化 */
    .box-card {
        background: linear-gradient(135deg, #ff6b9d 0%, #c06c84 50%, #6c5ce7 100%);
        border: none;
        box-shadow: 0 8px 24px rgba(255, 107, 157, 0.4);
        
        :deep(.el-card__header) {
            border-bottom: none;
            padding: 30px 20px;
        }
        
        span {
            font-size: 28px;
            font-weight: bold;
            color: white;
            letter-spacing: 2px;
        }
    }

    /* 用户信息展示卡片美化 */
    .user-info-card {
        background: linear-gradient(135deg, #ffeef8 0%, #e8d5f2 100%);
        border: none;
        box-shadow: 0 4px 16px rgba(255, 107, 157, 0.2);
        border-radius: 12px;
        overflow: hidden;
        
        :deep(.el-card__body) {
            padding: 0;
        }
    }

    .user-info-container {
        display: flex;
        align-items: center;
        padding: 30px;
        gap: 40px;
        background: white;
    }

    .user-avatar-section {
        flex-shrink: 0;
        
        .user-avatar-display {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            object-fit: cover;
            border: 4px solid #ff6b9d;
            box-shadow: 0 4px 12px rgba(255, 107, 157, 0.4);
            transition: all 0.3s ease;
            
            &:hover {
                transform: scale(1.05);
                box-shadow: 0 6px 16px rgba(255, 107, 157, 0.5);
            }
        }
    }

    .user-details-section {
        flex: 1;
        display: flex;
        flex-direction: column;
        gap: 20px;
    }

    .user-info-row {
        display: flex;
        gap: 40px;
        flex-wrap: wrap;
    }

    .info-item {
        display: flex;
        align-items: center;
        gap: 8px;
        min-width: 250px;
        padding: 12px 20px;
        background: linear-gradient(135deg, #fff0f6 0%, #f3e5f5 100%);
        border-radius: 8px;
        transition: all 0.3s ease;
        
        &:hover {
            background: linear-gradient(135deg, #ff6b9d 0%, #c06c84 50%, #6c5ce7 100%);
            transform: translateX(5px);
            box-shadow: 0 4px 12px rgba(255, 107, 157, 0.4);
            
            i, .info-label, .info-value {
                color: white;
            }
        }
        
        i {
            font-size: 20px;
            color: #ff6b9d;
            transition: color 0.3s ease;
        }
        
        .info-label {
            font-weight: 600;
            color: #495057;
            font-size: 15px;
            transition: color 0.3s ease;
        }
        
        .info-value {
            font-size: 15px;
            color: #212529;
            font-weight: 500;
            transition: color 0.3s ease;
        }
    }

    /* 标签页美化 */
    .custom-tabs {
        :deep(.el-tabs__header) {
            background: #fff0f6;
            border-radius: 8px;
            padding: 10px;
        }
        
        :deep(.el-tabs__item) {
            height: auto;
            padding: 20px 30px;
            font-size: 16px;
            font-weight: 500;
            border-radius: 8px;
            margin: 5px 0;
            transition: all 0.3s ease;
            
            &:hover {
                background: rgba(255, 107, 157, 0.1);
                transform: translateX(5px);
            }
            
            &.is-active {
                background: linear-gradient(135deg, #ff6b9d 0%, #c06c84 50%, #6c5ce7 100%);
                color: white !important;
                box-shadow: 0 4px 12px rgba(255, 107, 157, 0.5);
                transform: translateX(8px);
            }
        }
        
        :deep(.el-tabs__content) {
            padding: 30px;
        }
    }

    /* 自定义标签样式 */
    .custom-tab-label {
        display: flex;
        align-items: center;
        gap: 12px;
        font-size: 16px;
        
        i {
            font-size: 20px;
            transition: transform 0.3s ease;
        }
        
        &:hover i {
            transform: scale(1.2) rotate(5deg);
        }
    }

    /* 表单区域美化 */
    .addEdit-block {
        background: white;
        border-radius: 12px;
        padding: 30px;
        box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
        
        :deep(.el-form-item) {
            margin-bottom: 28px;
        }
        
        :deep(.el-form-item__label) {
            font-weight: 600;
            color: #333;
            font-size: 15px;
        }
        
        :deep(.el-input__inner) {
            border-radius: 8px;
            padding: 12px 15px;
            height: 45px;
            font-size: 14px;
            transition: all 0.3s ease;
            
            &:focus {
                border-color: #ff6b9d;
                box-shadow: 0 0 0 3px rgba(255, 107, 157, 0.15);
            }
        }
        
        :deep(.el-select) {
            width: 100%;
        }
        
        :deep(.el-button--primary) {
            background: linear-gradient(135deg, #ff6b9d 0%, #c06c84 50%, #6c5ce7 100%);
            border: none;
            padding: 14px 40px;
            font-size: 16px;
            font-weight: 600;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(255, 107, 157, 0.4);
            transition: all 0.3s ease;
            
            &:hover {
                transform: translateY(-2px);
                box-shadow: 0 6px 16px rgba(255, 107, 157, 0.5);
            }
            
            &:active {
                transform: translateY(0);
            }
        }
        
        /* 红色修改按钮特殊样式 */
        :deep(.el-button--danger.submit-btn-red) {
            background: linear-gradient(135deg, #ff6b9d 0%, #ff4b6b 50%, #ff416c 100%);
            border: none;
            padding: 16px 50px;
            font-size: 18px;
            font-weight: 700;
            border-radius: 10px;
            box-shadow: 0 6px 20px rgba(255, 107, 157, 0.5);
            transition: all 0.3s ease;
            letter-spacing: 1px;
            
            &:hover {
                transform: translateY(-3px) scale(1.05);
                box-shadow: 0 8px 25px rgba(255, 107, 157, 0.6);
                background: linear-gradient(135deg, #ff416c 0%, #ff4b6b 50%, #ff6b9d 100%);
            }
            
            &:active {
                transform: translateY(-1px) scale(1.02);
            }
        }
    }

    /* 头像上传区域美化 */
    :deep(.avatar-uploader) {
        border: 2px dashed #ffb3d9;
        border-radius: 12px;
        overflow: hidden;
        transition: all 0.3s ease;
        
        &:hover {
            border-color: #ff6b9d;
            box-shadow: 0 0 0 4px rgba(255, 107, 157, 0.15);
        }
        
        .el-upload {
            border: none;
            border-radius: 12px;
        }
        
        .avatar {
            border-radius: 12px;
        }
    }
</style>

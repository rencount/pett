<script setup lang="ts">
    import { defineAsyncComponent,reactive,ref,toRefs } from 'vue';
    import base from "@/utils/base";
    import { uploadImg } from '@/api/common/media';
    import request from "@/utils/request";
    import {notify} from '@/utils/element'
    import { Session } from '@/utils/storage';
    const Editor=defineAsyncComponent(()=>import('@/components/editor/index.vue'));
                                                                                                                                                                            const formRef=ref();
    const state=reactive({
        loadding:false,//加载框
        visible:false,//隐藏显示
        roisfalg:{
                                                                            zhanghao: false,
                                                touxiang: false,
                                                mima: false,
                                                xingbie: false,
                                                lianxifangshi: false,
                                                nicheng: false,
                               },
        formData:{} as any,

                                                                                                                                            xingbieOptions: [],
                                                                                                                                                                                                                                            title:'新增',
        type:'add',
        id:''
    })
    const initData={

    }
    const {
        loadding,
        visible,
        formData,
        title,
        type,
        id,
        roisfalg
                                                                                                                                        , xingbieOptions
                                                                                                                                                                                                                                        } = {...toRefs(state)};


    function open(title:string,id:any,type:string,formData?:any)
    {

        state.title = title;
        state.type = type;
        state.id = id;
        state.formData = {...formData,...initData }
        ;
        state.visible = true;
        if (state.type == 'cross') {
            var obj = Session.get('crossObj');
            for (var o in obj) {
                                                                                                                                    if (o == 'zhanghao') {
                            state.formData.zhanghao = obj[o];
                            state.roisfalg.zhanghao = true;
                            continue;
                        }
                                                                                if (o == 'touxiang') {
                            state.formData.touxiang = obj[o];
                            state.roisfalg.touxiang = true;
                            continue;
                        }
                                                                                if (o == 'mima') {
                            state.formData.mima = obj[o];
                            state.roisfalg.mima = true;
                            continue;
                        }
                                                                                if (o == 'xingbie') {
                            state.formData.xingbie = obj[o];
                            state.roisfalg.xingbie = true;
                            continue;
                        }
                                                                                if (o == 'lianxifangshi') {
                            state.formData.lianxifangshi = obj[o];
                            state.roisfalg.lianxifangshi = true;
                            continue;
                        }
                                                                                if (o == 'nicheng') {
                            state.formData.nicheng = obj[o];
                            state.roisfalg.nicheng = true;
                            continue;
                        }
                                                }


        }
    }

    const emit = defineEmits(['refresh']);

    const close=()=>{
        state.visible=false;
    }

    function submitForm(){
        formRef.value?.validate((valid:boolean)=>{

            if(!valid) return;
            state.loadding=true;
            request({
                url:`yonghu/${!state.formData.id ? "save" : "update"}`,
                method:'post',
                data:state.formData
            }).then(({data})=>{
                notify("操作成功",{type:'success'});
            state.loadding=false;
            close();
            emit("refresh");
        })



        })

    }

    defineExpose({
        open
    })


                    


                    


                    


                                async function handleUploadImg(options: any){
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
                    formData.value.touxiang = name+'upload/'+ data.file;
                } catch(e) {

                } finally {
                    loadding.value = false;
                }
            }
            


                    


                                                    state.xingbieOptions= "男,女".split(',')
                
            


                    


                    


        
</script>

<template>
    <el-dialog :title="`${title}`" v-model="visible"
               center draggable :before-close="close" width="1000px"
               :close-on-click-modal="false" destroy-on-close>
        <div v-loading="loadding">
            <el-form ref="formRef" :model="formData"
                     label-width="85px" label-right="right"
                     label-suffix=":" status-icon
            >
                                                                                                            
                        <el-form-item label="账号" prop="zhanghao" :rules="{required: true, message: '账号为必填项！', trigger: 'blur'}">
                            <el-input v-model="formData.zhanghao" maxlength="30" clearable show-word-limit placeholder="请输入账号" :readonly="roisfalg.zhanghao"/>
                        </el-form-item>
                                                                                                                        

                                                                                <el-form-item label="头像" prop="touxiang" :rules="{required: true, message: '为必填项', trigger: 'blur'}">

                            <el-upload
                                    class="avatar-uploader"
                                    accept="image/png,image/jpg,image/jpeg"
                                    action="#"
                                    :show-file-list="false"
                                    :http-request="handleUploadImg"
                            >
                                <img style="width: 100px;height: 100px;" v-if="formData.touxiang" :src="formData.touxiang" class="avatar" />
                                <el-icon v-else class="avatar-uploader-icon"><ele-Plus /></el-icon>

                            </el-upload>
                        </el-form-item>


                                                        
                        <el-form-item label="密码" prop="mima" :rules="{required: true, message: '密码为必填项！', trigger: 'blur'}">
                            <el-input v-model="formData.mima" maxlength="30" clearable show-word-limit placeholder="请输入密码" :readonly="roisfalg.mima"/>
                        </el-form-item>
                                                                                                                        

                                                                                <el-form-item label="性别" prop="xingbie" :rules="{required: true, message: '性别为必选项！', trigger: 'blur'}">
                            <el-select
                                    v-model="formData.xingbie"
                                    clearable
                                    placeholder="请选择性别"
                                    style="width: 240px"
                            >
                                <el-option
                                        v-for="(item,index) in xingbieOptions"
                                        v-bind:key="index"
                                        :label="item"
                                        :value="item"
                                />
                            </el-select>
                        </el-form-item>

                                                        
                        <el-form-item label="联系方式" prop="lianxifangshi" :rules="{required: true, message: '联系方式为必填项！', trigger: 'blur'}">
                            <el-input v-model="formData.lianxifangshi" maxlength="30" clearable show-word-limit placeholder="请输入联系方式" :readonly="roisfalg.lianxifangshi"/>
                        </el-form-item>
                                                                                                                        

                                                        
                        <el-form-item label="昵称" prop="nicheng" :rules="{required: true, message: '昵称为必填项！', trigger: 'blur'}">
                            <el-input v-model="formData.nicheng" maxlength="30" clearable show-word-limit placeholder="请输入昵称" :readonly="roisfalg.nicheng"/>
                        </el-form-item>
                                                                                                                        

                                    


                
            </el-form>
            <el-row justify="center" class="mt10">
                <el-button @click="close">取 消</el-button>
                <el-button @click="submitForm" type="primary">保 存</el-button>
            </el-row>
        </div>
    </el-dialog>

            
            
            
            
            
            
            
            
    

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

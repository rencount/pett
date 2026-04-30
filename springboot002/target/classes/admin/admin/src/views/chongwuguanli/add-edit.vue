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
        formData:{} as any,
      roisfalg:{
                                                                                              chongwumingcheng: false,
                                                            tupian: false,
                                                            xingbie: false,
                                                            xihao: false,
                                                            xiangqing: false,
                                                            userid: false,
                                                            clicktime: false,
                                                            clicknum: false,
                                                            thumbsupnum: false,
                                                            crazilynum: false,
                              },
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        title:'新增',
        type:'add'
    })
    const initData={

    }
    const {
        loadding,
        visible,
        formData,
        title,
        type,
      roisfalg
                                                                                                                                                                                                                                                        
                                                                                                                                                                                                                                                            } = {...toRefs(state)};


    function open(title:string,type:string,formData?:any){

        state.title=title;
        state.type=type;
        state.formData={...formData,...initData};
        state.visible=true;


      if (state.type == 'cross') {
        var obj = Session.get('crossObj');
        for (var o in obj) {
                                                                                                      if (o == 'chongwumingcheng') {
                    state.formData.chongwumingcheng = obj[o];
                    state.roisfalg.chongwumingcheng = true;
                    continue;
                  }
                                                              if (o == 'tupian') {
                    state.formData.tupian = obj[o];
                    state.roisfalg.tupian = true;
                    continue;
                  }
                                                              if (o == 'xingbie') {
                    state.formData.xingbie = obj[o];
                    state.roisfalg.xingbie = true;
                    continue;
                  }
                                                              if (o == 'xihao') {
                    state.formData.xihao = obj[o];
                    state.roisfalg.xihao = true;
                    continue;
                  }
                                                              if (o == 'xiangqing') {
                    state.formData.xiangqing = obj[o];
                    state.roisfalg.xiangqing = true;
                    continue;
                  }
                                                              if (o == 'userid') {
                    state.formData.userid = obj[o];
                    state.roisfalg.userid = true;
                    continue;
                  }
                                                              if (o == 'clicktime') {
                    state.formData.clicktime = obj[o];
                    state.roisfalg.clicktime = true;
                    continue;
                  }
                                                              if (o == 'clicknum') {
                    state.formData.clicknum = obj[o];
                    state.roisfalg.clicknum = true;
                    continue;
                  }
                                                              if (o == 'thumbsupnum') {
                    state.formData.thumbsupnum = obj[o];
                    state.roisfalg.thumbsupnum = true;
                    continue;
                  }
                                                              if (o == 'crazilynum') {
                    state.formData.crazilynum = obj[o];
                    state.roisfalg.crazilynum = true;
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
          if (state.type == 'cross') {
            request({
              url:`chongwuguanli/save`,
              method:'post',
              data:state.formData
            }).then(({data})=>{
              notify("操作成功",{type:'success'});
              state.loadding=false;
              close();
              emit("refresh");
            })
          }else{
            request({
              url:`chongwuguanli/${!state.formData.id ? "save" : "update"}`,
              method:'post',
              data:state.formData
            }).then(({data})=>{
              notify("操作成功",{type:'success'});
              state.loadding=false;
              close();
              emit("refresh");
            })
          }




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
                    formData.value.tupian = name+'upload/'+ data.file;
                } catch(e) {

                } finally {
                    loadding.value = false;
                }
            }
            


                    


                    


                    


                    


                    


                    


                    


                    


        


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
                                                                                                            
                        <el-form-item label="宠物名称" prop="chongwumingcheng" :rules="{required: true, message: '宠物名称为必填项！', trigger: 'blur'}">
                            <el-input v-model="formData.chongwumingcheng" maxlength="30" clearable show-word-limit placeholder="请输入宠物名称" :readonly="roisfalg.chongwumingcheng"/>
                        </el-form-item>
                                                                                                                        

                                                                                <el-form-item label="图片" prop="tupian" :rules="{required: true, message: '为必填项', trigger: 'blur'}">

                            <el-upload
                                    class="avatar-uploader"
                                    accept="image/png,image/jpg,image/jpeg"
                                    action="#"
                                    :show-file-list="false"
                                    :http-request="handleUploadImg"
                            >
                                <img style="width: 100px;height: 100px;" v-if="formData.tupian" :src="formData.tupian" class="avatar" />
                                <el-icon v-else class="avatar-uploader-icon"><ele-Plus /></el-icon>

                            </el-upload>
                        </el-form-item>


                                                        
                        <el-form-item label="性别" prop="xingbie" :rules="{required: true, message: '性别为必填项！', trigger: 'blur'}">
                            <el-input v-model="formData.xingbie" maxlength="30" clearable show-word-limit placeholder="请输入性别" :readonly="roisfalg.xingbie"/>
                        </el-form-item>
                                                                                                                        

                                                        
                        <el-form-item label="喜好" prop="xihao" :rules="{required: true, message: '喜好为必填项！', trigger: 'blur'}">
                            <el-input v-model="formData.xihao" maxlength="30" clearable show-word-limit placeholder="请输入喜好" :readonly="roisfalg.xihao"/>
                        </el-form-item>
                                                                                                                        

                                                        
                        <el-form-item label="详情" prop="xiangqing" :rules="{required: true, message: '详情为必填项！', trigger: 'blur'}">
                            <el-input v-model="formData.xiangqing" maxlength="30" clearable show-word-limit placeholder="请输入详情" :readonly="roisfalg.xiangqing"/>
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

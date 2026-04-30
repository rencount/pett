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
                                                            bingqingmiaoshu: false,
                                                            tupianshangchuan: false,
                                                            buchong: false,
                                                            fabushijian: false,
                                                            userid: false,
                                                            sfsh: false,
                                                            shhf: false,
                              },
                                                                                chongwumingchengOptions: [],
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
                                                                            , chongwumingchengOptions
                                                                                                                                                                
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
                                                              if (o == 'bingqingmiaoshu') {
                    state.formData.bingqingmiaoshu = obj[o];
                    state.roisfalg.bingqingmiaoshu = true;
                    continue;
                  }
                                                              if (o == 'tupianshangchuan') {
                    state.formData.tupianshangchuan = obj[o];
                    state.roisfalg.tupianshangchuan = true;
                    continue;
                  }
                                                              if (o == 'buchong') {
                    state.formData.buchong = obj[o];
                    state.roisfalg.buchong = true;
                    continue;
                  }
                                                              if (o == 'fabushijian') {
                    state.formData.fabushijian = obj[o];
                    state.roisfalg.fabushijian = true;
                    continue;
                  }
                                                              if (o == 'userid') {
                    state.formData.userid = obj[o];
                    state.roisfalg.userid = true;
                    continue;
                  }
                                                              if (o == 'sfsh') {
                    state.formData.sfsh = obj[o];
                    state.roisfalg.sfsh = true;
                    continue;
                  }
                                                              if (o == 'shhf') {
                    state.formData.shhf = obj[o];
                    state.roisfalg.shhf = true;
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
              url:`jibingshangchuan/save`,
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
              url:`jibingshangchuan/${!state.formData.id ? "save" : "update"}`,
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


                    


                    


                                                        
                    request({
                        url: `option/chongwuguanli/chongwumingcheng`,
                        method: "get"
                    }).then((data) => {
                        if (data && data.code === 0) {
                        state.chongwumingchengOptions = data.data;
                    } else {
                        notify(data.msg,{type:'error'});
                    }
                    });
                                                        



    


                    


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
                    formData.value.tupianshangchuan = name+'upload/'+ data.file;
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
                                                                                                                                    <el-form-item label="宠物名称" prop="chongwumingcheng" :rules="{required: true, message: '宠物名称为必选项！', trigger: 'blur'}">
                            <el-select
                                    v-model="formData.chongwumingcheng"
                                    clearable
                                    placeholder="请选择宠物名称"
                                    style="width: 240px"
                            >
                                <el-option
                                        v-for="(item,index) in chongwumingchengOptions"
                                        v-bind:key="index"
                                        :label="item"
                                        :value="item"
                                />
                            </el-select>
                        </el-form-item>

                                                                                <el-form-item label="病情描述" prop="bingqingmiaoshu">
                            <el-input v-model="formData.bingqingmiaoshu" maxlength="100" clearable show-word-limit type="textarea" />
                        </el-form-item>


                                                                                <el-form-item label="图片上传" prop="tupianshangchuan" :rules="{required: true, message: '为必填项', trigger: 'blur'}">

                            <el-upload
                                    class="avatar-uploader"
                                    accept="image/png,image/jpg,image/jpeg"
                                    action="#"
                                    :show-file-list="false"
                                    :http-request="handleUploadImg"
                            >
                                <img style="width: 100px;height: 100px;" v-if="formData.tupianshangchuan" :src="formData.tupianshangchuan" class="avatar" />
                                <el-icon v-else class="avatar-uploader-icon"><ele-Plus /></el-icon>

                            </el-upload>
                        </el-form-item>


                                                                                <el-form-item label="补充" prop="buchong">
                            <el-input v-model="formData.buchong" maxlength="100" clearable show-word-limit type="textarea" />
                        </el-form-item>


                                                                                <el-form-item label="发布时间" prop="fabushijian"  :rules="{required: true, message: '发布时间为必填项', trigger: 'blur'}">

                            <el-date-picker
                                    v-model="formData.fabushijian"
                                    type="date"
                                    format="YYYY/MM/DD"
                                    value-format="YYYY-MM-DD"
                                    placeholder="发布时间"
                            />


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

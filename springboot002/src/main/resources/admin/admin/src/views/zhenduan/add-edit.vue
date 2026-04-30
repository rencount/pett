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
                                                            zhengzhuang: false,
                                                            zhiliaojianyi: false,
                                                            zhenduanshijian: false,
                                                            zhenduanren: false,
                                                            lianxifangshi: false,
                                                            userid: false,
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
                                                              if (o == 'zhengzhuang') {
                    state.formData.zhengzhuang = obj[o];
                    state.roisfalg.zhengzhuang = true;
                    continue;
                  }
                                                              if (o == 'zhiliaojianyi') {
                    state.formData.zhiliaojianyi = obj[o];
                    state.roisfalg.zhiliaojianyi = true;
                    continue;
                  }
                                                              if (o == 'zhenduanshijian') {
                    state.formData.zhenduanshijian = obj[o];
                    state.roisfalg.zhenduanshijian = true;
                    continue;
                  }
                                                              if (o == 'zhenduanren') {
                    state.formData.zhenduanren = obj[o];
                    state.roisfalg.zhenduanren = true;
                    continue;
                  }
                                                              if (o == 'lianxifangshi') {
                    state.formData.lianxifangshi = obj[o];
                    state.roisfalg.lianxifangshi = true;
                    continue;
                  }
                                                              if (o == 'userid') {
                    state.formData.userid = obj[o];
                    state.roisfalg.userid = true;
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
              url:`zhenduan/save`,
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
              url:`zhenduan/${!state.formData.id ? "save" : "update"}`,
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
                                                                                                                        

                                                                                <el-form-item label="症状" prop="zhengzhuang">
                            <el-input v-model="formData.zhengzhuang" maxlength="100" clearable show-word-limit type="textarea" />
                        </el-form-item>


                                                                                <el-form-item label="治疗建议" prop="zhiliaojianyi">
                            <el-input v-model="formData.zhiliaojianyi" maxlength="100" clearable show-word-limit type="textarea" />
                        </el-form-item>


                                                                                <el-form-item label="诊断时间" prop="zhenduanshijian"  :rules="{required: true, message: '诊断时间为必填项', trigger: 'blur'}">

                            <el-date-picker
                                    v-model="formData.zhenduanshijian"
                                    type="date"
                                    format="YYYY/MM/DD"
                                    value-format="YYYY-MM-DD"
                                    placeholder="诊断时间"
                            />


                        </el-form-item>
                                                        
                        <el-form-item label="诊断人" prop="zhenduanren" :rules="{required: true, message: '诊断人为必填项！', trigger: 'blur'}">
                            <el-input v-model="formData.zhenduanren" maxlength="30" clearable show-word-limit placeholder="请输入诊断人" :readonly="roisfalg.zhenduanren"/>
                        </el-form-item>
                                                                                                                        

                                                        
                        <el-form-item label="联系方式" prop="lianxifangshi" :rules="{required: true, message: '联系方式为必填项！', trigger: 'blur'}">
                            <el-input v-model="formData.lianxifangshi" maxlength="30" clearable show-word-limit placeholder="请输入联系方式" :readonly="roisfalg.lianxifangshi"/>
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

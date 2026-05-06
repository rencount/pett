<script setup lang="ts">
  import { defineAsyncComponent,reactive,ref,toRefs,onMounted,computed } from 'vue';
  import { toRaw } from "@vue/reactivity";
  import { Key } from '@/stores/auth';
  import { Session } from '@/utils/storage';
  import request from "@/utils/request";
  import {notify,confirm} from '@/utils/element';
  import { isAuth } from '@/utils/utils';
  import {exportExcel} from '@/utils/exportExcel';
  import printJS from 'print-js'

  const AddEdit=defineAsyncComponent(()=>import('@/views/jibingshangchuan/add-edit.vue'));
        const state=reactive({
          query:{
                                                                                chongwumingcheng:'',
                                                    bingqingmiaoshu:'',
                                                                                            fabushijian:'',
                                                                                
    },
    page:{
      current:1,
      size:10,
      total:0
    },
          tableList:[],
    ids:[],
    isdel:true,
              sfshVisiable:false,
        formData:{},
      
        })
  const { query,tableList,page,isdel
                          ,sfshVisiable,
        formData
              } = {...toRefs(state)};
  
  // 获取当前登录用户角色
  const currentRole = Session.get('role');


      


  //进入执行
  getDateList();
  //获取列表
  function getDateList(){
    const params={
      page: state.page.current,
      limit: state.page.size,
                            }
    
    // 如果是宠物医生角色，只查看审核通过的信息
    if (currentRole === '宠物医生') {
      params.sfsh = '是';
    }


                                                            if (state.query.chongwumingcheng != '' && state.query.chongwumingcheng != undefined) {
              params['chongwumingcheng'] = '%' + state.query.chongwumingcheng + '%'
            }
                                      if (state.query.bingqingmiaoshu != '' && state.query.bingqingmiaoshu != undefined) {
              params['bingqingmiaoshu'] = '%' + state.query.bingqingmiaoshu + '%'
            }
                                                                      if (state.query.fabushijian != '' && state.query.fabushijian != undefined) {
              params['fabushijian'] = '%' + state.query.fabushijian + '%'
            }
                                                                    request({
      url:'jibingshangchuan/page',
      method:'get',
      params
    }).then(({data})=>{

      state.page.total=data.total;
      state.tableList=data.list;
            })
  }
  //查询
  function handleQuery(){
    getDateList();
  }

  function download(file) {
    window.open(`${file}`)
  }

  //删除

  function handleDelete(row:any){

    request({
      url:`jibingshangchuan/delete`,
      method:'post',
      data:[row.id]
    }).then(({data})=>{
      notify("删除成功",{type:'success'});
      getDateList();

    })
  }

  const multipleSelection = ref<[]>([]);

  function handleSelectionChange(val:any){
    multipleSelection.value=val;
    const list=toRaw(multipleSelection.value)
    list.forEach((item)=>{
      state.ids.push(item.id);
    })

    if(list.length==0){
      state.ids=[];
      state.isdel=true
    }else{
      state.isdel=false
    }

  }


  function listdel(){
    const ids=toRaw(state.ids)
    request({
      url:`jibingshangchuan/delete`,
      method:'post',
      data:ids
    }).then(({data})=>{
      notify("删除成功",{type:'success'});
      getDateList();

    })
  }


  const users=Session.get(Key.userInfoKey);

  const editRef=ref();


      


  function add(){
    editRef.value.open('新增','add');
  }
  function handleEdit(row:any){
    editRef.value.open('修改','up',row);
  }

      
      function shDialog(row:any){
        state.sfshVisiable=true;
        state.formData=row;
        // 如果没有回复内容，初始化为空字符串
        if (!state.formData.shhf) {
          state.formData.shhf = '';
        }
      }

      function shHandler(){
        const message = currentRole === '宠物医生' ? "确认要提交回复吗？" : "确认要审核吗？";
        confirm(message).then(()=>{

          request({
            url:'jibingshangchuan/update',
            method:'post',
            data:state.formData
          }).then((data)=>{
            state.sfshVisiable=false;
            const successMsg = currentRole === '宠物医生' ? "回复成功" : "操作成功";
            notify(successMsg,{type:'success'});
            getDateList();
          })

        })
      }

      

      


      
      
</script>
<template>
  <div class="list-page-container">
    <el-card shadow="hover">
      <div class="header-section">
        <div class="header-title">
                    <h3>问诊记录</h3>
                  </div>
        <div class="action-buttons">
            <el-button v-if="isAuth('jibingshangchuan','新增')" icon="ele-Plus" type="primary" @click="add()">新增</el-button>
            <el-button v-if="isAuth('jibingshangchuan','删除')" icon="ele-Delete" @click="listdel()" :disabled="isdel" type="danger" plain>批量删除</el-button>
                                                </div>
      </div>
       <div class="query-section">
         <el-form :model="query" inline>
                                                                                                        <el-form-item label="宠物名称">
                        <el-input v-model="query.chongwumingcheng" maxlength="100" placeholder="请输入宠物名称" clearable/>
                    </el-form-item>
                                                                <el-form-item label="病情描述">
                        <el-input v-model="query.bingqingmiaoshu" maxlength="100" placeholder="请输入病情描述" clearable/>
                    </el-form-item>
                                                                                                                        <el-form-item label="发布时间">
                        <el-input v-model="query.fabushijian" maxlength="100" placeholder="请输入发布时间" clearable/>
                    </el-form-item>
                                                                                                                            <el-form-item>
                <el-button icon="ele-Search" type="primary" @click="handleQuery()">查询</el-button>
            </el-form-item>
         </el-form>
       </div>

      <el-table
          ref="tableListRef"
          :data="tableList"
          class="data-table"
          style="width: 100%"
          border
          stripe
          highlight-current-row
          @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
                                                                                                                                          <el-table-column header-align="center"  align="center" prop="chongwumingcheng" label="宠物名称" width="120"/>
                                                                                                                                          <el-table-column header-align="center"  align="center" prop="bingqingmiaoshu" label="病情描述" min-width="200" show-overflow-tooltip/>
                                                                                                                                                                              <el-table-column header-align="center"  align="center" prop="tupianshangchuan" label="图片上传">
                          <template #default="{row}">
                              <el-image v-if="row.tupianshangchuan" :src="row.tupianshangchuan" style="width: 80px; height: 80px; border-radius: 4px;" fit="cover" :preview-src-list="[row.tupianshangchuan]" preview-teleported/>
                              <span v-else>无图片</span>
                          </template>
                      </el-table-column>
                                                                                                                                                                                  <el-table-column header-align="center"  align="center" prop="fabushijian" label="发布时间"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="userid" label="用户id"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="sfsh" label="审核状态">
                              <template #default="{row}">
                                  <el-tag :type="row.sfsh === '是' ? 'success' : 'danger'" disable-transitions>{{row.sfsh=='是'?'通过':'未通过'}}</el-tag>
                              </template>
                          </el-table-column>
                          <!-- 管理员显示审核按钮 -->
                          <el-table-column v-if="currentRole !== '宠物医生' && isAuth('jibingshangchuan','审核')" header-align="center"  align="center" label="审核操作">
                              <template #default="{row}">
                                  <el-button type="primary" link icon="ele-Edit" size="small" @click="shDialog(row)">审核
                                  </el-button>
                              </template>
                          </el-table-column>
                          <!-- 宠物医生显示回复按钮 -->
                          <el-table-column v-if="currentRole === '宠物医生'" header-align="center"  align="center" label="回复操作">
                              <template #default="{row}">
                                  <el-button type="primary" link icon="ele-ChatDotRound" size="small" @click="shDialog(row)">回复
                                  </el-button>
                              </template>
                          </el-table-column>
                          <!-- 审核回复列 -->
                          <el-table-column header-align="center"  align="center" prop="shhf" label="审核回复" min-width="150" show-overflow-tooltip/>
                                                                          <el-table-column v-if="isAuth('jibingshangchuan','修改') || isAuth('jibingshangchuan','删除')" fixed="right" align="center" label="操作" width="250">
              <template #default="{row}">
                                                      <el-button v-if="isAuth('jibingshangchuan','修改')" icon="ele-Edit" @click.stop="handleEdit(row)" type="primary" link>修改</el-button>
                  <el-popconfirm  v-if="isAuth('jibingshangchuan','删除')"  width="auto" @confirm="handleDelete(row)"  :title="`确定要删除这条记录吗？`">
                      <template #reference>
                          <el-button icon="ele-Delete" type="danger" link>删除</el-button>
                      </template>
                  </el-popconfirm>
              </template>
          </el-table-column>
      </el-table>
      <!-- 分页 -->
      <div class="pagination-container">
        <m-page :page="page" @pageChange="getDateList"/>
      </div>
      <AddEdit ref="editRef" @refresh="getDateList"/>
    </el-card>
  </div>
      <!-- 管理员审核对话框 -->
      <el-dialog
                v-if="currentRole !== '宠物医生'"
                title="审核"
                v-model="sfshVisiable"
                width="400px">
            <el-form ref="form" :model="formData" label-width="100px">
                <el-form-item label="审核状态">
                    <el-select v-model="formData.sfsh" placeholder="请选择审核状态" style="width: 100%;">
                        <el-option label="通过" value="是"></el-option>
                        <el-option label="不通过" value="否"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="sfshVisiable = false">取消</el-button>
                    <el-button type="primary" @click="shHandler">确定</el-button>
                </span>
            </template>
        </el-dialog>
        
        <!-- 宠物医生回复对话框 -->
        <el-dialog
                v-if="currentRole === '宠物医生'"
                title="回复疾病信息"
                v-model="sfshVisiable"
                width="600px">
            <el-form ref="form" :model="formData" label-width="100px">
                <el-form-item label="宠物名称">
                    <el-input v-model="formData.chongwumingcheng" disabled></el-input>
                </el-form-item>
                <el-form-item label="病情描述">
                    <el-input type="textarea" :rows="3" v-model="formData.bingqingmiaoshu" disabled></el-input>
                </el-form-item>
                <el-form-item label="回复内容">
                    <el-input type="textarea" :rows="6" v-model="formData.shhf" placeholder="请输入您的专业回复..."></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="sfshVisiable = false">取消</el-button>
                    <el-button type="primary" @click="shHandler">提交回复</el-button>
                </span>
            </template>
        </el-dialog>

    



</template>

<style scoped>
.list-page-container {
  padding: 20px;
  background-color: #f0f2f5;
}

.header-section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.header-title h3 {
  margin: 0;
  font-size: 22px;
  color: #333;
}

.query-section {
  background-color: #fafafa;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 20px;
}

.data-table {
  border-radius: 8px;
  overflow: hidden;
}

:deep(.el-table .el-table__header-wrapper th) {
  background-color: #f5f7fa;
  color: #606266;
  font-weight: bold;
}

:deep(.el-table .el-button--text) {
    padding: 0;
}

.pagination-container {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}

</style>

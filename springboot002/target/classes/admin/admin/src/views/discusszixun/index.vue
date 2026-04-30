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

  const AddEdit=defineAsyncComponent(()=>import('@/views/discusszixun/add-edit.vue'));
        const state=reactive({
          query:{
                                                                                                                                                    
    },
    page:{
      current:1,
      size:10,
      total:0
    },
          tableList:[],
    ids:[],
    isdel:true,
      
        })
  const { query,tableList,page,isdel
                          } = {...toRefs(state)};


      


  //进入执行
  getDateList();
  //获取列表
  function getDateList(){
    const params={
      page: state.page.current,
      limit: state.page.size,
                            }


                                                                                                                          request({
      url:'discusszixun/page',
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
      url:`discusszixun/delete`,
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
      url:`discusszixun/delete`,
      method:'post',
      data:ids
    }).then(({data})=>{
      notify("删除成功",{type:'success'});
      getDateList();

    })
  }


  const users=Session.get(Key.userInfoKey);

  const editRef=ref();


      

//新增
  function add(){
    editRef.value.open('新增','add');
  }
  //修改
  function handleEdit(row:any){
    editRef.value.open('修改','up',row);
  }
  //回复
  function handleReply(row:any){
    editRef.value.open('回复评论','up',row);
  }

      

      


      
      
</script>
<template>
  <div class="list-page-container">
    <el-card shadow="hover">
      <div class="header-section">
        <div class="header-title">
                    <h3>咨询评论</h3>
                  </div>
        <div class="action-buttons">
            <el-button v-if="isAuth('discusszixun','新增')" icon="ele-Plus" type="primary" @click="add()">新增</el-button>
            <el-button v-if="isAuth('discusszixun','删除')" icon="ele-Delete" @click="listdel()" :disabled="isdel" type="danger" plain>批量删除</el-button>
                                                </div>
      </div>
       <div class="query-section">
         <el-form :model="query" inline>
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
                                                                                                                                          <el-table-column header-align="center"  align="center" prop="refid" label="关联表id"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="userid" label="用户id"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="nickname" label="用户名"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="content" label="评论内容"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="reply" label="回复内容"/>
                                                                          <el-table-column fixed="right" align="center" label="操作" width="250">
              <template #default="{row}">
                                                      <el-button v-if="isAuth('discusszixun','修改')" icon="ele-ChatDotSquare" @click.stop="handleReply(row)" type="success" link>回复</el-button>
                                                      <el-button v-if="isAuth('discusszixun','修改')" icon="ele-Edit" @click.stop="handleEdit(row)" type="primary" link>修改</el-button>
                  <el-popconfirm  v-if="isAuth('discusszixun','删除')"  width="auto" @confirm="handleDelete(row)"  :title="`确定要删除这条记录吗？`">
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

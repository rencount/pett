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

  const AddEdit=defineAsyncComponent(()=>import('@/views/chongwuguanli/add-edit.vue'));
        const state=reactive({
          query:{
                                                                                chongwumingcheng:'',
                                                                        xingbie:'',
                                                    xihao:'',
                                                                                                                                            
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


                                                            if (state.query.chongwumingcheng != '' && state.query.chongwumingcheng != undefined) {
              params['chongwumingcheng'] = '%' + state.query.chongwumingcheng + '%'
            }
                                                      if (state.query.xingbie != '' && state.query.xingbie != undefined) {
              params['xingbie'] = '%' + state.query.xingbie + '%'
            }
                                      if (state.query.xihao != '' && state.query.xihao != undefined) {
              params['xihao'] = '%' + state.query.xihao + '%'
            }
                                                                                                                    request({
      url:'chongwuguanli/page',
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
      url:`chongwuguanli/delete`,
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
      url:`chongwuguanli/delete`,
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

      

      


      
      
</script>
<template>
  <div class="list-page-container">
    <el-card shadow="hover">
      <div class="header-section">
        <div class="header-title">
                    <h3>宠物管理</h3>
                  </div>
        <div class="action-buttons">
            <el-button v-if="isAuth('chongwuguanli','新增')" icon="ele-Plus" type="primary" @click="add()">新增</el-button>
            <el-button v-if="isAuth('chongwuguanli','删除')" icon="ele-Delete" @click="listdel()" :disabled="isdel" type="danger" plain>批量删除</el-button>
                                                </div>
      </div>
       <div class="query-section">
         <el-form :model="query" inline>
                                                                                                        <el-form-item label="宠物名称">
                        <el-input v-model="query.chongwumingcheng" maxlength="100" placeholder="请输入宠物名称" clearable/>
                    </el-form-item>
                                                                                            <el-form-item label="性别">
                        <el-input v-model="query.xingbie" maxlength="100" placeholder="请输入性别" clearable/>
                    </el-form-item>
                                                                <el-form-item label="喜好">
                        <el-input v-model="query.xihao" maxlength="100" placeholder="请输入喜好" clearable/>
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
                                                                                                                                          <el-table-column header-align="center"  align="center" prop="chongwumingcheng" label="宠物名称"/>
                                                                                                                      <el-table-column header-align="center"  align="center" prop="tupian" label="图片">
                          <template #default="{row}">
                              <el-image v-if="row.tupian" :src="row.tupian" style="width: 80px; height: 80px; border-radius: 4px;" fit="cover" :preview-src-list="[row.tupian]" preview-teleported/>
                              <span v-else>无图片</span>
                          </template>
                      </el-table-column>
                                                                                                                          <el-table-column header-align="center"  align="center" prop="xingbie" label="性别"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="xihao" label="喜好"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="xiangqing" label="详情"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="userid" label="用户id"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="clicktime" label="最近点击时间"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="clicknum" label="点击次数"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="thumbsupnum" label="赞"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="crazilynum" label="踩"/>
                                                                          <el-table-column fixed="right" align="center" label="操作" width="250">
              <template #default="{row}">
                                                      <el-button v-if="isAuth('chongwuguanli','修改')" icon="ele-Edit" @click.stop="handleEdit(row)" type="primary" link>修改</el-button>
                  <el-popconfirm  v-if="isAuth('chongwuguanli','删除')"  width="auto" @confirm="handleDelete(row)"  :title="`确定要删除这条记录吗？`">
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

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

  const AddEdit=defineAsyncComponent(()=>import('@/views/zhenduan/add-edit.vue'));
        const state=reactive({
              category:{
          data:[]
        },
        tjdialogTableVisible:false,
          query:{
                                                                                chongwumingcheng:'',
                                                                                            zhenduanshijian:'',
                                                    zhenduanren:'',
                                                            
    },
    page:{
      current:1,
      size:10,
      total:0
    },
              exportList:[],
          tableList:[],
    ids:[],
    isdel:true,
      
        })
  const { query,tableList,page,isdel
              ,exportList
                    ,category,tjdialogTableVisible
                    } = {...toRefs(state)};


      


      //饼图
      const PieChart=defineAsyncComponent(()=>import('@/components/echarts/pieChart.vue'));
      //折线图
      const LineChart=defineAsyncComponent(()=>import('@/components/echarts/lineChart.vue'));
      //柱状图
      const BarChart=defineAsyncComponent(()=>import('@/components/echarts/barChart.vue'));
      //仪表盘
      const Doughnut=defineAsyncComponent(()=>import('@/components/echarts/DoughnutChart.vue'));








      function getfenglei(){
        request({
          url:'group/zhenduan/zhenduanren',
          method:'get'
        }).then((data)=>{

          const newsarr= objArrtransArr(data.data,'zhenduanren','total');
          console.log(newsarr);
          state.category.data=newsarr;

        })
      }


      function  objArrtransArr(olddata:any, oldval:any, oldname:any) {
        const newArr:any = [];
        olddata.forEach(item => {
          // 定义数组内部对象形式
          let obj = {};
          obj.name = item[oldval];
          obj.value = item[oldname];
          // 将对象数据推到数组中
          newArr.push(obj);
        });
        return newArr;
      }


      function tj(){
        state.tjdialogTableVisible=true;
        getfenglei();
      }




      


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
                                                                      if (state.query.zhenduanshijian != '' && state.query.zhenduanshijian != undefined) {
              params['zhenduanshijian'] = '%' + state.query.zhenduanshijian + '%'
            }
                                      if (state.query.zhenduanren != '' && state.query.zhenduanren != undefined) {
              params['zhenduanren'] = '%' + state.query.zhenduanren + '%'
            }
                                                    request({
      url:'zhenduan/page',
      method:'get',
      params
    }).then(({data})=>{

      state.page.total=data.total;
      state.tableList=data.list;
                  for (let i = 0; i < state.tableList.length; i++) {
            state.exportList.push({
                                                                                                                                        chongwumingcheng: state.tableList[i].chongwumingcheng,
                                                                                    zhengzhuang: state.tableList[i].zhengzhuang,
                                                                                    zhiliaojianyi: state.tableList[i].zhiliaojianyi,
                                                                                    zhenduanshijian: state.tableList[i].zhenduanshijian,
                                                                                    zhenduanren: state.tableList[i].zhenduanren,
                                                                                    lianxifangshi: state.tableList[i].lianxifangshi,
                                                                                    userid: state.tableList[i].userid,
                                                })
          }
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
      url:`zhenduan/delete`,
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
      url:`zhenduan/delete`,
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

      

      
      const exportBtn = () => {

        if (!state.exportList) {
          return;
        }

        console.log(state.exportList);

        const titleArr = [
            
                

            
                

            
                
                  '宠物名称',

                

            
                
                  '症状',

                

            
                
                  '治疗建议',

                

            
                
                  '诊断时间',

                

            
                
                  '诊断人',

                

            
                
                  '联系方式',

                

            
                
                  '用户id',

                

                    ]
        exportExcel(state.exportList,'诊断',titleArr,'诊断');
      };

      


      
      
</script>
<template>
  <div class="list-page-container">
    <el-card shadow="hover">
      <div class="header-section">
        <div class="header-title">
                    <h3>诊断</h3>
                  </div>
        <div class="action-buttons">
            <el-button v-if="isAuth('zhenduan','新增')" icon="ele-Plus" type="primary" @click="add()">新增</el-button>
            <el-button v-if="isAuth('zhenduan','删除')" icon="ele-Delete" @click="listdel()" :disabled="isdel" type="danger" plain>批量删除</el-button>
                             <el-button  v-if="isAuth('zhenduan','导出')" icon="ele-Bottom" type="success" @click="exportBtn()">导出</el-button>
                                                        <el-button v-if="isAuth('zhenduan','统计')" type="info" @click="tj()">统计</el-button>
                      </div>
      </div>
       <div class="query-section">
         <el-form :model="query" inline>
                                                                                                        <el-form-item label="宠物名称">
                        <el-input v-model="query.chongwumingcheng" maxlength="100" placeholder="请输入宠物名称" clearable/>
                    </el-form-item>
                                                                                                                        <el-form-item label="诊断时间">
                        <el-input v-model="query.zhenduanshijian" maxlength="100" placeholder="请输入诊断时间" clearable/>
                    </el-form-item>
                                                                <el-form-item label="诊断人">
                        <el-input v-model="query.zhenduanren" maxlength="100" placeholder="请输入诊断人" clearable/>
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
                                                                                                                                                                                                                                                                <el-table-column header-align="center"  align="center" prop="zhenduanshijian" label="诊断时间"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="zhenduanren" label="诊断人"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="lianxifangshi" label="联系方式"/>
                                                                                                                                                <el-table-column header-align="center"  align="center" prop="userid" label="用户id"/>
                                                                          <el-table-column fixed="right" align="center" label="操作" width="250">
              <template #default="{row}">
                                                      <el-button v-if="isAuth('zhenduan','修改')" icon="ele-Edit" @click.stop="handleEdit(row)" type="primary" link>修改</el-button>
                  <el-popconfirm  v-if="isAuth('zhenduan','删除')"  width="auto" @confirm="handleDelete(row)"  :title="`确定要删除这条记录吗？`">
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

    <el-dialog v-model="tjdialogTableVisible" title="统计" width="800" destroy-on-close
               center>


        <PieChart
                title="诊断人统计"
                subtitle="诊断人统计"
                :data="category.data"
        />



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

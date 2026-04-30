package com.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.entity.UserEntity;
import com.utils.ValidatorUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
 import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.entity.XuexiguanliEntity;
import com.entity.view.XuexiguanliView;
import com.service.XuexiguanliService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 学习管理Controller
 *后端接口
 */
@RestController
@RequestMapping("/xuexiguanli")
public class XuexiguanliController {

    @Autowired
    private XuexiguanliService xuexiguanliService;



    
    
            /**
         * 后端列表
         */
                    @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, XuexiguanliEntity xuexiguanli, HttpServletRequest request) {
                                    EntityWrapper<XuexiguanliEntity> ew = new EntityWrapper<XuexiguanliEntity>();
            PageUtils page = xuexiguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexiguanli), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                @IgnoreAuth
                            @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, XuexiguanliEntity xuexiguanli, HttpServletRequest request) {

         

                            xuexiguanli.setSfsh("是");
                                    EntityWrapper<XuexiguanliEntity> ew = new EntityWrapper<XuexiguanliEntity>();
            PageUtils page = xuexiguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexiguanli), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
             * 列表
             */
                    @RequestMapping("/lists")
        public R list( XuexiguanliEntity xuexiguanli) {
            EntityWrapper<XuexiguanliEntity> ew = new EntityWrapper<XuexiguanliEntity>();
            ew.allEq(MPUtil.allEQMapPre( xuexiguanli, "xuexiguanli"));
            return R.ok().put("data", xuexiguanliService.selectListView(ew));
        }

        /**
         * 查询
         */
                    @RequestMapping("/query")
        public R query(XuexiguanliEntity xuexiguanli) {
            EntityWrapper< XuexiguanliEntity> ew = new EntityWrapper< XuexiguanliEntity>();
            ew.allEq(MPUtil.allEQMapPre( xuexiguanli, "Xuexiguanli"));
                XuexiguanliView xuexiguanliView = xuexiguanliService.selectView(ew);
            return R.ok("学习管理").put("data", xuexiguanliView);
        }

        /**
             * 后端详情
             */
                        @IgnoreAuth
                                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
                XuexiguanliEntity xuexiguanli = xuexiguanliService.selectById(id);
            return R.ok().put("data", xuexiguanli);
        }

        /**
            * 前端详情
            */
                        @IgnoreAuth
                                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
                XuexiguanliEntity xuexiguanli = xuexiguanliService.selectById(id);
                            xuexiguanli.setClicknum(xuexiguanli.getClicknum() + 1);
                xuexiguanli.setClicktime(Timestamp.valueOf(LocalDateTime.now()));
                    xuexiguanliService.updateById(xuexiguanli);
                        return R.ok().put("data", xuexiguanli);
        }

        /**
        * 后端保存
        */
                    @RequestMapping("/save")
        public R save(@RequestBody XuexiguanliEntity xuexiguanli, HttpServletRequest request) {
            
        
            xuexiguanli.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            xuexiguanliService.insert(xuexiguanli);
            return R.ok();
        }

        /**
            * 前端保存
            */
                    @RequestMapping("/add")
        public R add(@RequestBody XuexiguanliEntity xuexiguanli, HttpServletRequest request) {
                                xuexiguanli.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                xuexiguanliService.insert(xuexiguanli);
            return R.ok();
        }

        /**
          * 修改
          */
                    @RequestMapping("/update")
        public R update(@RequestBody XuexiguanliEntity xuexiguanli, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                xuexiguanliService.updateById(xuexiguanli);//全部更新
            return R.ok();
        }

        /**
             * 删除
             */
                    @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
                xuexiguanliService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

            /**
         * 推荐
         * @param userId
         * @param num
         * @return
         */
        @PostMapping("/recommendList")
        public R recommendList(@RequestParam("userId") Long userId, @RequestParam("num") Integer num) {
            Map<String, Double> map = xuexiguanliService.recommend(userId, num);
            List<Map.Entry<String, Double>> entryList = new ArrayList<>(map.entrySet());
            // 使用Collections.sort()对List进行排序
            Collections.sort(entryList, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
            // 提取排序后的键
            List<Long> sortedKeys = new ArrayList<>();
            for (Map.Entry<String, Double> entry : entryList) {
                if(entry.getValue() > 0) {
                    sortedKeys.add(Long.valueOf(entry.getKey()));
                }
            }
            if(sortedKeys.size() > 0) {
                return R.ok().put("data", xuexiguanliService.selectBatchIds(sortedKeys));
            }
            return R.ok().put("data", null);
        }
    
    





    
}

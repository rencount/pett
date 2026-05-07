package com.controller;

import java.text.SimpleDateFormat;
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
import com.entity.ZhenduanEntity;
import com.entity.view.ZhenduanView;
import com.service.ZhenduanService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 诊断Controller
 *后端接口
 */
@RestController
@RequestMapping("/zhenduan")
public class ZhenduanController {

    @Autowired
    private ZhenduanService zhenduanService;



    
    
            /**
         * 后端列表
         */
                    @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, ZhenduanEntity zhenduan, HttpServletRequest request) {
                            if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
                    zhenduan.setUserid((Long) request.getSession().getAttribute("userId"));
                }
                                    EntityWrapper<ZhenduanEntity> ew = new EntityWrapper<ZhenduanEntity>();
            PageUtils page = zhenduanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhenduan), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                            @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, ZhenduanEntity zhenduan, HttpServletRequest request) {

         

                                    EntityWrapper<ZhenduanEntity> ew = new EntityWrapper<ZhenduanEntity>();
            PageUtils page = zhenduanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhenduan), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
             * 列表
             */
                    @RequestMapping("/lists")
        public R list( ZhenduanEntity zhenduan) {
            EntityWrapper<ZhenduanEntity> ew = new EntityWrapper<ZhenduanEntity>();
            ew.allEq(MPUtil.allEQMapPre( zhenduan, "zhenduan"));
            return R.ok().put("data", zhenduanService.selectListView(ew));
        }

        /**
         * 查询
         */
                    @RequestMapping("/query")
        public R query(ZhenduanEntity zhenduan) {
            EntityWrapper< ZhenduanEntity> ew = new EntityWrapper< ZhenduanEntity>();
            ew.allEq(MPUtil.allEQMapPre( zhenduan, "Zhenduan"));
                ZhenduanView zhenduanView = zhenduanService.selectView(ew);
            return R.ok("诊断").put("data", zhenduanView);
        }

        /**
             * 后端详情
             */
                                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
                ZhenduanEntity zhenduan = zhenduanService.selectById(id);
            return R.ok().put("data", zhenduan);
        }

        /**
            * 前端详情
            */
                                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
                ZhenduanEntity zhenduan = zhenduanService.selectById(id);
                        return R.ok().put("data", zhenduan);
        }

        /**
        * 后端保存
        */
                    @RequestMapping("/save")
        public R save(@RequestBody ZhenduanEntity zhenduan, HttpServletRequest request) {


            zhenduan.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
            // 如果前端已传入userid（如医生代患者创建诊断报告），则保留；否则从session获取
            if (zhenduan.getUserid() == null) {
                zhenduan.setUserid((Long) request.getSession().getAttribute("userId"));
            }
                            zhenduanService.insert(zhenduan);
            return R.ok();
        }

        /**
            * 前端保存
            */
                    @RequestMapping("/add")
        public R add(@RequestBody ZhenduanEntity zhenduan, HttpServletRequest request) {
                    
            zhenduan.setUserid((Long) request.getSession().getAttribute("userId"));

                        zhenduan.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                zhenduanService.insert(zhenduan);
            return R.ok();
        }

        /**
          * 修改
          */
                    @RequestMapping("/update")
        public R update(@RequestBody ZhenduanEntity zhenduan, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                zhenduanService.updateById(zhenduan);//全部更新
            return R.ok();
        }

        /**
             * 删除
             */
                    @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
                zhenduanService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





    
}

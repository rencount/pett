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
import com.entity.JibingshangchuanEntity;
import com.entity.view.JibingshangchuanView;
import com.service.JibingshangchuanService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 疾病上传Controller
 *后端接口
 */
@RestController
@RequestMapping("/jibingshangchuan")
public class JibingshangchuanController {

    @Autowired
    private JibingshangchuanService jibingshangchuanService;



    
    
            /**
         * 后端列表
         */
                    @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, JibingshangchuanEntity jibingshangchuan, HttpServletRequest request) {
                            String role = request.getSession().getAttribute("role").toString();
                // 只有管理员和宠物医生可以查看所有数据，其他角色只能查看自己的数据
                if (!role.equals("管理员") && !role.equals("宠物医生")) {
                    jibingshangchuan.setUserid((Long) request.getSession().getAttribute("userId"));
                }
                                    EntityWrapper<JibingshangchuanEntity> ew = new EntityWrapper<JibingshangchuanEntity>();
            PageUtils page = jibingshangchuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jibingshangchuan), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                            @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, JibingshangchuanEntity jibingshangchuan, HttpServletRequest request) {

         

                            jibingshangchuan.setSfsh("是");
                                    EntityWrapper<JibingshangchuanEntity> ew = new EntityWrapper<JibingshangchuanEntity>();
            PageUtils page = jibingshangchuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jibingshangchuan), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
             * 列表
             */
                    @RequestMapping("/lists")
        public R list( JibingshangchuanEntity jibingshangchuan) {
            EntityWrapper<JibingshangchuanEntity> ew = new EntityWrapper<JibingshangchuanEntity>();
            ew.allEq(MPUtil.allEQMapPre( jibingshangchuan, "jibingshangchuan"));
            return R.ok().put("data", jibingshangchuanService.selectListView(ew));
        }

        /**
         * 查询
         */
                    @RequestMapping("/query")
        public R query(JibingshangchuanEntity jibingshangchuan) {
            EntityWrapper< JibingshangchuanEntity> ew = new EntityWrapper< JibingshangchuanEntity>();
            ew.allEq(MPUtil.allEQMapPre( jibingshangchuan, "Jibingshangchuan"));
                JibingshangchuanView jibingshangchuanView = jibingshangchuanService.selectView(ew);
            return R.ok("疾病上传").put("data", jibingshangchuanView);
        }

        /**
             * 后端详情
             */
                                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
                JibingshangchuanEntity jibingshangchuan = jibingshangchuanService.selectById(id);
            return R.ok().put("data", jibingshangchuan);
        }

        /**
            * 前端详情
            */
                                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
                JibingshangchuanEntity jibingshangchuan = jibingshangchuanService.selectById(id);
                        return R.ok().put("data", jibingshangchuan);
        }

        /**
        * 后端保存
        */
                    @RequestMapping("/save")
        public R save(@RequestBody JibingshangchuanEntity jibingshangchuan, HttpServletRequest request) {
            
        
            jibingshangchuan.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            jibingshangchuan.setUserid((Long) request.getSession().getAttribute("userId"));
                            jibingshangchuanService.insert(jibingshangchuan);
            return R.ok();
        }

        /**
            * 前端保存
            */
                    @RequestMapping("/add")
        public R add(@RequestBody JibingshangchuanEntity jibingshangchuan, HttpServletRequest request) {
                    
            jibingshangchuan.setUserid((Long) request.getSession().getAttribute("userId"));

                        jibingshangchuan.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                jibingshangchuanService.insert(jibingshangchuan);
            return R.ok();
        }

        /**
          * 修改
          */
                    @RequestMapping("/update")
        public R update(@RequestBody JibingshangchuanEntity jibingshangchuan, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                jibingshangchuanService.updateById(jibingshangchuan);//全部更新
            return R.ok();
        }

        /**
             * 删除
             */
                    @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
                jibingshangchuanService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





    
}

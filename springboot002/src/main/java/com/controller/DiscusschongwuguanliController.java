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
import com.entity.DiscusschongwuguanliEntity;
import com.entity.view.DiscusschongwuguanliView;
import com.service.DiscusschongwuguanliService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 宠物管理评论Controller
 *后端接口
 */
@RestController
@RequestMapping("/discusschongwuguanli")
public class DiscusschongwuguanliController {

    @Autowired
    private DiscusschongwuguanliService discusschongwuguanliService;



    
    
            /**
         * 后端列表
         */
                    @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, DiscusschongwuguanliEntity discusschongwuguanli, HttpServletRequest request) {
                                    EntityWrapper<DiscusschongwuguanliEntity> ew = new EntityWrapper<DiscusschongwuguanliEntity>();
            PageUtils page = discusschongwuguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusschongwuguanli), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                            @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, DiscusschongwuguanliEntity discusschongwuguanli, HttpServletRequest request) {

         

                                    EntityWrapper<DiscusschongwuguanliEntity> ew = new EntityWrapper<DiscusschongwuguanliEntity>();
            PageUtils page = discusschongwuguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusschongwuguanli), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
             * 列表
             */
                    @RequestMapping("/lists")
        public R list( DiscusschongwuguanliEntity discusschongwuguanli) {
            EntityWrapper<DiscusschongwuguanliEntity> ew = new EntityWrapper<DiscusschongwuguanliEntity>();
            ew.allEq(MPUtil.allEQMapPre( discusschongwuguanli, "discusschongwuguanli"));
            return R.ok().put("data", discusschongwuguanliService.selectListView(ew));
        }

        /**
         * 查询
         */
                    @RequestMapping("/query")
        public R query(DiscusschongwuguanliEntity discusschongwuguanli) {
            EntityWrapper< DiscusschongwuguanliEntity> ew = new EntityWrapper< DiscusschongwuguanliEntity>();
            ew.allEq(MPUtil.allEQMapPre( discusschongwuguanli, "Discusschongwuguanli"));
                DiscusschongwuguanliView discusschongwuguanliView = discusschongwuguanliService.selectView(ew);
            return R.ok("宠物管理评论").put("data", discusschongwuguanliView);
        }

        /**
             * 后端详情
             */
                                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
                DiscusschongwuguanliEntity discusschongwuguanli = discusschongwuguanliService.selectById(id);
            return R.ok().put("data", discusschongwuguanli);
        }

        /**
            * 前端详情
            */
                                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
                DiscusschongwuguanliEntity discusschongwuguanli = discusschongwuguanliService.selectById(id);
                        return R.ok().put("data", discusschongwuguanli);
        }

        /**
        * 后端保存
        */
                    @RequestMapping("/save")
        public R save(@RequestBody DiscusschongwuguanliEntity discusschongwuguanli, HttpServletRequest request) {
            
        
            discusschongwuguanli.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            discusschongwuguanliService.insert(discusschongwuguanli);
            return R.ok();
        }

        /**
            * 前端保存
            */
                    @RequestMapping("/add")
        public R add(@RequestBody DiscusschongwuguanliEntity discusschongwuguanli, HttpServletRequest request) {
                                discusschongwuguanli.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                discusschongwuguanliService.insert(discusschongwuguanli);
            return R.ok();
        }

        /**
          * 修改
          */
                    @RequestMapping("/update")
        public R update(@RequestBody DiscusschongwuguanliEntity discusschongwuguanli, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                discusschongwuguanliService.updateById(discusschongwuguanli);//全部更新
            return R.ok();
        }

        /**
             * 删除
             */
                    @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
                discusschongwuguanliService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





    
}

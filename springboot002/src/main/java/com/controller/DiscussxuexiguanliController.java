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
import com.entity.DiscussxuexiguanliEntity;
import com.entity.view.DiscussxuexiguanliView;
import com.service.DiscussxuexiguanliService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 学习管理评论Controller
 *后端接口
 */
@RestController
@RequestMapping("/discussxuexiguanli")
public class DiscussxuexiguanliController {

    @Autowired
    private DiscussxuexiguanliService discussxuexiguanliService;



    
    
            /**
         * 后端列表
         */
                    @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, DiscussxuexiguanliEntity discussxuexiguanli, HttpServletRequest request) {
                                    EntityWrapper<DiscussxuexiguanliEntity> ew = new EntityWrapper<DiscussxuexiguanliEntity>();
            PageUtils page = discussxuexiguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussxuexiguanli), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                            @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, DiscussxuexiguanliEntity discussxuexiguanli, HttpServletRequest request) {

         

                                    EntityWrapper<DiscussxuexiguanliEntity> ew = new EntityWrapper<DiscussxuexiguanliEntity>();
            PageUtils page = discussxuexiguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussxuexiguanli), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
             * 列表
             */
                    @RequestMapping("/lists")
        public R list( DiscussxuexiguanliEntity discussxuexiguanli) {
            EntityWrapper<DiscussxuexiguanliEntity> ew = new EntityWrapper<DiscussxuexiguanliEntity>();
            ew.allEq(MPUtil.allEQMapPre( discussxuexiguanli, "discussxuexiguanli"));
            return R.ok().put("data", discussxuexiguanliService.selectListView(ew));
        }

        /**
         * 查询
         */
                    @RequestMapping("/query")
        public R query(DiscussxuexiguanliEntity discussxuexiguanli) {
            EntityWrapper< DiscussxuexiguanliEntity> ew = new EntityWrapper< DiscussxuexiguanliEntity>();
            ew.allEq(MPUtil.allEQMapPre( discussxuexiguanli, "Discussxuexiguanli"));
                DiscussxuexiguanliView discussxuexiguanliView = discussxuexiguanliService.selectView(ew);
            return R.ok("学习管理评论").put("data", discussxuexiguanliView);
        }

        /**
             * 后端详情
             */
                                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
                DiscussxuexiguanliEntity discussxuexiguanli = discussxuexiguanliService.selectById(id);
            return R.ok().put("data", discussxuexiguanli);
        }

        /**
            * 前端详情
            */
                                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
                DiscussxuexiguanliEntity discussxuexiguanli = discussxuexiguanliService.selectById(id);
                        return R.ok().put("data", discussxuexiguanli);
        }

        /**
        * 后端保存
        */
                    @RequestMapping("/save")
        public R save(@RequestBody DiscussxuexiguanliEntity discussxuexiguanli, HttpServletRequest request) {
            
        
            discussxuexiguanli.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            discussxuexiguanliService.insert(discussxuexiguanli);
            return R.ok();
        }

        /**
            * 前端保存
            */
                    @RequestMapping("/add")
        public R add(@RequestBody DiscussxuexiguanliEntity discussxuexiguanli, HttpServletRequest request) {
                                discussxuexiguanli.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                discussxuexiguanliService.insert(discussxuexiguanli);
            return R.ok();
        }

        /**
          * 修改
          */
                    @RequestMapping("/update")
        public R update(@RequestBody DiscussxuexiguanliEntity discussxuexiguanli, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                discussxuexiguanliService.updateById(discussxuexiguanli);//全部更新
            return R.ok();
        }

        /**
             * 删除
             */
                    @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
                discussxuexiguanliService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





    
}

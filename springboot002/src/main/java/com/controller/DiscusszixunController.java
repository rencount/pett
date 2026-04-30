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
import com.entity.DiscusszixunEntity;
import com.entity.view.DiscusszixunView;
import com.service.DiscusszixunService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 咨询评论Controller
 *后端接口
 */
@RestController
@RequestMapping("/discusszixun")
public class DiscusszixunController {

    @Autowired
    private DiscusszixunService discusszixunService;

    @Autowired
    private com.service.ZixunService zixunService;



    
    
            /**
         * 后端列表
         */
                    @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, DiscusszixunEntity discusszixun, HttpServletRequest request) {
            EntityWrapper<DiscusszixunEntity> ew = new EntityWrapper<DiscusszixunEntity>();
            String role = request.getSession().getAttribute("role").toString();
            
            if (role.equals("宠物医生")) {
                // 获取当前医生的 ID
                Long userId = (Long) request.getSession().getAttribute("userId");
                // 查询该医生相关的所有咨询 ID
                List<com.entity.ZixunEntity> zixunList = zixunService.selectList(
                    new EntityWrapper<com.entity.ZixunEntity>().eq("userid", userId));
                if (zixunList != null && !zixunList.isEmpty()) {
                    List<Long> refids = new java.util.ArrayList<Long>();
                    for(com.entity.ZixunEntity z : zixunList) {
                        refids.add(z.getId());
                    }
                    ew.in("refid", refids);
                } else {
                    ew.eq("refid", -1L); // 无数据
                }
            }
            
            PageUtils page = discusszixunService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusszixun), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                            @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, DiscusszixunEntity discusszixun, HttpServletRequest request) {

         

                                    EntityWrapper<DiscusszixunEntity> ew = new EntityWrapper<DiscusszixunEntity>();
            PageUtils page = discusszixunService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusszixun), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
             * 列表
             */
                    @RequestMapping("/lists")
        public R list( DiscusszixunEntity discusszixun) {
            EntityWrapper<DiscusszixunEntity> ew = new EntityWrapper<DiscusszixunEntity>();
            ew.allEq(MPUtil.allEQMapPre( discusszixun, "discusszixun"));
            return R.ok().put("data", discusszixunService.selectListView(ew));
        }

        /**
         * 查询
         */
                    @RequestMapping("/query")
        public R query(DiscusszixunEntity discusszixun) {
            EntityWrapper< DiscusszixunEntity> ew = new EntityWrapper< DiscusszixunEntity>();
            ew.allEq(MPUtil.allEQMapPre( discusszixun, "Discusszixun"));
                DiscusszixunView discusszixunView = discusszixunService.selectView(ew);
            return R.ok("咨询评论").put("data", discusszixunView);
        }

        /**
             * 后端详情
             */
                                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
                DiscusszixunEntity discusszixun = discusszixunService.selectById(id);
            return R.ok().put("data", discusszixun);
        }

        /**
            * 前端详情
            */
                                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
                DiscusszixunEntity discusszixun = discusszixunService.selectById(id);
                        return R.ok().put("data", discusszixun);
        }

        /**
        * 后端保存
        */
                    @RequestMapping("/save")
        public R save(@RequestBody DiscusszixunEntity discusszixun, HttpServletRequest request) {
            
        
            discusszixun.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            discusszixunService.insert(discusszixun);
            return R.ok();
        }

        /**
            * 前端保存
            */
                    @RequestMapping("/add")
        public R add(@RequestBody DiscusszixunEntity discusszixun, HttpServletRequest request) {
                                discusszixun.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                discusszixunService.insert(discusszixun);
            return R.ok();
        }

        /**
          * 修改
          */
                    @RequestMapping("/update")
        public R update(@RequestBody DiscusszixunEntity discusszixun, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                discusszixunService.updateById(discusszixun);//全部更新
            return R.ok();
        }

        /**
             * 删除
             */
                    @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
                discusszixunService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





    
}

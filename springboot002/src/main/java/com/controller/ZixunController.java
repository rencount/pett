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
import com.entity.ZixunEntity;
import com.entity.view.ZixunView;
import com.service.ZixunService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 咨询Controller
 *后端接口
 */
@RestController
@RequestMapping("/zixun")
public class ZixunController {

    @Autowired
    private ZixunService zixunService;
    
    @Autowired
    private com.service.YonghuService yonghuService;



    
    
            /**
         * 后端列表
         */
                    @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, ZixunEntity zixun, HttpServletRequest request) {
            if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
                zixun.setUserid((Long) request.getSession().getAttribute("userId"));
            }
            EntityWrapper<ZixunEntity> ew = new EntityWrapper<ZixunEntity>();
            PageUtils page = zixunService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixun), params), params));
            
            // 动态填充最新的医生信息
            List<com.entity.view.ZixunView> list = (List<com.entity.view.ZixunView>) page.getList();
            com.service.ChongwuyishengService chongwuyishengService = (com.service.ChongwuyishengService)com.utils.SpringContextUtils.getBean("chongwuyishengService");
            for (com.entity.view.ZixunView view : list) {
                com.entity.ChongwuyishengEntity doctor = null;
                if (view.getUserid() != null) {
                    doctor = chongwuyishengService.selectById(view.getUserid());
                }
                if (doctor == null && org.apache.commons.lang3.StringUtils.isNotBlank(view.getChongwuyisheng())) {
                    doctor = chongwuyishengService.selectOne(new com.baomidou.mybatisplus.mapper.EntityWrapper<com.entity.ChongwuyishengEntity>().eq("zhanghao", view.getChongwuyisheng()).or().eq("nicheng", view.getChongwuyisheng()));
                }
                if (doctor != null) {
                    view.setUserid(doctor.getId());
                    view.setChongwuyisheng(doctor.getNicheng() != null ? doctor.getNicheng() : doctor.getZhanghao());
                    view.setTouxiang(doctor.getTouxiang());
                    view.setXingbie(doctor.getXingbie());
                    view.setLianxifangshi(doctor.getLianxifangshi());
                }
            }
            
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
        @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, ZixunEntity zixun, HttpServletRequest request) {
            EntityWrapper<ZixunEntity> ew = new EntityWrapper<ZixunEntity>();
            PageUtils page = zixunService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixun), params), params));
            
            // 动态填充最新的医生信息
            List<com.entity.view.ZixunView> list = (List<com.entity.view.ZixunView>) page.getList();
            com.service.ChongwuyishengService chongwuyishengService = (com.service.ChongwuyishengService)com.utils.SpringContextUtils.getBean("chongwuyishengService");
            for (com.entity.view.ZixunView view : list) {
                com.entity.ChongwuyishengEntity doctor = null;
                if (view.getUserid() != null) {
                    doctor = chongwuyishengService.selectById(view.getUserid());
                }
                if (doctor == null && org.apache.commons.lang3.StringUtils.isNotBlank(view.getChongwuyisheng())) {
                    doctor = chongwuyishengService.selectOne(new com.baomidou.mybatisplus.mapper.EntityWrapper<com.entity.ChongwuyishengEntity>().eq("zhanghao", view.getChongwuyisheng()).or().eq("nicheng", view.getChongwuyisheng()));
                }
                if (doctor != null) {
                    view.setUserid(doctor.getId());
                    view.setChongwuyisheng(doctor.getNicheng() != null ? doctor.getNicheng() : doctor.getZhanghao());
                    view.setTouxiang(doctor.getTouxiang());
                    view.setXingbie(doctor.getXingbie());
                    view.setLianxifangshi(doctor.getLianxifangshi());
                }
            }
            
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
             * 列表
             */
                    @RequestMapping("/lists")
        public R list( ZixunEntity zixun) {
            EntityWrapper<ZixunEntity> ew = new EntityWrapper<ZixunEntity>();
            ew.allEq(MPUtil.allEQMapPre( zixun, "zixun"));
            return R.ok().put("data", zixunService.selectListView(ew));
        }

        /**
         * 查询
         */
                    @RequestMapping("/query")
        public R query(ZixunEntity zixun) {
            EntityWrapper< ZixunEntity> ew = new EntityWrapper< ZixunEntity>();
            ew.allEq(MPUtil.allEQMapPre( zixun, "Zixun"));
                ZixunView zixunView = zixunService.selectView(ew);
            return R.ok("咨询").put("data", zixunView);
        }

        /**
             * 后端详情
             */
                                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
                ZixunEntity zixun = zixunService.selectById(id);
            return R.ok().put("data", zixun);
        }

        /**
            * 前端详情
            */
                                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
                ZixunEntity zixun = zixunService.selectById(id);
                        return R.ok().put("data", zixun);
        }

        /**
        * 后端保存
        */
        @RequestMapping("/save")
        public R save(@RequestBody ZixunEntity zixun, HttpServletRequest request) {
            zixun.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(zixun);
            zixun.setUserid((Long) request.getSession().getAttribute("userId"));
            
            if (zixun.getChongwuyisheng() != null) {
                com.service.ChongwuyishengService chongwuyishengService = (com.service.ChongwuyishengService)com.utils.SpringContextUtils.getBean("chongwuyishengService");
                com.entity.ChongwuyishengEntity doctor = chongwuyishengService.selectOne(new com.baomidou.mybatisplus.mapper.EntityWrapper<com.entity.ChongwuyishengEntity>().eq("zhanghao", zixun.getChongwuyisheng()).or().eq("nicheng", zixun.getChongwuyisheng()));
                if (doctor != null) {
                    zixun.setUserid(doctor.getId());
                    zixun.setChongwuyisheng(doctor.getNicheng() != null ? doctor.getNicheng() : doctor.getZhanghao());
                    zixun.setTouxiang(doctor.getTouxiang());
                    zixun.setXingbie(doctor.getXingbie());
                    zixun.setLianxifangshi(doctor.getLianxifangshi());
                }
            }
            zixunService.insert(zixun);
            return R.ok();
        }

        /**
            * 前端保存
            */
        @RequestMapping("/add")
        public R add(@RequestBody ZixunEntity zixun, HttpServletRequest request) {
            zixun.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(zixun);
            zixun.setUserid((Long) request.getSession().getAttribute("userId"));
            
            if (zixun.getChongwuyisheng() != null) {
                com.service.ChongwuyishengService chongwuyishengService = (com.service.ChongwuyishengService)com.utils.SpringContextUtils.getBean("chongwuyishengService");
                com.entity.ChongwuyishengEntity doctor = chongwuyishengService.selectOne(new com.baomidou.mybatisplus.mapper.EntityWrapper<com.entity.ChongwuyishengEntity>().eq("zhanghao", zixun.getChongwuyisheng()).or().eq("nicheng", zixun.getChongwuyisheng()));
                if (doctor != null) {
                    zixun.setUserid(doctor.getId());
                    zixun.setChongwuyisheng(doctor.getNicheng() != null ? doctor.getNicheng() : doctor.getZhanghao());
                    zixun.setTouxiang(doctor.getTouxiang());
                    zixun.setXingbie(doctor.getXingbie());
                    zixun.setLianxifangshi(doctor.getLianxifangshi());
                }
            }
            zixunService.insert(zixun);
            return R.ok();
        }

        /**
          * 修改
          */
        @RequestMapping("/update")
        public R update(@RequestBody ZixunEntity zixun, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zixun);
            if (zixun.getChongwuyisheng() != null) {
                com.service.ChongwuyishengService chongwuyishengService = (com.service.ChongwuyishengService)com.utils.SpringContextUtils.getBean("chongwuyishengService");
                com.entity.ChongwuyishengEntity doctor = chongwuyishengService.selectOne(new com.baomidou.mybatisplus.mapper.EntityWrapper<com.entity.ChongwuyishengEntity>().eq("zhanghao", zixun.getChongwuyisheng()).or().eq("nicheng", zixun.getChongwuyisheng()));
                if (doctor != null) {
                    zixun.setUserid(doctor.getId());
                    zixun.setChongwuyisheng(doctor.getNicheng() != null ? doctor.getNicheng() : doctor.getZhanghao());
                    zixun.setTouxiang(doctor.getTouxiang());
                    zixun.setXingbie(doctor.getXingbie());
                    zixun.setLianxifangshi(doctor.getLianxifangshi());
                }
            }
            zixunService.updateById(zixun);//全部更新
            return R.ok();
        }

        /**
             * 删除
             */
                    @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
                zixunService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





    
}

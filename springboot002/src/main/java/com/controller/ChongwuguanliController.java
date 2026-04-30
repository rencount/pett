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
import com.entity.ChongwuguanliEntity;
import com.entity.view.ChongwuguanliView;
import com.service.ChongwuguanliService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 宠物管理Controller
 *后端接口
 */
@RestController
@RequestMapping("/chongwuguanli")
public class ChongwuguanliController {

    @Autowired
    private ChongwuguanliService chongwuguanliService;



    
    
            /**
         * 后端列表
         */
                    @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, ChongwuguanliEntity chongwuguanli, HttpServletRequest request) {
                            if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
                    chongwuguanli.setUserid((Long) request.getSession().getAttribute("userId"));
                }
                                    EntityWrapper<ChongwuguanliEntity> ew = new EntityWrapper<ChongwuguanliEntity>();
            PageUtils page = chongwuguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chongwuguanli), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                            @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, ChongwuguanliEntity chongwuguanli, HttpServletRequest request) {

         

                                    EntityWrapper<ChongwuguanliEntity> ew = new EntityWrapper<ChongwuguanliEntity>();
            PageUtils page = chongwuguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chongwuguanli), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
             * 列表
             */
                    @RequestMapping("/lists")
        public R list( ChongwuguanliEntity chongwuguanli) {
            EntityWrapper<ChongwuguanliEntity> ew = new EntityWrapper<ChongwuguanliEntity>();
            ew.allEq(MPUtil.allEQMapPre( chongwuguanli, "chongwuguanli"));
            return R.ok().put("data", chongwuguanliService.selectListView(ew));
        }

        /**
         * 查询
         */
                    @RequestMapping("/query")
        public R query(ChongwuguanliEntity chongwuguanli) {
            EntityWrapper< ChongwuguanliEntity> ew = new EntityWrapper< ChongwuguanliEntity>();
            ew.allEq(MPUtil.allEQMapPre( chongwuguanli, "Chongwuguanli"));
                ChongwuguanliView chongwuguanliView = chongwuguanliService.selectView(ew);
            return R.ok("宠物管理").put("data", chongwuguanliView);
        }

        /**
             * 后端详情
             */
                                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
                ChongwuguanliEntity chongwuguanli = chongwuguanliService.selectById(id);
            return R.ok().put("data", chongwuguanli);
        }

        /**
            * 前端详情
            */
                                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
                ChongwuguanliEntity chongwuguanli = chongwuguanliService.selectById(id);
                            chongwuguanli.setClicknum(chongwuguanli.getClicknum() + 1);
                chongwuguanli.setClicktime(Timestamp.valueOf(LocalDateTime.now()));
                    chongwuguanliService.updateById(chongwuguanli);
                        return R.ok().put("data", chongwuguanli);
        }

        /**
        * 后端保存
        */
                    @RequestMapping("/save")
        public R save(@RequestBody ChongwuguanliEntity chongwuguanli, HttpServletRequest request) {
            
        
            chongwuguanli.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            chongwuguanli.setUserid((Long) request.getSession().getAttribute("userId"));
                            chongwuguanliService.insert(chongwuguanli);
            return R.ok();
        }

        /**
            * 前端保存
            */
                    @RequestMapping("/add")
        public R add(@RequestBody ChongwuguanliEntity chongwuguanli, HttpServletRequest request) {
                    
            chongwuguanli.setUserid((Long) request.getSession().getAttribute("userId"));

                        chongwuguanli.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                chongwuguanliService.insert(chongwuguanli);
            return R.ok();
        }

        /**
          * 修改
          */
                    @RequestMapping("/update")
        public R update(@RequestBody ChongwuguanliEntity chongwuguanli, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                chongwuguanliService.updateById(chongwuguanli);//全部更新
            return R.ok();
        }

        /**
             * 删除
             */
                    @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
                chongwuguanliService.deleteBatchIds(Arrays.asList(ids));
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
            Map<String, Double> map = chongwuguanliService.recommend(userId, num);
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
                return R.ok().put("data", chongwuguanliService.selectBatchIds(sortedKeys));
            }
            return R.ok().put("data", null);
        }
    
    





    
}

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
import com.entity.ChongwuyishengEntity;
import com.entity.ZixunEntity;
import com.entity.view.ChongwuyishengView;
import com.service.ChongwuyishengService;
import com.service.TokenService;
import com.service.ZixunService;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 宠物医生Controller
 *后端接口
 */
@RestController
@RequestMapping("/chongwuyisheng")
public class ChongwuyishengController {

    @Autowired
    private ChongwuyishengService chongwuyishengService;

    @Autowired
    private ZixunService zixunService;

    @Autowired
    private com.service.ConsultationService consultationService;

            @Autowired
        private TokenService tokenService;
    
    
            /**
         * 后端列表
         */
                    @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, ChongwuyishengEntity chongwuyisheng, HttpServletRequest request) {
                                    EntityWrapper<ChongwuyishengEntity> ew = new EntityWrapper<ChongwuyishengEntity>();
            PageUtils page = chongwuyishengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chongwuyisheng), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                            @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, ChongwuyishengEntity chongwuyisheng, HttpServletRequest request) {

         

                                    EntityWrapper<ChongwuyishengEntity> ew = new EntityWrapper<ChongwuyishengEntity>();
            PageUtils page = chongwuyishengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chongwuyisheng), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
             * 列表
             */
                    @RequestMapping("/lists")
        public R list( ChongwuyishengEntity chongwuyisheng) {
            EntityWrapper<ChongwuyishengEntity> ew = new EntityWrapper<ChongwuyishengEntity>();
            ew.allEq(MPUtil.allEQMapPre( chongwuyisheng, "chongwuyisheng"));
            return R.ok().put("data", chongwuyishengService.selectListView(ew));
        }

        /**
         * 查询
         */
                    @RequestMapping("/query")
        public R query(ChongwuyishengEntity chongwuyisheng) {
            EntityWrapper< ChongwuyishengEntity> ew = new EntityWrapper< ChongwuyishengEntity>();
            ew.allEq(MPUtil.allEQMapPre( chongwuyisheng, "Chongwuyisheng"));
                ChongwuyishengView chongwuyishengView = chongwuyishengService.selectView(ew);
            return R.ok("宠物医生").put("data", chongwuyishengView);
        }

        /**
             * 后端详情
             */
                                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
                ChongwuyishengEntity chongwuyisheng = chongwuyishengService.selectById(id);
            return R.ok().put("data", chongwuyisheng);
        }

        /**
            * 前端详情
            */
                                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
                ChongwuyishengEntity chongwuyisheng = chongwuyishengService.selectById(id);
                        return R.ok().put("data", chongwuyisheng);
        }

        /**
        * 后端保存
        */
                    @RequestMapping("/save")
        public R save(@RequestBody ChongwuyishengEntity chongwuyisheng, HttpServletRequest request) {
            chongwuyisheng.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            chongwuyishengService.insert(chongwuyisheng);
            
            // 自动在 zixun 表中添加一条记录，以便新医生能直接出现在“在线咨询”列表中
            ZixunEntity zixun = new ZixunEntity();
            zixun.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            zixun.setUserid(chongwuyisheng.getId());
            zixun.setChongwuyisheng(chongwuyisheng.getNicheng() != null ? chongwuyisheng.getNicheng() : chongwuyisheng.getZhanghao());
            zixun.setTouxiang(chongwuyisheng.getTouxiang());
            zixun.setXingbie(chongwuyisheng.getXingbie());
            zixun.setLianxifangshi(chongwuyisheng.getLianxifangshi());
            zixun.setXiangqing(chongwuyisheng.getXiangqing());
            zixunService.insert(zixun);
            
            return R.ok();
        }

        /**
            * 前端保存
            */
        @RequestMapping("/add")
        public R add(@RequestBody ChongwuyishengEntity chongwuyisheng, HttpServletRequest request) {
            chongwuyisheng.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            chongwuyishengService.insert(chongwuyisheng);
            
            // 自动在 zixun 表中添加一条记录，以便新医生能直接出现在“在线咨询”列表中
            ZixunEntity zixun = new ZixunEntity();
            zixun.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            zixun.setUserid(chongwuyisheng.getId());
            zixun.setChongwuyisheng(chongwuyisheng.getNicheng() != null ? chongwuyisheng.getNicheng() : chongwuyisheng.getZhanghao());
            zixun.setTouxiang(chongwuyisheng.getTouxiang());
            zixun.setXingbie(chongwuyisheng.getXingbie());
            zixun.setLianxifangshi(chongwuyisheng.getLianxifangshi());
            zixun.setXiangqing(chongwuyisheng.getXiangqing());
            zixunService.insert(zixun);
            
            return R.ok();
        }

        /**
          * 修改
          */
                    @RequestMapping("/update")
        @Transactional(rollbackFor = Exception.class) // 建议添加事务注解保证数据一致性
        public R update(@RequestBody ChongwuyishengEntity chongwuyisheng, HttpServletRequest request) {
            // 获取修改前的医生信息
            ChongwuyishengEntity oldDoctor = chongwuyishengService.selectById(chongwuyisheng.getId());
            
            chongwuyishengService.updateById(chongwuyisheng);//全部更新
            
            // ================== 添加的代码 开始 ==================
            // 同步更新咨询表中的冗余字段（医生姓名、头像）
            if(oldDoctor != null && oldDoctor.getZhanghao() != null ) {
                // 构建更新条件：根据旧医生账号匹配对应的咨询记录
                EntityWrapper<ZixunEntity> wrapper = new EntityWrapper<ZixunEntity>();
                wrapper.eq("userid" , oldDoctor.getId());
                if (StringUtils.isNotBlank(oldDoctor.getNicheng())) {
                    wrapper.or().eq("chongwuyisheng", oldDoctor.getNicheng()); // 备选：通过旧昵称匹配
                }
                if (StringUtils.isNotBlank(oldDoctor.getZhanghao())) {
                    wrapper.or().eq("chongwuyisheng", oldDoctor.getZhanghao()); // 备选：通过旧账号匹配
                }
                
                // 构建需要更新的字段内容
                ZixunEntity zixunUpdate = new ZixunEntity();
                zixunUpdate.setUserid(chongwuyisheng.getId()); // 【关键】同步修正可能的 userid 错误
                zixunUpdate.setChongwuyisheng(chongwuyisheng.getNicheng() != null ? chongwuyisheng.getNicheng() : chongwuyisheng.getZhanghao());
                zixunUpdate.setTouxiang(chongwuyisheng.getTouxiang());
                zixunUpdate.setXingbie(chongwuyisheng.getXingbie());
                zixunUpdate.setLianxifangshi(chongwuyisheng.getLianxifangshi());
                zixunUpdate.setXiangqing(chongwuyisheng.getXiangqing());
                
                // 执行批量更新
                zixunService.update(zixunUpdate, wrapper);
                
                // 同步更新聊天记录表中的冗余字段（医生姓名）
                com.entity.ConsultationEntity senderUpdate = new com.entity.ConsultationEntity();
                senderUpdate.setSenderName(chongwuyisheng.getNicheng() != null ? chongwuyisheng.getNicheng() : chongwuyisheng.getZhanghao());
                EntityWrapper<com.entity.ConsultationEntity> senderWrapper = new EntityWrapper<com.entity.ConsultationEntity>();
                senderWrapper.eq("sender", chongwuyisheng.getId());
                consultationService.update(senderUpdate, senderWrapper);

                com.entity.ConsultationEntity receiverUpdate = new com.entity.ConsultationEntity();
                receiverUpdate.setReceiverName(chongwuyisheng.getNicheng() != null ? chongwuyisheng.getNicheng() : chongwuyisheng.getZhanghao());
                EntityWrapper<com.entity.ConsultationEntity> receiverWrapper = new EntityWrapper<com.entity.ConsultationEntity>();
                receiverWrapper.eq("receiver", chongwuyisheng.getId());
                consultationService.update(receiverUpdate, receiverWrapper);
            }
            // ================== 添加的代码 结束 ==================
            
            return R.ok();
        }

        /**
             * 删除
             */
        @RequestMapping("/delete")
        @Transactional(rollbackFor = Exception.class) // 开启事务，保证联动删除的原子性
        public R delete(@RequestBody Long[] ids) {
            List<Long> idList = Arrays.asList(ids);
            
            // 1. 删除医生基本信息
            chongwuyishengService.deleteBatchIds(idList);
            
            // 2. 联动删除咨询列表中的医生记录 (zixun 表)
            // 解决前端咨询界面医生头像和信息残留的问题
            zixunService.delete(new EntityWrapper<ZixunEntity>().in("userid", idList));
            
            // 3. 联动删除相关的咨询聊天记录 (consultation 表)
            // 清理已删除医生的历史对话数据
            consultationService.delete(new EntityWrapper<com.entity.ConsultationEntity>()
                .in("sender", idList)
                .or()
                .in("receiver", idList));
                
            return R.ok();
        }
    

    
    





            /**
             * 注册
             */
        @IgnoreAuth
        @RequestMapping("/register")
        public R register(@RequestBody ChongwuyishengEntity chongwuyisheng) {
                ChongwuyishengEntity user = chongwuyishengService.selectOne(new EntityWrapper<ChongwuyishengEntity>().eq("zhanghao", chongwuyisheng.getZhanghao()))
            ;
            if (user != null) {
                return R.error("注册用户已存在");
            }

  Long uId = new Date().getTime();
            chongwuyisheng.setId(uId);
                chongwuyishengService.insert(chongwuyisheng);
   
          
            return R.ok();
        }

        /**
             * 登录
             */
        @IgnoreAuth
        @RequestMapping(value = "/login")
        public R login(@RequestBody UserEntity userEntity , String captcha, HttpServletRequest request) {
                ChongwuyishengEntity user = chongwuyishengService.selectOne(new EntityWrapper<ChongwuyishengEntity>().eq("zhanghao",userEntity.getUsername() ));
            if (user == null || !user.getMima().equals(userEntity.getPassword())) {
                return R.error("账号或密码不正确");
            }
            String token = tokenService.generateToken(user.getId(), userEntity.getUsername(), "chongwuyisheng", "宠物医生");
            return R.ok().put("token", token);
        }

        /**
	 * 退出
	 */
        @RequestMapping("/logout")
        public R logout(HttpServletRequest request) {
            request.getSession().invalidate();
            return R.ok("退出成功");
        }

        /**
     * 获取用户的session用户信息
     */
        @RequestMapping("/session")
        public R getCurrUser(HttpServletRequest request) {
            Long id = (Long) request.getSession().getAttribute("userId");
            ChongwuyishengEntity user = chongwuyishengService.selectById(id);
            JSONObject json = (JSONObject)JSON.toJSON(user);
            json.put("role", "chongwuyisheng");
            return R.ok().put("data", json);
        }

        /**
     * 密码重置
     */
        @IgnoreAuth
        @RequestMapping(value = "/resetPass")
        public R resetPass(String username, HttpServletRequest request) {
                ChongwuyishengEntity user = chongwuyishengService.selectOne(new EntityWrapper<ChongwuyishengEntity>().eq("zhanghao", username));
            if (user == null) {
                return R.error("账号不存在");
            }
            user.setMima("123456");
                chongwuyishengService.updateById(user);
            return R.ok("密码已重置为：123456");
        }
    
}

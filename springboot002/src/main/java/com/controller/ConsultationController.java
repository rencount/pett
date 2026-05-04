package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.ChongwuyishengEntity;
import com.entity.ConsultationEntity;
import com.entity.YonghuEntity;
import com.service.ChongwuyishengService;
import com.service.ConsultationService;
import com.service.YonghuService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private YonghuService yonghuService;

    @Autowired
    private ChongwuyishengService chongwuyishengService;

    /**
     * 根据发送者ID反查头像路径
     */
    private String getAvatarBySenderId(Long senderId) {
        if (senderId == null) return null;
        ChongwuyishengEntity doctor = chongwuyishengService.selectById(senderId);
        if (doctor != null) {
            return doctor.getTouxiang();
        }
        YonghuEntity user = yonghuService.selectById(senderId);
        if (user != null) {
            return user.getTouxiang();
        }
        return null;
    }

    /**
     * 给每条消息填充发送者头像
     */
    private void populateAvatar(List<ConsultationEntity> list) {
        for (ConsultationEntity entity : list) {
            entity.setAvatar(getAvatarBySenderId(entity.getSender()));
        }
    }

    /**
     * 问诊医生与所有人的聊天历史
     */
    @GetMapping("/queryMsg")
    public R queryMsg(@RequestParam Map<String, Object> params, ConsultationEntity consultationEntity, HttpServletRequest request){
        Wrapper<ConsultationEntity> wrapper = new EntityWrapper<ConsultationEntity>();
        wrapper.eq("receiver", consultationEntity.getReceiver()).or().eq("sender", consultationEntity.getReceiver());
        List list = new ArrayList();
        list.add("send_time");
        wrapper.orderAsc(list);
        List<ConsultationEntity> consultationEntities = consultationService.selectList(wrapper);
        populateAvatar(consultationEntities);
        return R.ok().put("data", consultationEntities);
    }

    /**
     * 查询和某个人的聊天记录
     */
    @GetMapping("/querySomeBodyMsg")
    public R querySomeBodyMsg(@RequestParam Map<String, Object> params, ConsultationEntity consultationEntity, HttpServletRequest request) {
        Wrapper<ConsultationEntity> wrapper = new EntityWrapper<ConsultationEntity>();
        Long selfId = (Long) request.getSession().getAttribute("userId");
        wrapper.eq("receiver", selfId).eq("sender", consultationEntity.getSender())
                .or()
                .eq("receiver", consultationEntity.getSender()).eq("sender", selfId);
        List list = new ArrayList();
        list.add("send_time");
        wrapper.orderAsc(list);
        List<ConsultationEntity> consultationEntities = consultationService.selectList(wrapper);
        populateAvatar(consultationEntities);
        return R.ok().put("data", consultationEntities);
    }

    /**
     * 更新已读状态
     */
    @PostMapping("/update")
    public R update(@RequestBody ConsultationEntity consultationEntity, HttpServletRequest request) {
        Long selfId = (Long) request.getSession().getAttribute("userId");
        Wrapper<ConsultationEntity> wrapper = new EntityWrapper<ConsultationEntity>();
        wrapper.eq("sender", consultationEntity.getSender());
        wrapper.eq("receiver", selfId);
        wrapper.eq("read_status", 0);
        consultationEntity.setReadStatus(1);
        consultationService.update(consultationEntity, wrapper);
        return R.ok();
    }

}

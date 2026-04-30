package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscusschongwuguanliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscusschongwuguanliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscusschongwuguanliView;


/**
 * 宠物管理评论
 */
public interface DiscusschongwuguanliService extends IService<DiscusschongwuguanliEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DiscusschongwuguanliVO> selectListVO(Wrapper<DiscusschongwuguanliEntity> wrapper);

    DiscusschongwuguanliVO selectVO(@Param("ew") Wrapper<DiscusschongwuguanliEntity> wrapper);

    List<DiscusschongwuguanliView> selectListView(Wrapper<DiscusschongwuguanliEntity> wrapper);

    DiscusschongwuguanliView selectView(@Param("ew") Wrapper<DiscusschongwuguanliEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusschongwuguanliEntity> wrapper);


}


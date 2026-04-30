package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussxuexiguanliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussxuexiguanliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussxuexiguanliView;


/**
 * 学习管理评论
 */
public interface DiscussxuexiguanliService extends IService<DiscussxuexiguanliEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DiscussxuexiguanliVO> selectListVO(Wrapper<DiscussxuexiguanliEntity> wrapper);

    DiscussxuexiguanliVO selectVO(@Param("ew") Wrapper<DiscussxuexiguanliEntity> wrapper);

    List<DiscussxuexiguanliView> selectListView(Wrapper<DiscussxuexiguanliEntity> wrapper);

    DiscussxuexiguanliView selectView(@Param("ew") Wrapper<DiscussxuexiguanliEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussxuexiguanliEntity> wrapper);


}


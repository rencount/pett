package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscusszixunEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscusszixunVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscusszixunView;


/**
 * 咨询评论
 */
public interface DiscusszixunService extends IService<DiscusszixunEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DiscusszixunVO> selectListVO(Wrapper<DiscusszixunEntity> wrapper);

    DiscusszixunVO selectVO(@Param("ew") Wrapper<DiscusszixunEntity> wrapper);

    List<DiscusszixunView> selectListView(Wrapper<DiscusszixunEntity> wrapper);

    DiscusszixunView selectView(@Param("ew") Wrapper<DiscusszixunEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusszixunEntity> wrapper);


}


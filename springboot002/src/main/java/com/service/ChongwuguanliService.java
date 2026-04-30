package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ChongwuguanliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ChongwuguanliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ChongwuguanliView;


/**
 * 宠物管理
 */
public interface ChongwuguanliService extends IService<ChongwuguanliEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ChongwuguanliVO> selectListVO(Wrapper<ChongwuguanliEntity> wrapper);

    ChongwuguanliVO selectVO(@Param("ew") Wrapper<ChongwuguanliEntity> wrapper);

    List<ChongwuguanliView> selectListView(Wrapper<ChongwuguanliEntity> wrapper);

    ChongwuguanliView selectView(@Param("ew") Wrapper<ChongwuguanliEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<ChongwuguanliEntity> wrapper);


    Map<String, Double> recommend(Long userId, Integer num);
}


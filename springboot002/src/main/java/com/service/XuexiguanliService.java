package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XuexiguanliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.XuexiguanliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.XuexiguanliView;


/**
 * 学习管理
 */
public interface XuexiguanliService extends IService<XuexiguanliEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<XuexiguanliVO> selectListVO(Wrapper<XuexiguanliEntity> wrapper);

    XuexiguanliVO selectVO(@Param("ew") Wrapper<XuexiguanliEntity> wrapper);

    List<XuexiguanliView> selectListView(Wrapper<XuexiguanliEntity> wrapper);

    XuexiguanliView selectView(@Param("ew") Wrapper<XuexiguanliEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<XuexiguanliEntity> wrapper);


    Map<String, Double> recommend(Long userId, Integer num);
}


package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ChongwuyishengEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ChongwuyishengVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ChongwuyishengView;


/**
 * 宠物医生
 */
public interface ChongwuyishengService extends IService<ChongwuyishengEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ChongwuyishengVO> selectListVO(Wrapper<ChongwuyishengEntity> wrapper);

    ChongwuyishengVO selectVO(@Param("ew") Wrapper<ChongwuyishengEntity> wrapper);

    List<ChongwuyishengView> selectListView(Wrapper<ChongwuyishengEntity> wrapper);

    ChongwuyishengView selectView(@Param("ew") Wrapper<ChongwuyishengEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<ChongwuyishengEntity> wrapper);


}


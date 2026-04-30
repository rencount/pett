package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZhenduanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhenduanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhenduanView;


/**
 * 诊断
 */
public interface ZhenduanService extends IService<ZhenduanEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ZhenduanVO> selectListVO(Wrapper<ZhenduanEntity> wrapper);

    ZhenduanVO selectVO(@Param("ew") Wrapper<ZhenduanEntity> wrapper);

    List<ZhenduanView> selectListView(Wrapper<ZhenduanEntity> wrapper);

    ZhenduanView selectView(@Param("ew") Wrapper<ZhenduanEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<ZhenduanEntity> wrapper);


}


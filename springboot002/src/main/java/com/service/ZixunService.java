package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZixunEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZixunVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZixunView;


/**
 * 咨询
 */
public interface ZixunService extends IService<ZixunEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ZixunVO> selectListVO(Wrapper<ZixunEntity> wrapper);

    ZixunVO selectVO(@Param("ew") Wrapper<ZixunEntity> wrapper);

    List<ZixunView> selectListView(Wrapper<ZixunEntity> wrapper);

    ZixunView selectView(@Param("ew") Wrapper<ZixunEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<ZixunEntity> wrapper);


}


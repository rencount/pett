package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JibingshangchuanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JibingshangchuanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JibingshangchuanView;


/**
 * 疾病上传
 */
public interface JibingshangchuanService extends IService<JibingshangchuanEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<JibingshangchuanVO> selectListVO(Wrapper<JibingshangchuanEntity> wrapper);

    JibingshangchuanVO selectVO(@Param("ew") Wrapper<JibingshangchuanEntity> wrapper);

    List<JibingshangchuanView> selectListView(Wrapper<JibingshangchuanEntity> wrapper);

    JibingshangchuanView selectView(@Param("ew") Wrapper<JibingshangchuanEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<JibingshangchuanEntity> wrapper);


}


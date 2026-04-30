package com.dao;

import com.entity.JibingshangchuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JibingshangchuanVO;
import com.entity.view.JibingshangchuanView;


/**
 * 疾病上传
 *
 * @author
 * @email
 * @date 2021-03-02 11:45:35
 */
public interface JibingshangchuanDao extends BaseMapper<JibingshangchuanEntity> {

    List<JibingshangchuanVO> selectListVO(@Param("ew") Wrapper<JibingshangchuanEntity> wrapper);

        JibingshangchuanVO selectVO(@Param("ew") Wrapper<JibingshangchuanEntity> wrapper);

    List<JibingshangchuanView> selectListView(@Param("ew") Wrapper<JibingshangchuanEntity> wrapper);

    List<JibingshangchuanView> selectListView(Pagination page, @Param("ew") Wrapper<JibingshangchuanEntity> wrapper);

        JibingshangchuanView selectView(@Param("ew") Wrapper<JibingshangchuanEntity> wrapper);
}

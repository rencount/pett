package com.dao;

import com.entity.ChongwuyishengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ChongwuyishengVO;
import com.entity.view.ChongwuyishengView;


/**
 * 宠物医生
 *
 * @author
 * @email
 * @date 2021-03-02 11:45:35
 */
public interface ChongwuyishengDao extends BaseMapper<ChongwuyishengEntity> {

    List<ChongwuyishengVO> selectListVO(@Param("ew") Wrapper<ChongwuyishengEntity> wrapper);

        ChongwuyishengVO selectVO(@Param("ew") Wrapper<ChongwuyishengEntity> wrapper);

    List<ChongwuyishengView> selectListView(@Param("ew") Wrapper<ChongwuyishengEntity> wrapper);

    List<ChongwuyishengView> selectListView(Pagination page, @Param("ew") Wrapper<ChongwuyishengEntity> wrapper);

        ChongwuyishengView selectView(@Param("ew") Wrapper<ChongwuyishengEntity> wrapper);
}

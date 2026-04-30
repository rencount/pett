package com.dao;

import com.entity.ChongwuguanliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ChongwuguanliVO;
import com.entity.view.ChongwuguanliView;


/**
 * 宠物管理
 *
 * @author
 * @email
 * @date 2021-03-02 11:45:35
 */
public interface ChongwuguanliDao extends BaseMapper<ChongwuguanliEntity> {

    List<ChongwuguanliVO> selectListVO(@Param("ew") Wrapper<ChongwuguanliEntity> wrapper);

        ChongwuguanliVO selectVO(@Param("ew") Wrapper<ChongwuguanliEntity> wrapper);

    List<ChongwuguanliView> selectListView(@Param("ew") Wrapper<ChongwuguanliEntity> wrapper);

    List<ChongwuguanliView> selectListView(Pagination page, @Param("ew") Wrapper<ChongwuguanliEntity> wrapper);

        ChongwuguanliView selectView(@Param("ew") Wrapper<ChongwuguanliEntity> wrapper);
}

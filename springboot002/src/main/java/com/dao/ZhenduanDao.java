package com.dao;

import com.entity.ZhenduanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZhenduanVO;
import com.entity.view.ZhenduanView;


/**
 * 诊断
 *
 * @author
 * @email
 * @date 2021-03-02 11:45:35
 */
public interface ZhenduanDao extends BaseMapper<ZhenduanEntity> {

    List<ZhenduanVO> selectListVO(@Param("ew") Wrapper<ZhenduanEntity> wrapper);

        ZhenduanVO selectVO(@Param("ew") Wrapper<ZhenduanEntity> wrapper);

    List<ZhenduanView> selectListView(@Param("ew") Wrapper<ZhenduanEntity> wrapper);

    List<ZhenduanView> selectListView(Pagination page, @Param("ew") Wrapper<ZhenduanEntity> wrapper);

        ZhenduanView selectView(@Param("ew") Wrapper<ZhenduanEntity> wrapper);
}

package com.dao;

import com.entity.XuexiguanliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.XuexiguanliVO;
import com.entity.view.XuexiguanliView;


/**
 * 学习管理
 *
 * @author
 * @email
 * @date 2021-03-02 11:45:35
 */
public interface XuexiguanliDao extends BaseMapper<XuexiguanliEntity> {

    List<XuexiguanliVO> selectListVO(@Param("ew") Wrapper<XuexiguanliEntity> wrapper);

        XuexiguanliVO selectVO(@Param("ew") Wrapper<XuexiguanliEntity> wrapper);

    List<XuexiguanliView> selectListView(@Param("ew") Wrapper<XuexiguanliEntity> wrapper);

    List<XuexiguanliView> selectListView(Pagination page, @Param("ew") Wrapper<XuexiguanliEntity> wrapper);

        XuexiguanliView selectView(@Param("ew") Wrapper<XuexiguanliEntity> wrapper);
}

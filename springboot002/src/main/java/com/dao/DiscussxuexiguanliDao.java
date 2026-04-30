package com.dao;

import com.entity.DiscussxuexiguanliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussxuexiguanliVO;
import com.entity.view.DiscussxuexiguanliView;


/**
 * 学习管理评论
 *
 * @author
 * @email
 * @date 2021-03-02 11:45:35
 */
public interface DiscussxuexiguanliDao extends BaseMapper<DiscussxuexiguanliEntity> {

    List<DiscussxuexiguanliVO> selectListVO(@Param("ew") Wrapper<DiscussxuexiguanliEntity> wrapper);

        DiscussxuexiguanliVO selectVO(@Param("ew") Wrapper<DiscussxuexiguanliEntity> wrapper);

    List<DiscussxuexiguanliView> selectListView(@Param("ew") Wrapper<DiscussxuexiguanliEntity> wrapper);

    List<DiscussxuexiguanliView> selectListView(Pagination page, @Param("ew") Wrapper<DiscussxuexiguanliEntity> wrapper);

        DiscussxuexiguanliView selectView(@Param("ew") Wrapper<DiscussxuexiguanliEntity> wrapper);
}

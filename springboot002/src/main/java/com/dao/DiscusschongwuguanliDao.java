package com.dao;

import com.entity.DiscusschongwuguanliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscusschongwuguanliVO;
import com.entity.view.DiscusschongwuguanliView;


/**
 * 宠物管理评论
 *
 * @author
 * @email
 * @date 2021-03-02 11:45:35
 */
public interface DiscusschongwuguanliDao extends BaseMapper<DiscusschongwuguanliEntity> {

    List<DiscusschongwuguanliVO> selectListVO(@Param("ew") Wrapper<DiscusschongwuguanliEntity> wrapper);

        DiscusschongwuguanliVO selectVO(@Param("ew") Wrapper<DiscusschongwuguanliEntity> wrapper);

    List<DiscusschongwuguanliView> selectListView(@Param("ew") Wrapper<DiscusschongwuguanliEntity> wrapper);

    List<DiscusschongwuguanliView> selectListView(Pagination page, @Param("ew") Wrapper<DiscusschongwuguanliEntity> wrapper);

        DiscusschongwuguanliView selectView(@Param("ew") Wrapper<DiscusschongwuguanliEntity> wrapper);
}

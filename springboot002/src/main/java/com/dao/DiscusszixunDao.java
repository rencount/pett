package com.dao;

import com.entity.DiscusszixunEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscusszixunVO;
import com.entity.view.DiscusszixunView;


/**
 * 咨询评论
 *
 * @author
 * @email
 * @date 2021-03-02 11:45:35
 */
public interface DiscusszixunDao extends BaseMapper<DiscusszixunEntity> {

    List<DiscusszixunVO> selectListVO(@Param("ew") Wrapper<DiscusszixunEntity> wrapper);

        DiscusszixunVO selectVO(@Param("ew") Wrapper<DiscusszixunEntity> wrapper);

    List<DiscusszixunView> selectListView(@Param("ew") Wrapper<DiscusszixunEntity> wrapper);

    List<DiscusszixunView> selectListView(Pagination page, @Param("ew") Wrapper<DiscusszixunEntity> wrapper);

        DiscusszixunView selectView(@Param("ew") Wrapper<DiscusszixunEntity> wrapper);
}

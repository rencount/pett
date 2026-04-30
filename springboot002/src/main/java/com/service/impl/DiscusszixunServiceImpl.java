package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DiscusszixunDao;
import com.entity.DiscusszixunEntity;
import com.service.DiscusszixunService;
import com.entity.vo.DiscusszixunVO;
import com.entity.view.DiscusszixunView;

@Service("discusszixunService")
public class DiscusszixunServiceImpl extends ServiceImpl<DiscusszixunDao, DiscusszixunEntity> implements DiscusszixunService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusszixunEntity> page = this.selectPage(
                new Query<DiscusszixunEntity>(params).getPage(),
                new EntityWrapper<DiscusszixunEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusszixunEntity> wrapper) {
        Page<DiscusszixunView> page = new Query<DiscusszixunView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<DiscusszixunVO> selectListVO(Wrapper<DiscusszixunEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public DiscusszixunVO selectVO(Wrapper<DiscusszixunEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<DiscusszixunView> selectListView(Wrapper<DiscusszixunEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public DiscusszixunView selectView(Wrapper<DiscusszixunEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}

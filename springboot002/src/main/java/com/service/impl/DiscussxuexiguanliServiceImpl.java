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


import com.dao.DiscussxuexiguanliDao;
import com.entity.DiscussxuexiguanliEntity;
import com.service.DiscussxuexiguanliService;
import com.entity.vo.DiscussxuexiguanliVO;
import com.entity.view.DiscussxuexiguanliView;

@Service("discussxuexiguanliService")
public class DiscussxuexiguanliServiceImpl extends ServiceImpl<DiscussxuexiguanliDao, DiscussxuexiguanliEntity> implements DiscussxuexiguanliService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussxuexiguanliEntity> page = this.selectPage(
                new Query<DiscussxuexiguanliEntity>(params).getPage(),
                new EntityWrapper<DiscussxuexiguanliEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussxuexiguanliEntity> wrapper) {
        Page<DiscussxuexiguanliView> page = new Query<DiscussxuexiguanliView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<DiscussxuexiguanliVO> selectListVO(Wrapper<DiscussxuexiguanliEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public DiscussxuexiguanliVO selectVO(Wrapper<DiscussxuexiguanliEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<DiscussxuexiguanliView> selectListView(Wrapper<DiscussxuexiguanliEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public DiscussxuexiguanliView selectView(Wrapper<DiscussxuexiguanliEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}

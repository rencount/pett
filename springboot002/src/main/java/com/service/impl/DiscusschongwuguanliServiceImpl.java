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


import com.dao.DiscusschongwuguanliDao;
import com.entity.DiscusschongwuguanliEntity;
import com.service.DiscusschongwuguanliService;
import com.entity.vo.DiscusschongwuguanliVO;
import com.entity.view.DiscusschongwuguanliView;

@Service("discusschongwuguanliService")
public class DiscusschongwuguanliServiceImpl extends ServiceImpl<DiscusschongwuguanliDao, DiscusschongwuguanliEntity> implements DiscusschongwuguanliService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusschongwuguanliEntity> page = this.selectPage(
                new Query<DiscusschongwuguanliEntity>(params).getPage(),
                new EntityWrapper<DiscusschongwuguanliEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusschongwuguanliEntity> wrapper) {
        Page<DiscusschongwuguanliView> page = new Query<DiscusschongwuguanliView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<DiscusschongwuguanliVO> selectListVO(Wrapper<DiscusschongwuguanliEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public DiscusschongwuguanliVO selectVO(Wrapper<DiscusschongwuguanliEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<DiscusschongwuguanliView> selectListView(Wrapper<DiscusschongwuguanliEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public DiscusschongwuguanliView selectView(Wrapper<DiscusschongwuguanliEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}

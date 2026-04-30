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


import com.dao.ChongwuyishengDao;
import com.entity.ChongwuyishengEntity;
import com.service.ChongwuyishengService;
import com.entity.vo.ChongwuyishengVO;
import com.entity.view.ChongwuyishengView;

@Service("chongwuyishengService")
public class ChongwuyishengServiceImpl extends ServiceImpl<ChongwuyishengDao, ChongwuyishengEntity> implements ChongwuyishengService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChongwuyishengEntity> page = this.selectPage(
                new Query<ChongwuyishengEntity>(params).getPage(),
                new EntityWrapper<ChongwuyishengEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ChongwuyishengEntity> wrapper) {
        Page<ChongwuyishengView> page = new Query<ChongwuyishengView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<ChongwuyishengVO> selectListVO(Wrapper<ChongwuyishengEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public ChongwuyishengVO selectVO(Wrapper<ChongwuyishengEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<ChongwuyishengView> selectListView(Wrapper<ChongwuyishengEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ChongwuyishengView selectView(Wrapper<ChongwuyishengEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}

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


import com.dao.JibingshangchuanDao;
import com.entity.JibingshangchuanEntity;
import com.service.JibingshangchuanService;
import com.entity.vo.JibingshangchuanVO;
import com.entity.view.JibingshangchuanView;

@Service("jibingshangchuanService")
public class JibingshangchuanServiceImpl extends ServiceImpl<JibingshangchuanDao, JibingshangchuanEntity> implements JibingshangchuanService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JibingshangchuanEntity> page = this.selectPage(
                new Query<JibingshangchuanEntity>(params).getPage(),
                new EntityWrapper<JibingshangchuanEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<JibingshangchuanEntity> wrapper) {
        Page<JibingshangchuanView> page = new Query<JibingshangchuanView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<JibingshangchuanVO> selectListVO(Wrapper<JibingshangchuanEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public JibingshangchuanVO selectVO(Wrapper<JibingshangchuanEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<JibingshangchuanView> selectListView(Wrapper<JibingshangchuanEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public JibingshangchuanView selectView(Wrapper<JibingshangchuanEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}

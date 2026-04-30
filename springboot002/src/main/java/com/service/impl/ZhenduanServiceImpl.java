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


import com.dao.ZhenduanDao;
import com.entity.ZhenduanEntity;
import com.service.ZhenduanService;
import com.entity.vo.ZhenduanVO;
import com.entity.view.ZhenduanView;

@Service("zhenduanService")
public class ZhenduanServiceImpl extends ServiceImpl<ZhenduanDao, ZhenduanEntity> implements ZhenduanService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhenduanEntity> page = this.selectPage(
                new Query<ZhenduanEntity>(params).getPage(),
                new EntityWrapper<ZhenduanEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhenduanEntity> wrapper) {
        Page<ZhenduanView> page = new Query<ZhenduanView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<ZhenduanVO> selectListVO(Wrapper<ZhenduanEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public ZhenduanVO selectVO(Wrapper<ZhenduanEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<ZhenduanView> selectListView(Wrapper<ZhenduanEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ZhenduanView selectView(Wrapper<ZhenduanEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}

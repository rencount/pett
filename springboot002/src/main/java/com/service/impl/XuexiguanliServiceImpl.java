package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.StoreupService;
import com.utils.UserBasedCollaborativeFiltering;
import java.util.*;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.XuexiguanliDao;
import com.entity.XuexiguanliEntity;
import com.service.XuexiguanliService;
import com.entity.vo.XuexiguanliVO;
import com.entity.view.XuexiguanliView;

@Service("xuexiguanliService")
public class XuexiguanliServiceImpl extends ServiceImpl<XuexiguanliDao, XuexiguanliEntity> implements XuexiguanliService {

    @Autowired
    private StoreupService storeupService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XuexiguanliEntity> page = this.selectPage(
                new Query<XuexiguanliEntity>(params).getPage(),
                new EntityWrapper<XuexiguanliEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<XuexiguanliEntity> wrapper) {
        Page<XuexiguanliView> page = new Query<XuexiguanliView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<XuexiguanliVO> selectListVO(Wrapper<XuexiguanliEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public XuexiguanliVO selectVO(Wrapper<XuexiguanliEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<XuexiguanliView> selectListView(Wrapper<XuexiguanliEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public XuexiguanliView selectView(Wrapper<XuexiguanliEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

    @Override
    public Map<String, Double> recommend(Long userId, Integer num) {
        // 创建用户-物品收藏矩阵
        Map<String, Set<String>> userItemCollections = new HashMap<>();
        List<Map<String, String>> maps = storeupService.getCollected();
        for (Map<String, String> map : maps) {
            userItemCollections.put(String.valueOf(map.get("userid")), new HashSet<>(Arrays.asList(map.get("items").split(","))));
        }
        UserBasedCollaborativeFiltering cf = new UserBasedCollaborativeFiltering(userItemCollections);
        // 为目标用户推荐物品
        Map<String, Double> recommendations = cf.recommendItems(String.valueOf(userId), num);
        // 输出推荐结果
        System.err.println("基于用户收藏的推荐 用户ID:" + userId);
        for (Map.Entry<String, Double> entry : recommendations.entrySet()) {
            System.err.println("商品ID: " + entry.getKey() + ", 分数: " + entry.getValue());
        }
        return recommendations;
    }
}

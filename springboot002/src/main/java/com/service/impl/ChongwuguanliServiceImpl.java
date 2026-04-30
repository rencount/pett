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


import com.dao.ChongwuguanliDao;
import com.entity.ChongwuguanliEntity;
import com.service.ChongwuguanliService;
import com.entity.vo.ChongwuguanliVO;
import com.entity.view.ChongwuguanliView;

@Service("chongwuguanliService")
public class ChongwuguanliServiceImpl extends ServiceImpl<ChongwuguanliDao, ChongwuguanliEntity> implements ChongwuguanliService {

    @Autowired
    private StoreupService storeupService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChongwuguanliEntity> page = this.selectPage(
                new Query<ChongwuguanliEntity>(params).getPage(),
                new EntityWrapper<ChongwuguanliEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ChongwuguanliEntity> wrapper) {
        Page<ChongwuguanliView> page = new Query<ChongwuguanliView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<ChongwuguanliVO> selectListVO(Wrapper<ChongwuguanliEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public ChongwuguanliVO selectVO(Wrapper<ChongwuguanliEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<ChongwuguanliView> selectListView(Wrapper<ChongwuguanliEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ChongwuguanliView selectView(Wrapper<ChongwuguanliEntity> wrapper) {
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

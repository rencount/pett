package com.entity.view;

import com.entity.DiscussxuexiguanliEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;


/**
 * 学习管理评论
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author
 * @email
 * @date 2021-01-07 10:40:53
 */
@TableName("discussxuexiguanli")
public class DiscussxuexiguanliView  extends DiscussxuexiguanliEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public DiscussxuexiguanliView(){
    }

    public DiscussxuexiguanliView(DiscussxuexiguanliEntity discussxuexiguanliEntity){
        try {
            BeanUtils.copyProperties(this, discussxuexiguanliEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

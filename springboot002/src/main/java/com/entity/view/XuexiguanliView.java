package com.entity.view;

import com.entity.XuexiguanliEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;


/**
 * 学习管理
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author
 * @email
 * @date 2021-01-07 10:40:53
 */
@TableName("xuexiguanli")
public class XuexiguanliView  extends XuexiguanliEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public XuexiguanliView(){
    }

    public XuexiguanliView(XuexiguanliEntity xuexiguanliEntity){
        try {
            BeanUtils.copyProperties(this, xuexiguanliEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

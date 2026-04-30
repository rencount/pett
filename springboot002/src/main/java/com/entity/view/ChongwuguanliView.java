package com.entity.view;

import com.entity.ChongwuguanliEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;


/**
 * 宠物管理
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author
 * @email
 * @date 2021-01-07 10:40:53
 */
@TableName("chongwuguanli")
public class ChongwuguanliView  extends ChongwuguanliEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public ChongwuguanliView(){
    }

    public ChongwuguanliView(ChongwuguanliEntity chongwuguanliEntity){
        try {
            BeanUtils.copyProperties(this, chongwuguanliEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

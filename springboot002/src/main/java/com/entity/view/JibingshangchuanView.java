package com.entity.view;

import com.entity.JibingshangchuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;


/**
 * 疾病上传
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author
 * @email
 * @date 2021-01-07 10:40:53
 */
@TableName("jibingshangchuan")
public class JibingshangchuanView  extends JibingshangchuanEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public JibingshangchuanView(){
    }

    public JibingshangchuanView(JibingshangchuanEntity jibingshangchuanEntity){
        try {
            BeanUtils.copyProperties(this, jibingshangchuanEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

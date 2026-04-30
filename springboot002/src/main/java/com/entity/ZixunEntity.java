package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 咨询
 * 数据库通用操作实体类（普通增删改查）
 */
@TableName("zixun")
public class ZixunEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public ZixunEntity() {

    }

    public ZixunEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 /** 主键 */
    private Long id;

 /** 创建时间 */
@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
@DateTimeFormat
    private Timestamp addtime;

 /** 宠物医生 */
    private String chongwuyisheng;

 /** 头像 */
    private String touxiang;

 /** 性别 */
    private String xingbie;

 /** 联系方式 */
    private String lianxifangshi;

 /** 详情 */
    private String xiangqing;

 /** 用户id */
    private Long userid;

    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getId()
    {
        return id;
    }
    public void setAddtime(Timestamp addtime)
    {
        this.addtime = addtime;
    }
    public Timestamp getAddtime()
    {
        return addtime;
    }
    public void setChongwuyisheng(String chongwuyisheng)
    {
        this.chongwuyisheng = chongwuyisheng;
    }
    public String getChongwuyisheng()
    {
        return chongwuyisheng;
    }
    public void setTouxiang(String touxiang)
    {
        this.touxiang = touxiang;
    }
    public String getTouxiang()
    {
        return touxiang;
    }
    public void setXingbie(String xingbie)
    {
        this.xingbie = xingbie;
    }
    public String getXingbie()
    {
        return xingbie;
    }
    public void setLianxifangshi(String lianxifangshi)
    {
        this.lianxifangshi = lianxifangshi;
    }
    public String getLianxifangshi()
    {
        return lianxifangshi;
    }
    public void setXiangqing(String xiangqing)
    {
        this.xiangqing = xiangqing;
    }
    public String getXiangqing()
    {
        return xiangqing;
    }
    public void setUserid(Long userid)
    {
        this.userid = userid;
    }
    public Long getUserid()
    {
        return userid;
    }

}

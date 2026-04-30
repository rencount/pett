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
 * 宠物管理
 * 数据库通用操作实体类（普通增删改查）
 */
@TableName("chongwuguanli")
public class ChongwuguanliEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public ChongwuguanliEntity() {

    }

    public ChongwuguanliEntity(T t) {
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

 /** 宠物名称 */
    private String chongwumingcheng;

 /** 图片 */
    private String tupian;

 /** 性别 */
    private String xingbie;

 /** 喜好 */
    private String xihao;

 /** 详情 */
    private String xiangqing;

 /** 用户id */
    private Long userid;

 /** 最近点击时间 */
@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
@DateTimeFormat
    private Timestamp clicktime;

 /** 点击次数 */
    private Long clicknum;

 /** 赞 */
    private Long thumbsupnum;

 /** 踩 */
    private Long crazilynum;

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
    public void setChongwumingcheng(String chongwumingcheng)
    {
        this.chongwumingcheng = chongwumingcheng;
    }
    public String getChongwumingcheng()
    {
        return chongwumingcheng;
    }
    public void setTupian(String tupian)
    {
        this.tupian = tupian;
    }
    public String getTupian()
    {
        return tupian;
    }
    public void setXingbie(String xingbie)
    {
        this.xingbie = xingbie;
    }
    public String getXingbie()
    {
        return xingbie;
    }
    public void setXihao(String xihao)
    {
        this.xihao = xihao;
    }
    public String getXihao()
    {
        return xihao;
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
    public void setClicktime(Timestamp clicktime)
    {
        this.clicktime = clicktime;
    }
    public Timestamp getClicktime()
    {
        return clicktime;
    }
    public void setClicknum(Long clicknum)
    {
        this.clicknum = clicknum;
    }
    public Long getClicknum()
    {
        return clicknum;
    }
    public void setThumbsupnum(Long thumbsupnum)
    {
        this.thumbsupnum = thumbsupnum;
    }
    public Long getThumbsupnum()
    {
        return thumbsupnum;
    }
    public void setCrazilynum(Long crazilynum)
    {
        this.crazilynum = crazilynum;
    }
    public Long getCrazilynum()
    {
        return crazilynum;
    }

}

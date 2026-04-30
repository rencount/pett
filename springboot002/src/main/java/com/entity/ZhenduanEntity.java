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
 * 诊断
 * 数据库通用操作实体类（普通增删改查）
 */
@TableName("zhenduan")
public class ZhenduanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public ZhenduanEntity() {

    }

    public ZhenduanEntity(T t) {
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

 /** 症状 */
    private String zhengzhuang;

 /** 治疗建议 */
    private String zhiliaojianyi;

 /** 诊断时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date zhenduanshijian;

 /** 诊断人 */
    private String zhenduanren;

 /** 联系方式 */
    private String lianxifangshi;

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
    public void setChongwumingcheng(String chongwumingcheng)
    {
        this.chongwumingcheng = chongwumingcheng;
    }
    public String getChongwumingcheng()
    {
        return chongwumingcheng;
    }
    public void setZhengzhuang(String zhengzhuang)
    {
        this.zhengzhuang = zhengzhuang;
    }
    public String getZhengzhuang()
    {
        return zhengzhuang;
    }
    public void setZhiliaojianyi(String zhiliaojianyi)
    {
        this.zhiliaojianyi = zhiliaojianyi;
    }
    public String getZhiliaojianyi()
    {
        return zhiliaojianyi;
    }
    public void setZhenduanshijian(Date zhenduanshijian)
    {
        this.zhenduanshijian = zhenduanshijian;
    }
    public Date getZhenduanshijian()
    {
        return zhenduanshijian;
    }
    public void setZhenduanren(String zhenduanren)
    {
        this.zhenduanren = zhenduanren;
    }
    public String getZhenduanren()
    {
        return zhenduanren;
    }
    public void setLianxifangshi(String lianxifangshi)
    {
        this.lianxifangshi = lianxifangshi;
    }
    public String getLianxifangshi()
    {
        return lianxifangshi;
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

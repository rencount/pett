package com.entity.vo;

import com.entity.ZhenduanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import java.sql.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 诊断
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
public class ZhenduanVO  implements Serializable {
    private static final long serialVersionUID = 1L;
                        /** 主键 */
                    private Long id;

                                /** 创建时间 */
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

package com.entity.vo;

import com.entity.ChongwuyishengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import java.sql.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 宠物医生
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
public class ChongwuyishengVO  implements Serializable {
    private static final long serialVersionUID = 1L;
                        /** 主键 */
                    private Long id;

                                /** 创建时间 */
                                                                                                private Timestamp addtime;

                                /** 账号 */
                                                                                                private String zhanghao;

                                /** 头像 */
                                                                                                private String touxiang;

                                /** 密码 */
                                                                                                private String mima;

                                /** 性别 */
                                                                                                private String xingbie;

                                /** 联系方式 */
                                                                                                private String lianxifangshi;

                                /** 昵称 */
                                                                                                private String nicheng;

                                /** 详情 */
                                                                                                private String xiangqing;

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
                                                        public void setZhanghao(String zhanghao)
            {
                this.zhanghao = zhanghao;
            }

            public String getZhanghao()
            {
                return zhanghao;
            }
                                                        public void setTouxiang(String touxiang)
            {
                this.touxiang = touxiang;
            }

            public String getTouxiang()
            {
                return touxiang;
            }
                                                        public void setMima(String mima)
            {
                this.mima = mima;
            }

            public String getMima()
            {
                return mima;
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
                                                        public void setNicheng(String nicheng)
            {
                this.nicheng = nicheng;
            }
            public String getNicheng()
            {
                return nicheng;
            }

            public void setXiangqing(String xiangqing)
            {
                this.xiangqing = xiangqing;
            }
            public String getXiangqing()
            {
                return xiangqing;
            }
            
    }

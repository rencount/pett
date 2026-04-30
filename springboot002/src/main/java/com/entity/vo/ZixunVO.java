package com.entity.vo;

import com.entity.ZixunEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import java.sql.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 咨询
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
public class ZixunVO  implements Serializable {
    private static final long serialVersionUID = 1L;
                        /** 主键 */
                    private Long id;

                                /** 创建时间 */
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

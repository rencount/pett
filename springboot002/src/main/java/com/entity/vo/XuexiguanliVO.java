package com.entity.vo;

import com.entity.XuexiguanliEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import java.sql.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 学习管理
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
public class XuexiguanliVO  implements Serializable {
    private static final long serialVersionUID = 1L;
                        /** 主键 */
                    private Long id;

                                /** 创建时间 */
                                                                                                private Timestamp addtime;

                                /** 标题 */
                                                                                                private String biaoti;

                                /** 图片 */
                                                                                                private String tupian;

                                /** 详情 */
                                                                                                private String xiangqing;

                                /** 上传时间 */
                                                                            @JsonFormat(pattern = "yyyy-MM-dd")
                                    private Date shangchuanshijian;

                                /** 最近点击时间 */
                                                                                                private Timestamp clicktime;

                                /** 点击次数 */
                                                                                                private Long clicknum;

                                /** 赞 */
                                                                                                private Long thumbsupnum;

                                /** 踩 */
                                                                                                private Long crazilynum;

                                /** 是否审核 */
                                                                                                private String sfsh;

                                /** 审核回复 */
                                                                                                private String shhf;

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
                                                        public void setBiaoti(String biaoti)
            {
                this.biaoti = biaoti;
            }

            public String getBiaoti()
            {
                return biaoti;
            }
                                                        public void setTupian(String tupian)
            {
                this.tupian = tupian;
            }

            public String getTupian()
            {
                return tupian;
            }
                                                        public void setXiangqing(String xiangqing)
            {
                this.xiangqing = xiangqing;
            }

            public String getXiangqing()
            {
                return xiangqing;
            }
                                                        public void setShangchuanshijian(Date shangchuanshijian)
            {
                this.shangchuanshijian = shangchuanshijian;
            }

            public Date getShangchuanshijian()
            {
                return shangchuanshijian;
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
                                                        public void setSfsh(String sfsh)
            {
                this.sfsh = sfsh;
            }

            public String getSfsh()
            {
                return sfsh;
            }
                                                        public void setShhf(String shhf)
            {
                this.shhf = shhf;
            }

            public String getShhf()
            {
                return shhf;
            }
            
    }

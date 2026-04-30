package com.entity.vo;

import com.entity.ChongwuguanliEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import java.sql.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 宠物管理
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
public class ChongwuguanliVO  implements Serializable {
    private static final long serialVersionUID = 1L;
                        /** 主键 */
                    private Long id;

                                /** 创建时间 */
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

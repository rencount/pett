package com.entity.vo;

import com.entity.JibingshangchuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import java.sql.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 疾病上传
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
public class JibingshangchuanVO  implements Serializable {
    private static final long serialVersionUID = 1L;
                        /** 主键 */
                    private Long id;

                                /** 创建时间 */
                                                                                                private Timestamp addtime;

                                /** 宠物名称 */
                                                                                                private String chongwumingcheng;

                                /** 病情描述 */
                                                                                                private String bingqingmiaoshu;

                                /** 图片上传 */
                                                                                                private String tupianshangchuan;

                                /** 补充 */
                                                                                                private String buchong;

                                /** 发布时间 */
                                                                            @JsonFormat(pattern = "yyyy-MM-dd")
                                    private Date fabushijian;

                                /** 用户id */
                                                                                                private Long userid;

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
                                                        public void setChongwumingcheng(String chongwumingcheng)
            {
                this.chongwumingcheng = chongwumingcheng;
            }

            public String getChongwumingcheng()
            {
                return chongwumingcheng;
            }
                                                        public void setBingqingmiaoshu(String bingqingmiaoshu)
            {
                this.bingqingmiaoshu = bingqingmiaoshu;
            }

            public String getBingqingmiaoshu()
            {
                return bingqingmiaoshu;
            }
                                                        public void setTupianshangchuan(String tupianshangchuan)
            {
                this.tupianshangchuan = tupianshangchuan;
            }

            public String getTupianshangchuan()
            {
                return tupianshangchuan;
            }
                                                        public void setBuchong(String buchong)
            {
                this.buchong = buchong;
            }

            public String getBuchong()
            {
                return buchong;
            }
                                                        public void setFabushijian(Date fabushijian)
            {
                this.fabushijian = fabushijian;
            }

            public Date getFabushijian()
            {
                return fabushijian;
            }
                                                        public void setUserid(Long userid)
            {
                this.userid = userid;
            }

            public Long getUserid()
            {
                return userid;
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

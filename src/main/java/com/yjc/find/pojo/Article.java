package com.yjc.find.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yjc.find.base.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_article")
public class Article extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String articleName;
    /**
     * 1lost  2find
     */
    private Integer lostOrFind;

    @DateTimeFormat(pattern = "yyyy-MM-dd")//接收格式
    @JsonFormat(pattern = "yyyy-MM-dd")//返回格式
    private Date lostDate;

    private String content;
    /**
     * 0审核中，1正常，2违规
     */
    private Integer status;
    /**
     * 0未找到 1找到 默认为0
     */
    private Integer isFind;

    private String linkMan;

    private String linkMethod;

    private String reward;

    private Long articleCategoryId;

    private Long userId;

    private Long areaId;

    private Long schoolId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//接收格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//返回格式
    private Date updateDate;

    @TableField(exist = false)
    private String articleCategoryName;

    @TableField(exist = false)
    private String areaName;

    @TableField(exist = false)
    private String nickName;

    @TableField(exist = false)
    private String userImgAddr;

    @TableField(exist = false)
    private String schoolName;

    @TableField(exist = false)
    private List<String> articleImgList;
}

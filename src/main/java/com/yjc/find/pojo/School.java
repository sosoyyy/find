package com.yjc.find.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yjc.find.base.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_school")
public class School extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String schoolName;
    private String note;

    private String schoolImgAddr;
    /**
     * 0入驻 1未入驻
     */
    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//接收格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//返回格式
    private Date updateDate;
}

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
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;



    private String email;

    private String username;

    private String password;
    /**
     * 0正常1不可用 默认0
     */
    private Integer userStatus;
    /**
     * 0普通用户1manager 默认0
     */
    private Integer userType;

    private String nickName;

    private String userImgAddr;
    /**
     * 1男2女 默认0
     */
    private Integer sex;

    private Long schoolId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//接收格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//返回格式
    private Date updateDate;
    @TableField(exist = false)
    private String schoolName ;
    @TableField(exist = false)
    private String token;

}

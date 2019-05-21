package com.yjc.find.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yjc.find.base.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_sys_message")
public class SysMessage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long receiveId;

    private Long articleId;

    private  String  title;
    private Integer isRead;

    private String content;

    @TableField(exist = false)
    private String receiveName;

    @TableField(exist = false)
    private String receiveImgAddr;

}

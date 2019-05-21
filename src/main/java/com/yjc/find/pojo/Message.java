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
@TableName("tb_message")
public class Message extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long sendId;

    private Long receiveId;
    /**
     * 0未读 1已读
     */
    private Integer isRead;
    private Long articleId;
    private  String  title;

    private String content;
    @TableField(exist = false)
    private String sendName;
    @TableField(exist = false)
    private String receiveName;
    @TableField(exist = false)
    private String sendImgAddr;
    @TableField(exist = false)
    private String receiveImgAddr;

}

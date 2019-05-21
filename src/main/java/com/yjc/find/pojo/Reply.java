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
@TableName("tb_reply")
public class Reply extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long articleId;

    private Long sendId;

    private String content;

    @TableField(exist = false)
    private String sendName;

    @TableField(exist = false)
    private String sendImg;


}

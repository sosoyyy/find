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
@TableName("tb_thank_letter")
public class ThankLetter extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String title;

    private String content;

    private Long sendId;
    private String status;
    @TableField(exist = false)
    private String sendName;
    @TableField(exist = false)
    private String sendImg;
}

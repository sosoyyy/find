package com.yjc.find.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjc.find.base.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_message_read")
public class MessageRead extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long receiveId;

    private Long messageId;
    /**
     * 0未读 1已读
     */
    private Integer readStatus;

    private Integer deleteTag;
}

package com.yjc.find.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjc.find.base.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_boards")
public class Boards extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String title;

    private String content;

    private String imgAddr;

    private Long schoolId;
    //0不展示1展示
    private Integer status;

}

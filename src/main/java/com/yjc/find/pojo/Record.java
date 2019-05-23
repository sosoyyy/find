package com.yjc.find.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjc.find.base.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_record")
public class Record extends BaseEntity {
    private static final long serialVersionUID = 1L;
    //1全体管理员，2全体用户
    private Integer receiveType;
    private  String  title;
    private String content;
}

package com.yjc.find.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjc.find.base.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_article_img")
public class ArticleImg extends BaseEntity {

    private Long articleId;

    private String articleImgAddr;
}

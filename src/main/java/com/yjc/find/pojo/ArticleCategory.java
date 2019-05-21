package com.yjc.find.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjc.find.base.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_article_category")
public class ArticleCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String articleCategoryName;

}

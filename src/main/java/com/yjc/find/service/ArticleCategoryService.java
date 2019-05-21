package com.yjc.find.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.find.pojo.ArticleCategory;
import org.springframework.stereotype.Service;

@Service
public interface ArticleCategoryService extends IService<ArticleCategory> {
    void saveArticleCategory(ArticleCategory articleCategory);

    void updateCategory(ArticleCategory articleCategory);
}

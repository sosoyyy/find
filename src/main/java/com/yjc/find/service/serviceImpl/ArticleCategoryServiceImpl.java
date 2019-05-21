package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.dao.ArticleCategoryMapper;
import com.yjc.find.pojo.ArticleCategory;
import com.yjc.find.service.ArticleCategoryService;
import com.yjc.find.utils.MyUtil;
import org.springframework.stereotype.Service;

import java.util.Queue;
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper,ArticleCategory> implements ArticleCategoryService {
    @Override
    public void saveArticleCategory(ArticleCategory articleCategory) {
        MyUtil.checkNull(articleCategory);
        MyUtil.checkNull(articleCategory.getArticleCategoryName());
        ArticleCategory queryObj = new ArticleCategory();
        queryObj.setArticleCategoryName(articleCategory.getArticleCategoryName());
        if (this.baseMapper.selectOne(new QueryWrapper<>(queryObj))!=null){
            MyUtil.checkFailed("种类重复");
        }
        this.baseMapper.insert(articleCategory);
    }

    @Override
    public void updateCategory(ArticleCategory articleCategory) {
        MyUtil.checkNull(articleCategory);
        MyUtil.checkNull(articleCategory.getId());
        MyUtil.checkNull(articleCategory.getArticleCategoryName());
        ArticleCategory queryObj = new ArticleCategory();
        queryObj.setArticleCategoryName(articleCategory.getArticleCategoryName());
        if (this.baseMapper.selectOne(new QueryWrapper<>(queryObj))!=null){
            MyUtil.checkFailed("种类重复");
        }
        this.baseMapper.updateById(articleCategory);
    }
}

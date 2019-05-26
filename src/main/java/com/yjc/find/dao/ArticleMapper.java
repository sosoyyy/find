package com.yjc.find.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.pojo.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleMapper extends BaseMapper<Article> {
    Article selectArticle(Long id);

    int selectLostCount(Map<String,Object> params);

    List<Article> selectLostPage(Map<String,Object> params);

    int selectFindCount(Map<String, Object> params);

    List<Article> selectFindPage(Map<String, Object> params);

    List<Article> matchArticle(@Param("article")Article matchArticle);

    int selectArticleCount(Map<String, Object> params);

    List<Article> selectArticlePage(Map<String, Object> params);
}

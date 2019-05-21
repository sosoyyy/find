package com.yjc.find.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.pojo.Article;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ArticleService extends IService<Article> {
    void saveArticleLost(Article article, MultipartFile[] articleImgList);

    void removeArticle(Long id);

    void saveArticleFind(Article article, MultipartFile[] articleImgList);

    Article getArticle(Long id);

    MyPage<Article> getLostArticlePage(MyPage<Article> page);

    void updateArticle(Article article, MultipartFile[] articleImgList);

    MyPage<Article> getFindArticlePage(MyPage<Article> page);
}

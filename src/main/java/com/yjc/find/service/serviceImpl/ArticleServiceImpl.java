package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.base.exception.MyException;
import com.yjc.find.dao.ArticleImgMapper;
import com.yjc.find.dao.ArticleMapper;
import com.yjc.find.dao.SysMessageMapper;
import com.yjc.find.pojo.Article;
import com.yjc.find.pojo.ArticleImg;
import com.yjc.find.pojo.Message;
import com.yjc.find.pojo.SysMessage;
import com.yjc.find.service.ArticleService;
import com.yjc.find.service.MessageService;
import com.yjc.find.utils.ImageUtil;
import com.yjc.find.utils.MyUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.pattern.PathPattern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PrimitiveIterator;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private ArticleImgMapper articleImgMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private SysMessageMapper sysMessageMapper;

    
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveArticleLost(Article article, MultipartFile[] articleImgList) {
        MyUtil.checkNull(article);
        List<String> imgAddrList = new ArrayList<>();

        MyUtil.checkNull(article.getArticleName());
        MyUtil.checkNull(article.getContent());
        MyUtil.checkNull(article.getUserId());
        MyUtil.checkNull(article.getSchoolId());
        MyUtil.checkNull(article.getArticleCategoryId());
        MyUtil.checkNull(article.getAreaId());
        MyUtil.checkNull(article.getLostOrFind());
        this.baseMapper.insert(article);
        try {
            if (articleImgList != null && articleImgList.length > 0) {
                for (int i = 0; i < articleImgList.length; i++) {
                    String imgAddr = ImageUtil.storeImg(articleImgList[i].getInputStream(), articleImgList[i].getOriginalFilename());
                    imgAddrList.add(imgAddr);
                }
                articleImgMapper.batchInsert(imgAddrList,article.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException("图片处理出错");
        }
        //和find匹配 匹配成功发送系统提醒
        Article matchArticle = new Article();
        matchArticle.setSchoolId(article.getSchoolId()).setAreaId(article.getAreaId()).setArticleCategoryId(article.getArticleCategoryId()).setLostDate(article.getLostDate()).setLostOrFind(2);
        List<Article> matchList = this.baseMapper.matchArticle(matchArticle);
        if (matchList!=null&&matchList.size()>0){
            for (Article item:matchList
                 ) {
                String contentOfMatch= "["+item.getArticleName()+"]"+" 可能与您发布的失物招领有关,请查看";
                String contentOfBeMatched ="["+article.getArticleName()+"]"+" 可能与您发布的寻物启事有关,请查看";
                String title = "系统提醒";
                SysMessage sysMessage = new SysMessage();
                sysMessage.setContent(contentOfMatch).setReceiveId(article.getUserId()).setTitle(title);
                SysMessage beMatched  = new SysMessage();
                beMatched.setContent(contentOfBeMatched).setTitle(title).setReceiveId(item.getUserId());
                sysMessageMapper.insert(sysMessage);
                sysMessageMapper.insert(beMatched);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveArticleFind(Article article, MultipartFile[] articleImgList) {
        MyUtil.checkNull(article);
        List<String> imgAddrList = new ArrayList<>();
        MyUtil.checkNull(article.getArticleCategoryId());
        MyUtil.checkNull(article.getArticleName());
        MyUtil.checkNull(article.getContent());
        MyUtil.checkNull(article.getUserId());
        MyUtil.checkNull(article.getSchoolId());

        MyUtil.checkNull(article.getAreaId());
        MyUtil.checkNull(article.getLostOrFind());
        this.baseMapper.insert(article);
        try {
            if (articleImgList != null && articleImgList.length > 0) {
                for (int i = 0; i < articleImgList.length; i++) {
                    String imgAddr = ImageUtil.storeImg(articleImgList[i].getInputStream(), articleImgList[i].getOriginalFilename());
                    imgAddrList.add(imgAddr);
                }
                articleImgMapper.batchInsert(imgAddrList,article.getId());
            }
        }catch (Exception e){

            throw new MyException("图片处理出错");
        }
        //和 lost匹配
        Article matchArticle = new Article();
        matchArticle.setAreaId(article.getAreaId()).setArticleCategoryId(article.getArticleCategoryId()).setLostDate(article.getLostDate()).setLostOrFind(1);
        List<Article> matchList = this.baseMapper.matchArticle(matchArticle);
        if (matchList!=null&&matchList.size()>0){
            for (Article item:matchList
            ) {
                String contentOfMatch= "["+item.getArticleName()+"]"+" 可能与您发布的寻物启事有关,请查看";
                String contentOfBeMatched ="["+article.getArticleName()+"]"+" 可能与您发布的失物招领有关,请查看";
                String title = "系统提醒";
                SysMessage sysMessage = new SysMessage();
                sysMessage.setContent(contentOfMatch).setReceiveId(article.getUserId()).setTitle(title);
                SysMessage beMatched  = new SysMessage();
                beMatched.setContent(contentOfBeMatched).setTitle(title).setReceiveId(item.getUserId());
                sysMessageMapper.insert(sysMessage);
                sysMessageMapper.insert(beMatched);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void removeArticle(Long id) {
        MyUtil.checkNull(id);
        ArticleImg queryArticleImg = new ArticleImg();
        queryArticleImg.setArticleId(id);
        List<ArticleImg> articleImgs = articleImgMapper.selectList(new QueryWrapper<>(queryArticleImg));
        if (articleImgs!=null) {
            for (ArticleImg articleImg : articleImgs
            ) {
                ImageUtil.deleteFileOrPath(articleImg.getArticleImgAddr());
            }
        }
        articleImgMapper.deleteById(id);
    }


    @Override
    public Article getArticle(Long id) {
        return this.baseMapper.selectArticle(id);
    }

    @Override
    public MyPage<Article> getLostArticlePage(MyPage<Article> page) {
        page.setCount(this.baseMapper.selectLostCount(page.getParams()));
        page.setRecords(this.baseMapper.selectLostPage(page.getParams()));
        return page;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateArticle(Article article, MultipartFile[] articleImgList) {
        MyUtil.checkNull(article);
        article.setUpdateDate(new Date());
        List<String> imgAddrList = new ArrayList<>();
        MyUtil.checkNull(article.getId());
        if (articleImgList!=null&&articleImgList.length>0){
            //删除原来照片
            ArticleImg queryArticleImg = new ArticleImg();
            queryArticleImg.setArticleId(article.getId());
            List<ArticleImg> articleImgs = articleImgMapper.selectList(new QueryWrapper<>(queryArticleImg));
            if (articleImgs!=null) {
                for (ArticleImg articleImg : articleImgs
                ) {
                    ImageUtil.deleteFileOrPath(articleImg.getArticleImgAddr());
                }
            }
            articleImgMapper.delete(new QueryWrapper<>(queryArticleImg));
            //插入新图片
            try {
                if (articleImgList != null && articleImgList.length > 0) {
                    for (int i = 0; i < articleImgList.length; i++) {
                        String imgAddr = ImageUtil.storeImg(articleImgList[i].getInputStream(), articleImgList[i].getOriginalFilename());
                        imgAddrList.add(imgAddr);
                    }
                    articleImgMapper.batchInsert(imgAddrList,article.getId());
                }
            }catch (Exception e){
                throw new MyException("图片处理出错");
            }
        }
        this.baseMapper.updateById(article);
    }

    @Override
    public MyPage<Article> getFindArticlePage(MyPage<Article> page) {
        page.setCount(this.baseMapper.selectFindCount(page.getParams()));
        page.setRecords(this.baseMapper.selectFindPage(page.getParams()));
        return page;
    }
}

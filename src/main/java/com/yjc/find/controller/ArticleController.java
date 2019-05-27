package com.yjc.find.controller;

import com.yjc.find.base.bean.MyPage;
import com.yjc.find.base.bean.ResultMsg;
import com.yjc.find.base.bean.ResultMsgFactory;
import com.yjc.find.base.controller.BaseController;
import com.yjc.find.pojo.Article;
import com.yjc.find.service.ArticleService;
import com.yjc.find.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/find/article")
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/page")
    public ResultMsg getPage(@RequestParam Map<String,Object> params){
        MyPage<Article> page =new MyPage<>(params);
        page = articleService.getArticlePage(page);
        ResultMsg resultMsg = ResultMsgFactory.createSuccessMsg();
        resultMsg.setCount(page.getCount());
        resultMsg.setData(page.getRecords());
        return resultMsg;
    }
    @PutMapping
    public ResultMsg update(Article article, @RequestParam(value = "imgList",required = false) MultipartFile[] articleImgList){

        articleService.updateArticle(article,articleImgList);
        return ResultMsgFactory.createSuccessMsg();
    }

    @GetMapping("/lost/page")
    public ResultMsg lostPage(@RequestParam Map<String,Object> params){
        MyPage<Article> page =new MyPage<>(params);
        page = articleService.getLostArticlePage(page);
        ResultMsg resultMsg = ResultMsgFactory.createSuccessMsg();
        resultMsg.setCount(page.getCount());
        resultMsg.setData(page.getRecords());
        return resultMsg;
    }
    @GetMapping("/find/page")
    public ResultMsg findPage(@RequestParam Map<String,Object> params){
        MyPage<Article> page =new MyPage<>(params);
        page = articleService.getFindArticlePage(page);
        ResultMsg resultMsg = ResultMsgFactory.createSuccessMsg();
        resultMsg.setCount(page.getCount());
        resultMsg.setData(page.getRecords());
        return resultMsg;
    }
    @GetMapping("/{id}")
    public ResultMsg get(@PathVariable("id")Long id){
         return ResultMsgFactory.createSuccessMsg(articleService.getArticle(id) );
    }

    @PostMapping("/lost")
    public ResultMsg addL(Article article, @RequestParam(value = "token") String token ,@RequestParam(value = "imgList",required = false) MultipartFile[] articleImgList){
        JwtUtil.checkToken(token);
        article.setUserId(JwtUtil.getUser().getId());
        article.setLostOrFind(1);
        articleService.saveArticleLost(article,articleImgList);
        JwtUtil.removeUser();
        return ResultMsgFactory.createSuccessMsg();
    }

    @PostMapping("/find")
    public ResultMsg addF(Article article,@RequestParam(value = "token") String token , @RequestParam(value = "imgList",required = false) MultipartFile[] articleImgList){
        JwtUtil.checkToken(token);
        article.setUserId(JwtUtil.getUser().getId());
        article.setLostOrFind(2);
        articleService.saveArticleFind(article,articleImgList);
        JwtUtil.removeUser();
        return ResultMsgFactory.createSuccessMsg();
    }

    @DeleteMapping("/{id}")
    public ResultMsg delete(@PathVariable("id")Long id){
       articleService.removeArticle(id);
       return ResultMsgFactory.createSuccessMsg();
    }
}

package com.yjc.find.controller;

import com.yjc.find.base.bean.ResultMsg;
import com.yjc.find.base.bean.ResultMsgFactory;
import com.yjc.find.base.controller.BaseController;
import com.yjc.find.pojo.ArticleCategory;
import com.yjc.find.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.RMIClassLoader;

@RestController
@RequestMapping("/find/article-category")
public class ArticleCategoryController extends BaseController {
    @Autowired
    private ArticleCategoryService articleCategoryService;

    @GetMapping("list")
    public ResultMsg list(){
       return ResultMsgFactory.createSuccessMsg(articleCategoryService.list());
    }

    @PutMapping
    public ResultMsg update(ArticleCategory articleCategory){
        articleCategoryService.updateCategory(articleCategory);
        return ResultMsgFactory.createSuccessMsg();
    }

    @DeleteMapping("/{id}")
    public ResultMsg delete(@PathVariable("id") Long id){
        articleCategoryService.removeById(id);
        return ResultMsgFactory.createSuccessMsg();
    }

    @GetMapping("/{id}")
    public ResultMsg get(@PathVariable("id")Long id){
        return ResultMsgFactory.createSuccessMsg(articleCategoryService.getById(id)) ;
    }

    @PostMapping
    public ResultMsg add( ArticleCategory articleCategory){
        articleCategoryService.saveArticleCategory(articleCategory);
        return ResultMsgFactory.createSuccessMsg();

    }
}

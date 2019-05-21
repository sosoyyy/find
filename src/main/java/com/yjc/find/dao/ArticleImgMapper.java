package com.yjc.find.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.find.pojo.ArticleImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleImgMapper  extends BaseMapper<ArticleImg> {
    int batchInsert(@Param("articleImgList")List<String> articleImgList,@Param("articleId")Long articleId);
}

package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.dao.ArticleImgMapper;
import com.yjc.find.pojo.ArticleImg;
import com.yjc.find.service.ArticleImgService;
import org.springframework.stereotype.Service;

@Service
public class ArticleImgServiceImpl extends ServiceImpl<ArticleImgMapper, ArticleImg> implements ArticleImgService {
}

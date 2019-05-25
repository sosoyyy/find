package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.dao.ReplyMapper;
import com.yjc.find.dao.SysMessageMapper;
import com.yjc.find.pojo.Article;
import com.yjc.find.pojo.Reply;
import com.yjc.find.pojo.SysMessage;
import com.yjc.find.service.ArticleService;
import com.yjc.find.service.ReplyService;
import com.yjc.find.utils.JwtUtil;
import com.yjc.find.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.krb5.internal.PAData;

@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper,Reply> implements ReplyService {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private SysMessageMapper sysMessageMapper;
    @Override
    @Transactional
    public void saveReply(Reply reply) {
        if (JwtUtil.getUser()==null||JwtUtil.getUser().getId()==null){
            MyUtil.checkFailed("账户错误");
        }
        MyUtil.checkNull(reply);
        reply.setSendId(JwtUtil.getUser().getId());
        MyUtil.checkNull(reply.getArticleId());
        MyUtil.checkNull(reply.getContent());
        this.baseMapper.insert(reply);
        //发送系统站内信给用户提醒收到回复
        Article article = articleService.getById(reply.getArticleId());
        MyUtil.checkNull(article);
        MyUtil.checkNull(article.getId());
        String content = "您发布的内容 [" +article.getArticleName()+"] 下有了新的回复，请查看。";
        String title = "回复提醒";
        SysMessage sysMessage = new SysMessage().setReceiveId(article.getUserId()).setContent(content).setTitle(title).setArticleId(article.getId());
        sysMessageMapper.insert(sysMessage);
    }

    @Override
    public Reply getReply(Long id) {
        return this.baseMapper.selectReply(id);
    }

    @Override
    public MyPage<Reply> getReplyPage(MyPage<Reply> page) {
        page.setCount(this.baseMapper.selectReplyCount(page.getParams()));
        page.setRecords(this.baseMapper.selectReplyPage(page.getParams()));
        return page;
    }
}

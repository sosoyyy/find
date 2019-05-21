package com.yjc.find.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.pojo.Reply;
import org.springframework.stereotype.Service;

@Service
public interface ReplyService extends IService<Reply> {
    void saveReply(Reply reply);

    Reply getReply(Long id);

    MyPage<Reply> getReplyPage(MyPage<Reply> page);
}

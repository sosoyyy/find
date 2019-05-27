package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.dao.MessageMapper;
import com.yjc.find.pojo.Message;
import com.yjc.find.service.MessageService;
import com.yjc.find.utils.JwtUtil;
import com.yjc.find.utils.MyUtil;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {



    @Override
    public void saveMessage(Message message) {
        //发送私信
        MyUtil.checkNull(JwtUtil.getUser());
        MyUtil.checkNull(JwtUtil.getUser().getId());
        MyUtil.checkNull(message);
        MyUtil.checkNull(message.getReceiveId());
        MyUtil.checkNull(message.getContent());
       // MyUtil.checkNull(message.getTitle());
        message.setSendId(JwtUtil.getUser().getId());
        this.baseMapper.insert(message);
    }

    @Override
    public Message getMessageOne(Long id) {
       Message message =  this.baseMapper.selectMessageOne(id);
       if (message!=null){
           Message insertMessage = new Message().setIsRead(1);
           insertMessage.setId(id);
           this.baseMapper.updateById(insertMessage);
       }
        return message;
    }

    @Override
    public MyPage<Message> getMessagePage(MyPage<Message> page) {
        page.setCount(this.baseMapper.selectMessageCount(page.getParams()));
        page.setRecords(this.baseMapper.selectMessagePage(page.getParams()));
        return page;
    }
}

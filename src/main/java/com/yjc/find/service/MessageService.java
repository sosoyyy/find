package com.yjc.find.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.pojo.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MessageService extends IService<Message> {


    void saveMessage(Message message);

    Message getMessageOne(Long id);

    MyPage<Message> getMessagePage(MyPage<Message> page);
}

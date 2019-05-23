package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.dao.RecordMapper;
import com.yjc.find.dao.SysMessageMapper;
import com.yjc.find.dao.UserMapper;
import com.yjc.find.pojo.Record;
import com.yjc.find.pojo.SysMessage;
import com.yjc.find.pojo.User;
import com.yjc.find.service.RecordService;
import com.yjc.find.service.SysMessageService;
import com.yjc.find.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.pattern.PathPattern;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service
public class SysMessageServiceImpl extends ServiceImpl<SysMessageMapper, SysMessage> implements SysMessageService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Override
    public void saveSysMessage(SysMessage sysMessage) {
        MyUtil.checkNull(sysMessage);
        MyUtil.checkNull(sysMessage.getContent());
        MyUtil.checkNull(sysMessage.getReceiveId());
        this.baseMapper.insert(sysMessage);
    }

    @Override
    public SysMessage getSysMessage(Long id) {
        SysMessage sysMessage =  this.baseMapper.selectSysMessage(id);
        sysMessage.setIsRead(1);
        this.baseMapper.updateById(sysMessage);
        return sysMessage;
    }

    @Override
    public MyPage<SysMessage> getSysMessagePage(MyPage<SysMessage> page) {
        page.setRecords(this.baseMapper.selectSysPage(page.getParams()));
        page.setCount(this.baseMapper.selectSysCount(page.getParams()));
        return page;
    }

    @Override
    public void saveBatchSysMessage(SysMessage sysMessage, List<Long> receiveIds) {
        this.baseMapper.batchInsert(sysMessage,receiveIds);
    }

    @Override
    @Transactional
    public void saveAllUser(SysMessage message) {
        MyUtil.checkNull(message.getTitle());
        MyUtil.checkNull(message.getContent());
        Record record = new Record().setReceiveType(2).setTitle(message.getTitle()).setContent(message.getContent());
        recordMapper.insert(record);
        User queryUser = new User().setUserType(2);
        List<User> users = userMapper.selectList(new QueryWrapper<>(queryUser));
        for (User user:users
             ) {
            message.setReceiveId(user.getId());
            this.baseMapper.insert(message);
        }
    }

    @Override
    public void saveAllManager(SysMessage message) {
        User queryUser = new User().setUserType(1);
        MyUtil.checkNull(message.getTitle());
        MyUtil.checkNull(message.getContent());
        Record record = new Record().setReceiveType(1).setTitle(message.getTitle()).setContent(message.getContent());
        recordMapper.insert(record);
        List<User> users = userMapper.selectList(new QueryWrapper<>(queryUser));
        for (User user:users
        ) {
            message.setReceiveId(user.getId());
            this.baseMapper.insert(message);
        }
    }
}

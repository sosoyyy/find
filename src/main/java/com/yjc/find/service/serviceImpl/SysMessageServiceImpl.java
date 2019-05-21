package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.dao.SysMessageMapper;
import com.yjc.find.pojo.SysMessage;
import com.yjc.find.service.SysMessageService;
import com.yjc.find.utils.MyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMessageServiceImpl extends ServiceImpl<SysMessageMapper, SysMessage> implements SysMessageService {
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
        return null;
    }

    @Override
    public void saveBatchSysMessage(SysMessage sysMessage, List<Long> receiveIds) {
        this.baseMapper.batchInsert(sysMessage,receiveIds);
    }
}

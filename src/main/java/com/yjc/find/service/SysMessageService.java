package com.yjc.find.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.pojo.SysMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysMessageService extends IService<SysMessage> {
    void saveSysMessage(SysMessage SysMessage);

    SysMessage getSysMessage(Long id);

    MyPage<SysMessage> getSysMessagePage(MyPage<SysMessage> page);

    void saveBatchSysMessage(SysMessage sysMessage, List<Long> receiveIds);
}

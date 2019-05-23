package com.yjc.find.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.pojo.Record;
import org.springframework.stereotype.Service;

@Service
public interface RecordService extends IService<Record> {
    MyPage<Record> getRecordPage(MyPage<Record> page);
}

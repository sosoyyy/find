package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.dao.RecordMapper;
import com.yjc.find.pojo.Record;
import com.yjc.find.service.RecordService;

import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Override
    public MyPage<Record> getRecordPage(MyPage<Record> page) {
        Record queryRecord = new Record();
        page.setCount(this.baseMapper.selectRecordCount(page.getParams()));
        page.setRecords(this.baseMapper.selectRecordPage(page.getParams()));
        return page;
    }
}

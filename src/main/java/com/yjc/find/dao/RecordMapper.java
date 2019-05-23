package com.yjc.find.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.find.pojo.Message;
import com.yjc.find.pojo.Record;

import java.util.List;
import java.util.Map;

public interface RecordMapper extends BaseMapper<Record> {
    int selectRecordCount(Map<String,Object> params);

    List<Record> selectRecordPage(Map<String,Object> params);
}

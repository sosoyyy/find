package com.yjc.find.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.find.pojo.SysMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysMessageMapper extends BaseMapper<SysMessage> {
    SysMessage selectSysMessage(Long id);

    void batchInsert(@Param("sysMessage") SysMessage sysMessage, @Param("list") List<Long> receiveIds);

    List<SysMessage> selectSysPage(Map<String, Object> params);

    int selectSysCount(Map<String, Object> params);
}

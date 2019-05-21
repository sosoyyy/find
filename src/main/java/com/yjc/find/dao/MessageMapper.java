package com.yjc.find.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.find.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MessageMapper extends BaseMapper<Message> {


    Message selectMessageOne(Long id);

    int selectMessageCount(Map<String, Object> params);

    List<Message> selectMessagePage(Map<String, Object> params);
}

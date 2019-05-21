package com.yjc.find.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.find.pojo.Reply;

import java.util.List;
import java.util.Map;

public interface ReplyMapper  extends BaseMapper<Reply> {
    Reply selectReply(Long id);

    int selectReplyCount(Map<String, Object> params);

    List<Reply> selectReplyPage(Map<String, Object> params);
}

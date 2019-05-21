package com.yjc.find.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.find.pojo.Boards;

import java.util.List;
import java.util.Map;

public interface BoardsMapper  extends BaseMapper<Boards> {
    List<Boards> selectBoardsPage(Map<String, Object> params);

    int selectBoardsCount(Map<String, Object> params);
}

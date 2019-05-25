package com.yjc.find.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.find.pojo.Area;

import java.util.List;
import java.util.Map;

public interface AreaMapper extends BaseMapper<Area> {
    List<Area> selectAreaPage(Map<String, Object> params);

    int selectAreaCount(Map<String, Object> params);
}

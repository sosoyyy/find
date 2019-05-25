package com.yjc.find.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.find.pojo.School;

import java.util.List;
import java.util.Map;

public interface SchoolMapper extends BaseMapper<School> {
    List<School> selectSchoolPage(Map<String, Object> params);

    int selectSchoolCount(Map<String, Object> params);
}

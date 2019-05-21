package com.yjc.find.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.find.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    User selectUser(Long id);

    int selectUserCount(Map<String, Object> params);

    List<User> selectUserPage(Map<String, Object> params);
}

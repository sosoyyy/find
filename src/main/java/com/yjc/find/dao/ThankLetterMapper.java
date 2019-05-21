package com.yjc.find.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.pojo.ThankLetter;

import java.util.List;
import java.util.Map;

public interface ThankLetterMapper extends BaseMapper<ThankLetter> {
    ThankLetter selectLetter(Long id);


    int selectLetterCount(Map<String, Object> params);

    List<ThankLetter> selectLetterPage(Map<String, Object> params);
}

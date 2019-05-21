package com.yjc.find.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.pojo.ThankLetter;
import org.springframework.stereotype.Service;

@Service
public interface ThankLetterService extends IService<ThankLetter> {
    void saveLetter(ThankLetter thankLetter);

    ThankLetter getLetter(Long id);

    MyPage<ThankLetter> getLetterPage(MyPage<ThankLetter> page);
}

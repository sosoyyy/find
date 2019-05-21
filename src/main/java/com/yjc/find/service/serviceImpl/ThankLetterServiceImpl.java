package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.dao.ThankLetterMapper;
import com.yjc.find.pojo.ThankLetter;
import com.yjc.find.service.ThankLetterService;
import com.yjc.find.utils.JwtUtil;
import com.yjc.find.utils.MyUtil;
import org.springframework.stereotype.Service;

@Service
public class ThankLetterServiceImpl extends ServiceImpl<ThankLetterMapper,ThankLetter> implements ThankLetterService {
    @Override
    public void saveLetter(ThankLetter thankLetter) {
        MyUtil.checkNull(thankLetter);
        MyUtil.checkNull(thankLetter.getTitle());
        MyUtil.checkNull(thankLetter.getContent());
        MyUtil.checkNull(JwtUtil.getUser());
        MyUtil.checkNull(JwtUtil.getUser().getId());
        thankLetter.setSendId(JwtUtil.getUser().getId());
        this.baseMapper.insert(thankLetter);

    }

    @Override
    public ThankLetter getLetter(Long id) {
        MyUtil.checkNull(id);
        return this.baseMapper.selectLetter(id);
    }

    @Override
    public MyPage<ThankLetter> getLetterPage(MyPage<ThankLetter> page) {
        page.setCount(this.baseMapper.selectLetterCount(page.getParams()));
        page.setRecords(this.baseMapper.selectLetterPage(page.getParams()));
        return page;
    }
}

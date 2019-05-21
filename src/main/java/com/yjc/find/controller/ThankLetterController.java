package com.yjc.find.controller;

import com.yjc.find.base.bean.MyPage;
import com.yjc.find.base.bean.ResultMsg;
import com.yjc.find.base.bean.ResultMsgFactory;
import com.yjc.find.base.controller.BaseController;
import com.yjc.find.pojo.ThankLetter;
import com.yjc.find.service.ThankLetterService;
import com.yjc.find.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/find/thank-letter")
public class ThankLetterController extends BaseController {
    @Autowired
    private ThankLetterService thankLetterService;

    @PostMapping
    public ResultMsg add(ThankLetter thankLetter){
        thankLetterService.saveLetter(thankLetter);
        return ResultMsgFactory.createSuccessMsg();
    }

    @DeleteMapping("/{id}")
    public ResultMsg delete(@PathVariable("id")Long id){
        thankLetterService.removeById(id);
        return ResultMsgFactory.createSuccessMsg();
    }

    @GetMapping("/{id}")
    public ResultMsg get(@PathVariable("id")Long id){
        return ResultMsgFactory.createSuccessMsg(thankLetterService.getLetter(id));
    }

    @PutMapping
    public ResultMsg update(ThankLetter thankLetter){
        MyUtil.checkNull(thankLetter);
        MyUtil.checkNull(thankLetter.getId());
        thankLetterService.updateById(thankLetter);
        return ResultMsgFactory.createSuccessMsg();
    }

    @GetMapping
    public ResultMsg page(@RequestParam Map<String,Object> params){
        MyPage<ThankLetter> page = new MyPage<>(params);
        page = thankLetterService.getLetterPage(page);
        ResultMsg resultMsg = ResultMsgFactory.createSuccessMsg();
        resultMsg.setCount(page.getCount());
        resultMsg.setData(page.getRecords());
        return resultMsg;
    }

}

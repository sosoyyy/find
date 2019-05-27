package com.yjc.find.controller;

import com.yjc.find.base.bean.MyPage;
import com.yjc.find.base.bean.ResultMsg;
import com.yjc.find.base.bean.ResultMsgFactory;
import com.yjc.find.base.controller.BaseController;
import com.yjc.find.pojo.Reply;
import com.yjc.find.service.ReplyService;
import com.yjc.find.utils.JwtUtil;
import com.yjc.find.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/find/reply")
public class ReplyController extends BaseController {
    @Autowired
    private ReplyService replyService;

    @PostMapping
    public ResultMsg add(Reply reply, @RequestParam(value = "token") String token){
        JwtUtil.checkToken(token);
        //todo 站内信提醒用户
        replyService.saveReply(reply);
        JwtUtil.removeUser();
        return ResultMsgFactory.createSuccessMsg();
    }
    @GetMapping("/{id}")
    public ResultMsg get(@PathVariable("id")Long id){
     return ResultMsgFactory.createSuccessMsg(replyService.getReply(id));
    }

    @GetMapping("/page")
    public ResultMsg getPage(@RequestParam Map<String,Object> params){
        MyPage<Reply> page = new MyPage<>(params);
        page = replyService.getReplyPage(page);
        ResultMsg resultMsg = ResultMsgFactory.createSuccessMsg();
        resultMsg.setCount(page.getCount());
        resultMsg.setData(page.getRecords());
        return resultMsg;
    }

    @DeleteMapping("/{id}")
    public ResultMsg delete(@PathVariable("id")Long id){
        replyService.removeById(id);
        return ResultMsgFactory.createSuccessMsg();
    }
}

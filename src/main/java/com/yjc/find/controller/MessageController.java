package com.yjc.find.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.base.bean.ResultMsg;
import com.yjc.find.base.bean.ResultMsgFactory;
import com.yjc.find.base.controller.BaseController;
import com.yjc.find.pojo.Message;
import com.yjc.find.service.MessageService;
import com.yjc.find.utils.JwtUtil;
import com.yjc.find.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/find/message")
public class MessageController extends BaseController {
    @Autowired
    private MessageService messageService;
    //全部标记为已读
    @PutMapping("/all")
    public ResultMsg updateAll(){
        Message whereMessage = new Message();
        Message message = new Message();
        message.setIsRead(1);
        whereMessage.setReceiveId(JwtUtil.getUser().getId());
        messageService.update(message,new UpdateWrapper<>(whereMessage));
        return ResultMsgFactory.createSuccessMsg();
    }
    //全部删除
    @DeleteMapping("/all")
    public ResultMsg deleteAll(){
        Message queryMessage = new Message();
        queryMessage.setReceiveId(JwtUtil.getUser().getId());
        messageService.remove(new QueryWrapper<>(queryMessage));
        return ResultMsgFactory.createSuccessMsg();
    }
    /**
     * 获取私信分页
     * @param params isRead /receiveId必需要
     * @return
     */
    @GetMapping("/page")
    public ResultMsg page(@RequestParam Map<String,Object> params){
        MyPage<Message> page = new MyPage<>(params);
        page = messageService.getMessagePage(page);
        ResultMsg resultMsg = ResultMsgFactory.createSuccessMsg();
        resultMsg.setCount(page.getCount());
        resultMsg.setData(page.getRecords());
        return resultMsg;
    }
    /**
     * 添加站内信
     * @param message
     * @return
     */
    @PostMapping
    public ResultMsg add(Message message,@RequestParam(value = "token") String token ){
        JwtUtil.checkToken(token);
        messageService.saveMessage(message);
        JwtUtil.removeUser();
        return ResultMsgFactory.createSuccessMsg();
    }

    /**
     * 删除站内信
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultMsg delete(@PathVariable("id")Long id){
        messageService.removeById(id);
        return ResultMsgFactory.createSuccessMsg();
    }

    /**
     * 查看站内信，并标记状态为已读
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultMsg get(@PathVariable("id")Long id){
        return ResultMsgFactory.createSuccessMsg(messageService.getMessageOne(id));
    }
    /**
     * 更改私信状态，标记为已读
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResultMsg update(@PathVariable("id")Long id){
        Message message = new Message().setIsRead(1);
        message.setId(id);
        messageService.updateById(message);
        return ResultMsgFactory.createSuccessMsg();
    }
}

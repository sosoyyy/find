package com.yjc.find.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.base.bean.ResultMsg;
import com.yjc.find.base.bean.ResultMsgFactory;
import com.yjc.find.base.controller.BaseController;
import com.yjc.find.pojo.Record;
import com.yjc.find.pojo.SysMessage;
import com.yjc.find.service.RecordService;
import com.yjc.find.service.SysMessageService;
import com.yjc.find.utils.JwtUtil;
import com.yjc.find.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/find/sys-message")
public class SysMessageController extends BaseController {
    @Autowired
    private SysMessageService sysMessageService;
    @Autowired
    private RecordService recordService;
    @DeleteMapping("/record/{id}")
    public ResultMsg deleteRecord(@PathVariable("id")Long id){
        recordService.removeById(id);
        return ResultMsgFactory.createSuccessMsg();
    }
    @GetMapping("/record/{id}")
    public ResultMsg getRecordOne(@PathVariable("id")Long id){
        return ResultMsgFactory.createSuccessMsg(recordService.getById(id));
    }
    @GetMapping("/record/page")
    public ResultMsg getRecord(@RequestParam Map<String,Object> params){
        MyPage<Record> page  = new MyPage<>(params);
        page = recordService.getRecordPage(page);
        ResultMsg resultMsg = ResultMsgFactory.createSuccessMsg();
        resultMsg.setCount(page.getCount());
        resultMsg.setData(page.getRecords());
        return resultMsg;
    }
    @PostMapping("all-user")
    public ResultMsg addAllUser(SysMessage message){
        sysMessageService.saveAllUser(message);
        return ResultMsgFactory.createSuccessMsg();
    }
    @PostMapping("all-manager")
    public ResultMsg addAllManager(SysMessage message){
        sysMessageService.saveAllManager(message);
        return ResultMsgFactory.createSuccessMsg();
    }
    @PostMapping
    public ResultMsg add(SysMessage sysMessage){
        sysMessageService.saveSysMessage(sysMessage);
        return ResultMsgFactory.createSuccessMsg();
    }
    @DeleteMapping("/{id}")
    public ResultMsg delete(@PathVariable("id")Long id){
        sysMessageService.removeById(id);
        return ResultMsgFactory.createSuccessMsg();
    }
    @DeleteMapping("/all")
    public ResultMsg deleteALL(){
        SysMessage queryMessage = new SysMessage();
        queryMessage.setReceiveId(JwtUtil.getUser().getId());
        sysMessageService.remove(new QueryWrapper<>(queryMessage));
        return ResultMsgFactory.createSuccessMsg();
    }
    /**
     * 全部标记为已读
     * @return
     */
    @PutMapping("/all")
    public  ResultMsg updateAll(){
        SysMessage sysMessage = new SysMessage();
        sysMessage.setIsRead(1);
        SysMessage updateSysMessage = new SysMessage();
        updateSysMessage.setReceiveId(JwtUtil.getUser().getId());
        sysMessageService.update(sysMessage,new UpdateWrapper<>(updateSysMessage));
        return ResultMsgFactory.createSuccessMsg();
    }
    /**
     * 更改私信状态，标记为已读
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResultMsg update(@PathVariable("id")Long id){
        SysMessage sysMessage = new SysMessage();
        sysMessage.setIsRead(1);
        sysMessage.setReceiveId(id);
        sysMessageService.updateById(sysMessage);
        return ResultMsgFactory.createSuccessMsg();
    }

    /**
     * 获取系统提醒信并标记为已读
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultMsg get(@PathVariable("id")Long id){
        return ResultMsgFactory.createSuccessMsg(sysMessageService.getSysMessage(id));
    }

    /**
     * 获取系统信提醒分页
     * @param params
     * @return
     */
    @GetMapping("/page")
    public ResultMsg page(@RequestParam Map<String,Object> params){
        params.put("receiveId",JwtUtil.getUser().getId());

        MyPage<SysMessage> page = new MyPage<>(params);
        page = sysMessageService.getSysMessagePage(page);
        ResultMsg resultMsg = ResultMsgFactory.createSuccessMsg();
        resultMsg.setData(page.getRecords());
        resultMsg.setCount(page.getCount());
        return resultMsg;
    }

    /**
     * 批量系统信
     * @return
     */
    @PostMapping("list")
    public ResultMsg addList(Map<String,Object> map){
        MyUtil.checkNull(map);
        MyUtil.checkNull(map.get("content"));
       // MyUtil.checkNull(map.get("title"));
        MyUtil.checkNull(map.get("receiveIds"));
        SysMessage sysMessage = new SysMessage();
        sysMessage.setContent((String)map.get("content"));
        sysMessage.setTitle((String)map.get("title"));
        List<Long> receiveIds = (List<Long>)map.get("receiveIds");
        sysMessageService.saveBatchSysMessage(sysMessage,receiveIds);
        return ResultMsgFactory.createSuccessMsg();
    }

}

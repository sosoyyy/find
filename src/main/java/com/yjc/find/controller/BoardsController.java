package com.yjc.find.controller;

import com.yjc.find.base.bean.MyPage;
import com.yjc.find.base.bean.ResultMsg;
import com.yjc.find.base.bean.ResultMsgFactory;
import com.yjc.find.base.controller.BaseController;
import com.yjc.find.pojo.Boards;
import com.yjc.find.service.BoardsService;
import com.yjc.find.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/find/boards")
public class BoardsController extends BaseController{
    @Autowired
    private BoardsService boardsService;

    @PostMapping
    public ResultMsg add(Boards boards){
        MyUtil.checkNull(boards);
        MyUtil.checkNull(boards.getContent());
        MyUtil.checkNull(boards.getTitle());
        MyUtil.checkNull(boards.getSchoolId());
        boardsService.save(boards);
        return ResultMsgFactory.createSuccessMsg();
    }

    @DeleteMapping("/{id}")
    public ResultMsg delete(@PathVariable("id")Long id){
        boardsService.removeById(id);
        return ResultMsgFactory.createSuccessMsg();
    }

    @PutMapping
    public ResultMsg update( Boards boards){
        MyUtil.checkNull(boards);
        boardsService.updateById(boards);
        return ResultMsgFactory.createSuccessMsg();
    }

    @GetMapping("/{id}")
    public ResultMsg get(@PathVariable("id") Long id){
        return ResultMsgFactory.createSuccessMsg(boardsService.getById(id));
    }

    @GetMapping("/page")
    public ResultMsg page(@RequestParam Map<String,Object> params){
        MyPage<Boards> page = new MyPage<>(params);
        page = boardsService.getBoardsPage(page);
        ResultMsg resultMsg = ResultMsgFactory.createSuccessMsg();
        resultMsg.setData(page.getRecords());
        resultMsg.setCount(page.getCount());
        return resultMsg;
    }
}

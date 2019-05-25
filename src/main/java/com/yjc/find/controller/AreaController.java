package com.yjc.find.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.base.bean.ResultMsg;
import com.yjc.find.base.bean.ResultMsgFactory;
import com.yjc.find.base.controller.BaseController;
import com.yjc.find.pojo.Area;
import com.yjc.find.pojo.Article;
import com.yjc.find.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/find/area")
public class AreaController extends BaseController {
    @Autowired
    private AreaService areaService;
    @GetMapping("page")
    public ResultMsg page(@RequestParam Map<String,Object> params){
        MyPage<Area> page =new MyPage<>(params);
        page = areaService.getAreaPage(page);
        ResultMsg resultMsg = ResultMsgFactory.createSuccessMsg();
        resultMsg.setCount(page.getCount());
        resultMsg.setData(page.getRecords());
        return resultMsg;
    }
    @GetMapping("/list")
    public ResultMsg list(@RequestParam Long schoolId){
        Area area = new Area().setSchoolId(schoolId);
        return ResultMsgFactory.createSuccessMsg(areaService.list(new QueryWrapper<>(area)));
    }
    @GetMapping("/{id}")
    public ResultMsg get(@PathVariable("id")Long id){
        return ResultMsgFactory.createSuccessMsg(areaService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResultMsg delete(@PathVariable("id")Long id){
        //JwtUtil.checkToken(token);
        areaService.removeById(id);
        return ResultMsgFactory.createSuccessMsg();
    }

    @PutMapping
    public ResultMsg update(Area area){
        //JwtUtil.checkToken(token);
        logger.info("更新地区信息{}",area);
        area.setUpdateDate(new Date());
        areaService.updateArea(area);
        return ResultMsgFactory.createSuccessMsg();
    }

    @PostMapping
    public ResultMsg add( Area area){
       // JwtUtil.checkToken(token);
       logger.info("保存地区信息{}",area);
       areaService.saveArea(area);
       return ResultMsgFactory.createSuccessMsg();
    }

}

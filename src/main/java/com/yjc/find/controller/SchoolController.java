package com.yjc.find.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjc.find.base.bean.ResultMsg;
import com.yjc.find.base.bean.ResultMsgFactory;
import com.yjc.find.base.controller.BaseController;
import com.yjc.find.pojo.School;
import com.yjc.find.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/find/school")
public class SchoolController extends BaseController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/list")
    public ResultMsg list(){
        //获取入驻状态学校
        School querySchool = new School();
        querySchool.setStatus(0);
        List<School> schoolList = schoolService.list(new QueryWrapper<>(querySchool));
        return ResultMsgFactory.createSuccessMsg(schoolList);
    }
    @GetMapping("/{id}")
    public ResultMsg get(@PathVariable("id")Long id){

        return ResultMsgFactory.createSuccessMsg(schoolService.getById(id));
    }
    @DeleteMapping("/{id}")
    public ResultMsg delete(@PathVariable("id")Long id){
        schoolService.removeSchool(id);
        return ResultMsgFactory.createSuccessMsg();
    }
    @PutMapping
    public ResultMsg update(School school, @RequestParam(value = "schoolImg",required = false) MultipartFile schoolImg){
        schoolService.updateSchool(school,schoolImg);
        return ResultMsgFactory.createSuccessMsg();
    }
    @PostMapping
    public ResultMsg add(School school, @RequestParam MultipartFile schoolImg){
        logger.info("保存学校信息{}");
        schoolService.saveSchool(school,schoolImg);
        return ResultMsgFactory.createSuccessMsg();
    }

}

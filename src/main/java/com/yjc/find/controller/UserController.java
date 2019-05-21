package com.yjc.find.controller;

import com.yjc.find.base.bean.MyPage;
import com.yjc.find.base.bean.ResultMsg;
import com.yjc.find.base.bean.ResultMsgFactory;
import com.yjc.find.base.controller.BaseController;
import com.yjc.find.pojo.Password;
import com.yjc.find.pojo.User;
import com.yjc.find.service.UserService;
import com.yjc.find.utils.JwtUtil;
import com.yjc.find.utils.MyUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/find/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    @GetMapping("/page")
    public ResultMsg page(@RequestParam Map<String,Object> params){
        MyPage<User> page = new MyPage<>(params);
        page = userService.getUserPage(page);
        ResultMsg resultMsg = ResultMsgFactory.createSuccessMsg();
        resultMsg.setCount(page.getCount());
        resultMsg.setData(page.getRecords());
        return resultMsg;
    }

    /**
     * 根据id获取User信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultMsg get(@PathVariable("id")Long id){
        return ResultMsgFactory.createSuccessMsg(userService.getUser(id));
    }

    /***
     * 修改密码
     * @param password
     * @return
     */
    @PutMapping("/pwd")
    public ResultMsg pwd(Password password){
        MyUtil.checkNull(password);
        MyUtil.checkNull(password.getConfirmPassword(),"确认密码为空");
        MyUtil.checkNull(password.getNewPassword(),"新密码为空");
        MyUtil.checkNull(password.getPassword(),"原始密码为空");
        if (password.getConfirmPassword()!=password.getNewPassword()){
            MyUtil.checkFailed("两次密码输入不一致");
        }
        MyUtil.checkNull(JwtUtil.getUser().getId());
        if (MyUtil.pwdMd5(password.getPassword())!=userService.getById(JwtUtil.getUser().getId()).getPassword()){
            MyUtil.checkFailed("原密码错误");
        }
        User user = new User();
        user.setPassword(MyUtil.pwdMd5(password.getNewPassword())).setId(JwtUtil.getUser().getId());
        userService.updateById(user);
        return ResultMsgFactory.createSuccessMsg();
    }

    /**
     * 修改用户信息/改变用户状态/
     * @param user
     * @param userImg
     * @return
     */
    @PutMapping
    public ResultMsg resultMsg(User user, @RequestParam(value ="userImgAddr",required = false)MultipartFile userImg){
        userService.update(user,userImg);
        return ResultMsgFactory.createSuccessMsg();
    }
}

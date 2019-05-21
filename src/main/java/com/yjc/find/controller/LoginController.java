package com.yjc.find.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjc.find.base.bean.ResultMsg;
import com.yjc.find.base.bean.ResultMsgFactory;
import com.yjc.find.base.constant.Constant;
import com.yjc.find.base.controller.BaseController;
import com.yjc.find.pojo.User;
import com.yjc.find.service.UserService;
import com.yjc.find.utils.JwtUtil;
import com.yjc.find.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/find")
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;

    @PostMapping("/reg")
    public ResultMsg reg(User user){
        MyUtil.checkNull(user);
        MyUtil.checkNull(user.getUsername());
        MyUtil.checkNull(user.getPassword());
        MyUtil.checkNull(user.getEmail());
        MyUtil.checkNull(user.getNickName());
        //检查是否有重复账号
        User queryUser1 = new User().setUsername(user.getUsername());
        if(userService.getOne(new QueryWrapper<>(queryUser1))!=null){
            MyUtil.checkFailed("账户名重复");
        }
        //检查nickName是否重复
        User queryUser2 = new User().setNickName(user.getNickName());
        if(userService.getOne(new QueryWrapper<>(queryUser2))!=null){
            MyUtil.checkFailed("名称重复");
        }
        User queryUser3 = new User().setEmail(user.getEmail());
        if(userService.getOne(new QueryWrapper<>(queryUser3))!=null){
            MyUtil.checkFailed("邮箱重复重复");
        }
        String pwd = MyUtil.pwdMd5(user.getPassword());
        user.setPassword(pwd);
        userService.save(user);
        return ResultMsgFactory.createSuccessMsg();
    }
    @PostMapping("/login")
    public ResultMsg login(User user){
        String password=user.getPassword();
        String username=user.getUsername();
        MyUtil.check(!StringUtils.isEmpty(username), "用户名不能为空");
        MyUtil.check(!StringUtils.isEmpty(password), "密码不能为空");
        user.setPassword(MyUtil.pwdMd5(password));
        user=userService.getOne(new QueryWrapper<>(user));

        MyUtil.checkNull(user,"用户名或密码错误");
        String token= JwtUtil.createJson(user, Constant.AUDIENCE, Constant.ISSUER, Constant.TTLMILLIS, Constant.BASE64_SECURITY);
        User currentUser=new User();
        currentUser.setUsername(user.getUsername());
        currentUser.setId(user.getId());
        currentUser.setUserType(user.getUserType());
        currentUser.setToken(token);
        return ResultMsgFactory.createSuccessMsg(currentUser);
    }
}

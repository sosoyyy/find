package com.yjc.find.base.controller;

import com.yjc.find.base.bean.ResultMsg;
import com.yjc.find.base.bean.ResultMsgFactory;
import com.yjc.find.base.exception.MyAuthException;
import com.yjc.find.base.exception.MyException;
import com.yjc.find.utils.JwtUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionController extends BaseController{
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
//        model.addAttribute("author", "Magical Sam");
    }

    /**
     * 全局异常捕捉处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultMsg errorHandler(Exception e) {
        logger.error("统一异常处理", e);
        //正式生产环境需要屏蔽数据库异常，这里开发环境直接返回所有异常信息
        JwtUtil.removeUser();

        return ResultMsgFactory.createErrorMsg("系统错误："+e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public ResultMsg myErrorHandler(MyException e){
        logger.warn("业务异常", e);
       JwtUtil.removeUser();

        return ResultMsgFactory.createErrorMsg(e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(value = MyAuthException.class)
    public ResultMsg myErrorHandler(MyAuthException e){
        logger.warn("认证异常", e);
        JwtUtil.removeUser();
        return ResultMsgFactory.createAuthErrorMsg(e.getMessage());
    }

}

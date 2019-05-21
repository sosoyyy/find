package com.yjc.find.utils;

import com.yjc.find.base.constant.Constant;
import com.yjc.find.base.exception.MyException;
import org.springframework.util.DigestUtils;

import java.util.Collection;

public class MyUtil {
    /**
     * Object转int，出错返回0
     * @param obj
     * @return
     */
    public static int getInt(Object obj){
        if(null!=obj){
            try {
                return Integer.valueOf(obj.toString());
            }catch(Exception e) {

            }
        }
        return 0;
    }

    /**
     * Object转double，出错返回0
     * @param obj
     * @return
     */
    public static double getDouble(Object obj){
        if(null!=obj){
            try {
                return Double.valueOf(obj.toString());
            }catch(Exception e) {

            }
        }
        return 0;
    }

    /**
     * Object转long，出错返回0
     * @param obj
     * @return
     */
    public static long getLong(Object obj){
        if(null!=obj){
            try {
                return Long.valueOf(obj.toString());
            }catch(Exception e) {

            }
        }
        return 0;
    }

    /**
     * 密码加密
     * @param password
     * @return
     */
    public static String pwdMd5(String password){
        password= Constant.SALT+password;
       return DigestUtils.md5DigestAsHex(password.getBytes());
   }


    /**
     * 对象转String，为空时返回“”
     * @param obj
     * @return
     */
    public static String ObjectToString(Object obj){
        return obj==null?"":obj.toString();
    }


    /**
     * 对象为空抛出异常
     * @param obj 检查对象
     */
    public static void checkNull(Object obj){
        checkNull(obj,"参数不能为空");
    }

    /**
     * 对象为空抛出异常
     * @param obj 检查对象
     * @param errorMsg 错误信息
     */
    public static void checkNull(Object obj,String errorMsg){
        if(obj==null) throw new MyException(errorMsg);
    }

    /**
     * 条件不成立时抛出异常
     * @param flag FALSE 抛出
     * @param errorMsg 错误信息
     */
    public static void check(boolean flag,String errorMsg){
        if(!flag) throw new MyException(errorMsg);
    }

    /**
     * 条件不成立时抛出异常
     * @param flag FALSE 抛出
     */
    public static void check(boolean flag){
        check(flag, "检查错误");
    }

    /**
     * 检查失败，直接抛出异常
     * @param errorMsg 错误信息
     */
    public static void checkFailed(String errorMsg){
        throw new MyException(errorMsg);
    }

    /**
     * 检查失败，直接抛出异常
     */
    public static void checkFailed(){
        checkFailed("系统错误");
    }

    /**
     * 检查集合，为空抛出异常
     * @param collection
     */
    public static void checkCollection(Collection collection){
        if(collection==null||collection.isEmpty()){
            checkCollection(collection,"数据为空");
        }
    }

    /**
     * 检查集合，为空抛出异常
     * @param collection
     * @param errorMsg 错误信息
     */
    public static void checkCollection(Collection collection,String errorMsg){
        if(collection==null||collection.isEmpty()){
            throw new MyException(errorMsg);
        }
    }
}

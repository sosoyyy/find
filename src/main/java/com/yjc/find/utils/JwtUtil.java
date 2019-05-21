package com.yjc.find.utils;

import com.yjc.find.base.constant.Constant;
import com.yjc.find.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final ThreadLocal<User> LOGIN_USER=new ThreadLocal<>();
    public static void checkToken(String token){
        if (token == null || !token.startsWith("bearer;")) {
            MyUtil.checkFailed("身份验证错误");
        }
        String token1 =token.substring(7);
        Claims claims= JwtUtil.parseJWT(token1, Constant.BASE64_SECURITY);
        MyUtil.checkNull(claims,"请登录后访问");
        long id=MyUtil.getLong(claims.get("id"));
        MyUtil.check(id!=0,"请登录后访问");
        User user = new User();
        user.setId(id);
        user.setUsername(MyUtil.ObjectToString(claims.get("username")));
        user.setUserType(MyUtil.getInt(claims.get("userType")));
        JwtUtil.setUser(user);
    }
    /**
     * 获取当前登录用户
     * @return
     */
    public static User getUser(){

        User user=LOGIN_USER.get();
        if(user==null){//登录未实现，临时处理
            user=new User();
            user.setId(1l);
            user.setUsername("admin");
        }
        return user;
    }

    /**
     * 设置当前登录用户
     * @param user
     */
    public static void setUser(User user){
        LOGIN_USER.set(user);

    }

    /**
     * 清除当前登录用户
     */
    public static void removeUser(){
        LOGIN_USER.remove();
    }


    /**
     * 生成token
     * @param user 用户
     * @param audience 客户端
     * @param issuer 服务端
     * @param TTLMillis 有效期
     * @param base64Security 秘钥
     * @return
     */
    public static String createJson(User user, String audience, String issuer, long TTLMillis, String base64Security){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("userType", user.getUserType())
                .claim("username", user.getUsername())
                .claim("id", user.getId())
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }

    /**
     * 解析jwt
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security){
        try
        {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }catch(Exception ex)
        {
            return null;
        }
    }
}

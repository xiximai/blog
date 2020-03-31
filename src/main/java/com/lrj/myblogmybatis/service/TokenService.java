package com.lrj.myblogmybatis.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lrj.myblogmybatis.pojo.Visitor;
import org.springframework.stereotype.Service;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/11 20:03
 */

@Service("TokenService")
public class TokenService {
    public String getToken(Visitor visitor){
        String token ="";
        token = JWT.create().withAudience(visitor.getEmail())
                .sign(Algorithm.HMAC256(visitor.getPassword()));
        return token;
       /**
       Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。

        withAudience()存入需要保存在token的信息，这里我把用户ID存入token中**/
    }
}

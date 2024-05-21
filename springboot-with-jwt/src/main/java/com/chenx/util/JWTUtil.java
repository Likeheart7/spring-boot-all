package com.chenx.util;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chenx.dto.UserTokenDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @author chenx
 * @description
 * @create 2022-12-16 15:16
 */
@Slf4j
public class JWTUtil {
    public static final String TOKEN_SECRET = "123456";

    /**
     * 生成token，自定义过期时间
     */
    public static String generateToken(UserTokenDTO userTokenDTO){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            HashMap<String, Object> header = new HashMap<>(2);
            header.put("type", "jwt");
            header.put("alg", "hs256");

            return JWT.create()
                    .withHeader(header)
                    .withClaim("token", JSONUtil.toJsonStr(userTokenDTO))
                    //token不带有过期时间，将过期交给redis处理
    //                .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("generate token occur error, error is {}", e);
            return null;
        }
    }

    /*
    检验token是否正确
     */
    public static UserTokenDTO parseToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier build = JWT.require(algorithm).build();
        DecodedJWT jwt = build.verify(token);
        String tokenInfo = jwt.getClaim("token").asString();
        return JSONUtil.toBean(tokenInfo, UserTokenDTO.class);
    }
}

package com.chenx.interceptor;

import com.chenx.dto.UserTokenDTO;
import com.chenx.service.RedisService;
import com.chenx.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenx
 * @description 用户判断是否token是否正确，正确的情况下检测token是否需要续期
 * @create 2022-12-16 17:24
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private RedisService redisService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("Authorization");
        String token = auth.substring("Bearer".length() + 1).trim();
        UserTokenDTO userTokenDTO = JWTUtil.parseToken(token);
        //判断请求是否有效
        if(redisService.get(userTokenDTO.getId()) == null ||
            !redisService.get(userTokenDTO.getId()).equals(token)){
            return false;
        }
        //判断是否需要续期
        if(redisService.getExpireTime(userTokenDTO.getId()) < 1 * 60 * 30){
            redisService.set(userTokenDTO.getId(), token);
            log.error("update token, id is: {}, user info is:{}", userTokenDTO.getId(), token);
        }
        return true;
    }
}

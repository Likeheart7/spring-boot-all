package com.chenx.controller;

import com.chenx.dto.UserTokenDTO;
import com.chenx.mapper.UserMapper;
import com.chenx.pojo.User;
import com.chenx.service.RedisService;
import com.chenx.service.UserService;
import com.chenx.util.JWTUtil;
import com.chenx.vo.LoginUserVO;
import com.chenx.vo.UpdatePasswordUserVO;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chenx
 * @description
 * @create 2022-12-16 16:10
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;


    /**
     * 登录功能
     * @param loginUserVO
     * @return
     */
    public String login(LoginUserVO loginUserVO){
        User user = userService.getByName(loginUserVO.getName());
        if(user == null){
            throw new RuntimeException("用户不存在");
        }
        if(!user.getPassword().equals(user.getPassword())){
            throw new RuntimeException("密码不一样");
        }

        //用户名密码正确生成token
        UserTokenDTO userTokenDTO = new UserTokenDTO();
        BeanUtils.copyProperties(userTokenDTO, loginUserVO);
        userTokenDTO.setId(user.getId());
        String token = JWTUtil.generateToken(userTokenDTO);
        redisService.set(user.getId(), token);
        return token;
    }

    /**
     * 登出功能
     * @param id
     * @return
     */
    public boolean loginOut(String id){
        boolean res = redisService.delete(id);
        return res;
    }

    /**
     * 更新密码
     */
    public String updatePwd(UpdatePasswordUserVO updatePasswordUserVO){
        User user = new User();
        user.setId(updatePasswordUserVO.getId());
        user.setPassword(updatePasswordUserVO.getPassword());
        User userById = userService.getById(updatePasswordUserVO.getId());
        if(userById == null){
            throw new RuntimeException("userById不存在");
        }
        if(!userService.updateById(user)){
            throw new RuntimeException("更新密码失败啦");
        }
        //生成新的token
        UserTokenDTO userTokenDTO = new UserTokenDTO();
        userTokenDTO.setId(userById.getId());
        userTokenDTO.setName(userById.getName());
        String token = JWTUtil.generateToken(userTokenDTO);
        redisService.set(userById.getId(), token);
        return token;
    }

}

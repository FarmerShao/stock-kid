package com.farmershao.www.stockmember.controller;

import com.farmershao.www.stockmember.entity.po.User;
import com.farmershao.www.stockmember.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserController
 *
 * @author Shao Yu
 * @since 2018/9/11 15:57
 **/
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired private UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User find(@PathVariable Long userId){
//        User user = (User)redisTemplate.opsForValue().get("user" + userId);
//        if (user == null) {
//            user = userService.find(userId);
//            redisTemplate.opsForValue().set("user" + userId, user);
//        }
        return userService.find(userId);
    }

}

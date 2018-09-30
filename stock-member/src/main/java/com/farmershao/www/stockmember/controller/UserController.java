package com.farmershao.www.stockmember.controller;

import com.farmershao.www.stockmember.configuration.MyConfiguration;
import com.farmershao.www.stockmember.entity.po.User;
import com.farmershao.www.stockmember.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired private MyConfiguration myConfiguration;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> findAll(){
        System.out.println(myConfiguration.getNames());
        return userService.findAll();
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public User find(@PathVariable Long user){
        return userService.find(user);
    }

}

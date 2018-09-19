package com.farmershao.www.stockmember.controller;

import com.farmershao.www.stockmember.configuration.MyConfiguration;
import com.farmershao.www.stockmember.configuration.TestImportConfiguration;
import com.farmershao.www.stockmember.repository.po.UserPO;
import com.farmershao.www.stockmember.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserController
 *
 * @author Shao Yu
 * @since 2018/9/11 15:57
 **/
@RestController
public class UserController {

    @Autowired private UserService userService;

    @Autowired private MyConfiguration myConfiguration;

    @Autowired private TestImportConfiguration importConfiguration;

    @RequestMapping(name = "/all", method = RequestMethod.GET)
    public List<UserPO> findAll(){
        System.out.println(myConfiguration.getNames());
        System.out.println(importConfiguration.getCode());
        return userService.findAll();
    }

}

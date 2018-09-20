package com.farmershao.www.stockmember.dao;

import com.farmershao.www.stockmember.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UserDao
 *
 * @author Shao Yu
 * @since 18/9/20 下午11:14
 **/
@Component
public class UserDao {

    @Autowired private UserRepository userRepository;

    public void insert(String mobile, String password) {

    }
}

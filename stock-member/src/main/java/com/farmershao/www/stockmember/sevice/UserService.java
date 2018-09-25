package com.farmershao.www.stockmember.sevice;

import com.farmershao.www.stockmember.dao.po.User;
import com.farmershao.www.stockmember.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService
 *
 * @author Shao Yu
 * @since 2018/9/11 16:19
 **/
@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User find(Long id){
        return userRepository.findById(id);
    }
}

package com.farmershao.www.stockmember.dao.repository;

import com.farmershao.www.stockmember.dao.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 *
 * @author Shao Yu
 * @since 2018/9/11 13:24
 **/
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 根据ID 查询用户
     * @param id
     * @return
     */
    User find(long id);

    /**
     * 插入新用户
     * @param user
     * @return
     */
    int insert(User user);
}

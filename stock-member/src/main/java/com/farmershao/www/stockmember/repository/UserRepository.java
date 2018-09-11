package com.farmershao.www.stockmember.repository;

import com.farmershao.www.stockmember.repository.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 *
 * @author Shao Yu
 * @since 2018/9/11 13:24
 **/
public interface UserRepository extends JpaRepository<UserPO,Long> {


}

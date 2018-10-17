package com.farmershao.www.stockmember.dao.repository;


import com.farmershao.www.stockmember.entity.po.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 *
 * @author Shao Yu
 * @since 2018/9/11 13:24
 **/
@CacheConfig(cacheNames = "user")
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 根据ID 查询用户
     * @param id
     * @return
     */
    @Cacheable(key ="#p0")
    User findById(long id);

}

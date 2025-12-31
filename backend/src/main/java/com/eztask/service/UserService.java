package com.eztask.service;

import com.eztask.entity.User;

/**
 * 用户服务接口
 *
 * @author EasyTask Team
 * @version 1.0.0
 */
public interface UserService {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息，如果不存在则返回null
     */
    User getByUsername(String username);
}
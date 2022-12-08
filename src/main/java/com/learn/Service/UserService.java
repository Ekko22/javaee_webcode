package com.learn.Service;

import com.learn.Domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author DingTian
 * @version 1.0
 */
public interface UserService {

    User selectOne(User user);

    boolean insert(User user);

    boolean updateById(int id,User user);

    List<User> selectPage(int page, int size);

    int count();

    List<User> selectOneUser(int id);

    boolean deleteById(int id);

    List<User> selectByName(String name);
}

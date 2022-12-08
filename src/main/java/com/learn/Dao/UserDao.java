package com.learn.Dao;

import com.learn.Domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author DingTian
 * @version 1.0
 */
@Mapper
public interface UserDao {

    /**
     * 根据用户名和密码查询用户是否正确
     */
    User selectOne(@Param("username") String username, @Param("password") String password);

    User selectByusername(String username);

    int insert(User user);

    int updateById(@Param("id") int id, @Param("user") User user);

    //分页查询
    @Select("select * from user limit #{page},#{size}")
    @ResultMap("UserResultMap")
    List<User> selectPage(int page, int size);

    @Select("select count(*) from user")
    int count();

    @Select("select * from user where user_id=#{id}")
    @ResultMap("UserResultMap")
    List<User> selectOneUser(int id);
//根据id删除用户
    @Update("delete from user where user_id=#{id}")
    boolean deleteById(int id);
//根据name查询用户
    @Select("select * from user where user_name=#{name}")
    @ResultMap("UserResultMap")
    List<User> selectByName(String name);
}

package com.jmper.mybatis.dao;

import com.jmper.mybatis.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2017-12-23 20:14:09)
 */
public interface UserMapper {
    /**
     * 根据id 返回 用户类
     *
     * @param id 用户id
     * @return user类
     */
    @Select("select * from t_user where id=#{id}")
    @Results(value = {
            @Result(property = "userName", column = "user_name"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "createTime", column = "create_time")
    })
    User selectOne(Integer id);

    /**
     * 插入数据
     *
     * @param user 用户对象
     * @return id
     */
    @Insert(value = " insert into t_user (user_name, password, nick_name, address, telephone, income, create_time) " +
            "values (#{userName}, #{password}, #{nickName},#{address}, #{telephone}, #{income}, #{createTime})")
    int insertOne(User user);

    @Delete(value = "delete from t_user where id=#{id}")
    int deleteUserById(int id);
}

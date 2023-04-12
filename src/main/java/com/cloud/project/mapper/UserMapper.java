package com.cloud.project.mapper;


import com.cloud.project.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(@Param("id") Long id);

    @Select("SELECT * FROM user WHERE username = #{username} and password = #{password}")
    User getUserByUsernameAndPassword(String username, String password);

    int insert(User user);
    @Insert("INSERT INTO user(username, password) VALUES(#{user.username}, #{user.password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createUser(User user);
}
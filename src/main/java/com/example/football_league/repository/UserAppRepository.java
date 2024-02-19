package com.example.football_league.repository;

import com.example.football_league.model.entity.UserApp;
import com.example.football_league.model.request.UserAppRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;

@Mapper
public interface UserAppRepository {
    @Results( id = "userMapMapper",
            value = {
                    @Result(column = "is_enabled", property = "isEnabled")
            })
    @Select("""
            select * from accounts where email = #{userEmail}
            """)
    UserApp getUserByEmail(String userEmail);

    @Select("""
            select email from accounts where email = #{userEmail}
            """)
    String getEmail(String userEmail);

    @Select("""
            insert into accounts(name, password, email)
            values(#{req.name}, #{req.password}, #{req.email})
            returning *
            """)
    UserApp insertUser(@Param("req")UserAppRequest userAppRequest);

    @Select("""
                select * from accounts where id = #{userId}
            """)
//    @ResultMap("userMapMapper")
    UserApp getUserById(@Param("userId") Integer userId);
}

package com.example.football_league.repository;

import org.apache.ibatis.annotations.*;

import java.util.UUID;
@Mapper
public interface EmailVerificationRepository {
    @Select("""
                insert into email_verification (user_id, code)
                values (#{user_id}, #{code})
            """)
    void insertEmailVerification(Integer user_id, String code);
}

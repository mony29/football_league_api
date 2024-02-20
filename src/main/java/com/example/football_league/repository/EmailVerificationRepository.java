package com.example.football_league.repository;

import com.example.football_league.model.entity.EmailVerification;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmailVerificationRepository {
    @Results(
            id = "emailMap",
            value = {
                    @Result(column = "user_id", property = "userId"),
                    @Result(column = "is_verified", property = "isVerified")
            }
    )
    @Select("""
            select * from email_verification where code = #{code}
            """)
    EmailVerification getEmailVerificationByCode(@Param("code") String code);

    @Select("""
                insert into email_verification (user_id, code)
                values (#{user_id}, #{code})
            """)
    void insertEmailVerification(Integer user_id, String code);

    @Update("""
            update email_verification set is_verified = true where code = #{code}
            """)
    void verifyCode(String code);
}

package org.green.backend.repository.dao.common;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.common.SecurityUserDto;
import org.green.backend.dto.common.StarDto;
import org.green.backend.dto.common.UserDto;
import org.green.backend.dto.company.ResponseJobNoticeDto;

import java.util.List;

/**
 * 패키지명        : org.green.backend.repository.dao.common
 * 파일명          : UserDao
 * 작성자          : 김상준
 * 일자            : 2024-12-27
 * 내용            : 사용자 Dao
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-27        김상준            최초 생성
 */

@Mapper
public interface UserDao {

    public UserDto findById(String id);

    public SecurityUserDto findByIdSecurity(String id);

    public int checkId(String id);

    public String checkPw(String id);

    public int save(@Param("user") UserDto user);

    public int edit(@Param("user") UserDto user);

    public List<ResponseJobNoticeDto> getPopJobNotices(@Param("id") String id);

    public List<ResponseJobNoticeDto> getShortJobNotices(@Param("id") String id);

    public List<ResponseJobNoticeDto> getLikeJobNotices(@Param("id") String id);

    public List<ResponseJobNoticeDto> passJobNotice(@Param("id") String id);

    public void insertStar(@Param("star") StarDto star);

}

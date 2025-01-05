package org.green.backend.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.green.backend.dto.admin.ResponseUserDto;

import java.util.List;

/**
 * 패키지명        : org.green.backend.repository.dao
 * 파일명          : AdminDao
 * 작성자          : 김상준
 * 일자            : 2025-01-04
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-04        김상준            최초 생성
 */

@Mapper
public interface AdminDao {

    public int userCnt(String search, String filter);

    public List<ResponseUserDto> userList(int offset, int limit, String search, String filter);

    public void status(String id);

}

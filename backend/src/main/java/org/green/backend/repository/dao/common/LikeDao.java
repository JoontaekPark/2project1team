package org.green.backend.repository.dao.common;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.common.LikeDto;

/**
 * 패키지명        : org.green.backend.repository.dao.common
 * 파일명          : LikeDao
 * 작성자          : 김상준
 * 일자            : 2025-01-03
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-03        김상준            최초 생성
 */

@Mapper
public interface LikeDao {

    public void save(@Param("like") LikeDto like);

    public void delete(@Param("like") LikeDto like);
}

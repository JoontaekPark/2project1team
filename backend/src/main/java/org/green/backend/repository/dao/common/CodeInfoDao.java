package org.green.backend.repository.dao.common;

import org.apache.ibatis.annotations.Mapper;
import org.green.backend.dto.common.CodeInfoDto;

import java.util.List;

/**
 * 패키지명        : org.green.backend.repository.dao.common
 * 파일명          : CodeInfoDao
 * 작성자          : 김상준
 * 일자            : 2024-12-27
 * 내용            : 공통코드 Dao
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-27        김상준            최초 생성
 */

@Mapper
public interface CodeInfoDao {

    List<CodeInfoDto> getCodeInfos(String upCd);

    CodeInfoDto getCodeInfo(String cd, String upCd);

    List<CodeInfoDto> getCodeInfosBySubCode(String upCd);

}

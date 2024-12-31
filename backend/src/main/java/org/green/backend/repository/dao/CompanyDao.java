package org.green.backend.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.company.CompanyDto;

/**
 * 패키지명        : org.green.backend.repository.dao
 * 파일명          : CompanyDao
 * 작성자          : 김상준
 * 일자            : 2024-12-31
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-31        김상준            최초 생성
 */

@Mapper
public interface CompanyDao {

    public void save(@Param("company")CompanyDto company);

}

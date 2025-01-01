package org.green.backend.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.company.CompanyDto;
import org.green.backend.dto.company.EmployeeDto;
import org.green.backend.dto.company.HistoryDto;
import org.green.backend.dto.company.RevenusDto;

import java.util.List;

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

    public void save(@Param("company") CompanyDto company);

    public void update(@Param("company") CompanyDto company);

    public CompanyDto get(@Param("id") String id);

    public List<RevenusDto> getRevenuses(@Param("id") String id);

    public List<EmployeeDto> getEmployees(@Param("id") String id);

    public List<HistoryDto> getHistories(@Param("id") String id);

    public void deleteRevenue(String id);

    public void deleteEmployee(String id);

    public void deleteHistory(String id);

    public void saveRevenue(@Param("revenue") RevenusDto revenus);

    public void saveEmployee(@Param("employee") EmployeeDto employee);

    public void saveHistory(@Param("history") HistoryDto history);
}


package org.green.frontend.dto.company;

import lombok.Data;

import java.util.List;

/**
 * 패키지명        : org.green.backend.dto.company
 * 파일명          : CompanyDto
 * 작성자          : 김상준
 * 일자            : 2024-12-31
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-31        김상준            최초 생성
 */

@Data
public class CompanyDto {
    // 아이디
    private String id;
    // 대표자명
    private String ceoName;
    // 사업자등록번호 (-)없이
    private Long businessNum;
    // 홈페이지
    private String homepage;
    // 기업형태 (중소, 중견, 대)
    private String companyTypeCd;
    // 업종 (텍스트)
    private String companyIndustry;
    // 기업설명
    private String companyDetail;
    // 사원수 리스트
    List<EmployeeDto> employee;
    // 연혁 리스트
    List<HistoryDto> history;
    // 매출액 리스트
    List<RevenusDto> revenuse;
}

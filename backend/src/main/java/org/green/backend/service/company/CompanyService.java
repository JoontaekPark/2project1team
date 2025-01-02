package org.green.backend.service.company;

import org.green.backend.dto.company.CompanyDto;
import org.green.backend.dto.company.ResponseCompanyDto;
import org.green.backend.dto.company.ResponseJobNoticeDto;
import org.green.backend.dto.company.StarDto;

import java.util.List;
import java.util.Map;

/**
 * 패키지명        : org.green.backend.service.company
 * 파일명          : CompanyService
 * 작성자          : 김상준
 * 일자            : 2024-12-31
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-31        김상준            최초 생성
 */


public interface CompanyService {

    public CompanyDto getCompany(String token);

    public ResponseCompanyDto companyInfo(String companyId, String token);

    public void save(CompanyDto company);

    public void update(CompanyDto company, String token);

    public Map<String, Object> companyExtraInfo(String companyId);

    public List<ResponseJobNoticeDto> getJobNotices(String token);

    public List<StarDto> getStars(String token);

    public Map<String, Object> companyMain(int jobNoticeNum);
}

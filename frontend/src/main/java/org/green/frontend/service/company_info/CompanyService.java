package org.green.frontend.service.company_info;

import org.green.frontend.dto.company.CompanyDto;
import org.green.frontend.dto.company.ResponseCompanyDto;

/**
 * 패키지명        : org.green.frontend.service.company_info
 * 파일명          : CompanySerivce
 * 작성자          : 김상준
 * 일자            : 2025-01-01
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-01        김상준            최초 생성
 */

public interface CompanyService {

    public CompanyDto getCompany() throws Exception;

    public ResponseCompanyDto companyInfo(String companyId) throws Exception;

}

package org.green.frontend.service.company;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.company.CompanyDto;
import org.green.frontend.dto.company.ResponseCompanyDto;
import org.green.frontend.dto.company.ResponseJobNoticeDto;
import org.green.frontend.global.common.ApiResponse;
import org.green.frontend.utils.WebClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 패키지명        : org.green.frontend.service.company_info
 * 파일명          : CompanyServiceImpl
 * 작성자          : 김상준
 * 일자            : 2025-01-01
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-01        김상준            최초 생성
 */

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final WebClientUtil webClientUtil;

    @Override
    public CompanyDto getCompany() throws Exception {

        ApiResponse<CompanyDto> response = webClientUtil.getApi("/api/v1/company-info/edit", CompanyDto.class);
//뒤에꺼 매개변수에 (url,반환타입)
        return response.getBody();
    }

    @Override
    public ResponseCompanyDto companyInfo(String companyId) throws Exception {
        ApiResponse<ResponseCompanyDto> response = webClientUtil.getApi("/api/v1/company-info/" + companyId, ResponseCompanyDto.class);

        return response.getBody();
    }

    @Override
    public List<ResponseJobNoticeDto> jobNoticeInfo() throws Exception {
        ApiResponse<List> response = webClientUtil.getApi("/api/v1/company-job-notice", List.class);
        return response.getBody();
    }
}

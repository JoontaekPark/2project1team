package org.green.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.green.backend.dto.company.CompanyDto;
import org.green.backend.dto.company.ResponseCompanyDto;
import org.green.backend.service.company.CompanyService;
import org.green.backend.utils.CookieUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 패키지명        : org.green.backend.controller
 * 파일명          : CompanyController
 * 작성자          : 김상준
 * 일자            : 2025-01-01
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-01        김상준            최초 생성
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CompanyController {

    private final CookieUtil cookieUtil;
    private final CompanyService companyService;

    @PutMapping("/company-info")
    public void companyInfo(@RequestBody CompanyDto company,
                            HttpServletRequest request) {

        String token = cookieUtil.getCookie(request, "Authorization");
        System.out.println(company);
        companyService.update(company, token);
    }

    @GetMapping("/company-info/edit")
    public CompanyDto companyInfoEdit(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        return companyService.getCompany(token);
    }

    @GetMapping("/company-info/{companyId}")
    public ResponseCompanyDto companyInfoDetail(@PathVariable String companyId,
                                                HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        return companyService.companyInfo(companyId, token);
    }

    @GetMapping("/company-info/extra/{companyId}")
    public Map<String, Object> companyInfoExtraDetail(@PathVariable String companyId) {
        return companyService.companyExtraInfo(companyId);
    }
}

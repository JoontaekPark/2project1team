package org.green.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 패키지명        : org.green.frontend.controller
 * 파일명          : CompanyController
 * 작성자          : 김상준
 * 일자            : 2024-12-31
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-31        김상준            최초 생성
 */

@Controller
public class CompanyController {

    @GetMapping("/company-info/edit")
    public String companyInfo() {
        return "company_info/company_info_edit";
    }

    @GetMapping("/company-info/{id}")
    public String companyInfo(@PathVariable String id) {
        return "company_info";
    }

}

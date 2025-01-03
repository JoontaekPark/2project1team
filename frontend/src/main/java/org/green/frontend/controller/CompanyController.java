package org.green.frontend.controller;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.dto.company.CompanyDto;
import org.green.frontend.dto.company.ResponseCompanyDto;
import org.green.frontend.service.common.CodeInfoService;
import org.green.frontend.service.company_info.CompanyService;
import org.green.frontend.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
@RequiredArgsConstructor
public class CompanyController {

    private final CodeInfoService codeInfoService;
    private final CompanyService companyService;

    @GetMapping("/company-info/edit")
    public String companyInfo(Model model) throws Exception {

        List<CodeInfoDto> companyTypeCd = codeInfoService.getCodeInfos("company_type_cd");
        CompanyDto company = companyService.getCompany();

        model.addAttribute("companyTypeCd", companyTypeCd);
        model.addAttribute("company", company);
        System.out.println(company);
        return "company_info/company_info_edit";
    }

    @GetMapping("/company-info/{companyId}")
    public String companyInfo(@PathVariable String companyId,
                              Model model) throws Exception {
        ResponseCompanyDto companyInfo = companyService.companyInfo(companyId);
        System.out.println(companyInfo);
        model.addAttribute("companyInfo", companyInfo);

        return "company_info/company_info";
    }

}

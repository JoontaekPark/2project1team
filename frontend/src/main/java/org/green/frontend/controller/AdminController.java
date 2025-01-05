package org.green.frontend.controller;

import lombok.RequiredArgsConstructor;
import org.green.frontend.service.admin.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 패키지명        : org.green.frontend.controller
 * 파일명          : admin
 * 작성자          : 김상준
 * 일자            : 2025-01-04
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-04        김상준            최초 생성
 */

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin")
    public String admin(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "search", defaultValue = "") String search,
                        @RequestParam(value = "filter", defaultValue = "") String filter,
                        Model model) throws Exception {

        Map<String, Object> result = adminService.adminMain(page, search, filter);

        model.addAttribute("paging", result.get("paging"));
        model.addAttribute("list", result.get("list"));
        model.addAttribute("userGbnList", result.get("userGbnList"));
        model.addAttribute("search", search);
        model.addAttribute("filter", filter);

        return "admin_main";
    }

}

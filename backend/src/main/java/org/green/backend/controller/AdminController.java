package org.green.backend.controller;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.green.backend.service.admin.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 패키지명        : org.green.backend.controller
 * 파일명          : AdminController
 * 작성자          : 김상준
 * 일자            : 2025-01-04
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-04        김상준            최초 생성
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public Map<String, Object> adminMain(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "search", defaultValue = "") String search,
                                         @RequestParam(value = "filter", defaultValue = "") String filter) {

        System.out.println(page);
        System.out.println(search);
        System.out.println(filter);

        return adminService.adminMain(page, search, filter);
    }

    @PostMapping("/status")
    public void status(@RequestBody List<String> ids) {

        adminService.changeStatus(ids);
    }

}

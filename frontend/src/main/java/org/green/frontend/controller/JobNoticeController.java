package org.green.frontend.controller;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.service.JobNotice.JobNoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created on 2024-12-30 by 최윤서
 */
@Controller
@RequiredArgsConstructor
public class JobNoticeController {

    private final JobNoticeService jobNoticeService;

    @GetMapping("/job-notice-form")
    public String registForm(Model model) throws Exception {

        List<CodeInfoDto> jobNoticeEducationGbnCdList = jobNoticeService.educationInfo();
        List<CodeInfoDto> jobNoticeCareerGbnCdList = jobNoticeService.careerInfo();
        List<CodeInfoDto> jobNoticeStackGbnCdList = jobNoticeService.stackInfo();

        model.addAttribute("jobNoticeEducationGbnCdList", jobNoticeEducationGbnCdList);
        model.addAttribute("jobNoticeCareerGbnCdList", jobNoticeCareerGbnCdList);
        model.addAttribute("jobNoticeStackGbnCdList", jobNoticeStackGbnCdList);

        return "job_notice/regist_job_notice_form";
    }

}

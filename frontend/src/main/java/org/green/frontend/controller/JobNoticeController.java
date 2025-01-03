package org.green.frontend.controller;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.JobNotice.ApplyStatusResponseDto;
import org.green.frontend.dto.JobNotice.JobNoticeResponseDto;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.service.JobNotice.JobNoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2024-12-30 by 최윤서
 */
@Controller
@RequiredArgsConstructor
public class JobNoticeController {

    private final JobNoticeService jobNoticeService;

    //등록
    @GetMapping("/job-notice-form")
    public String registForm(Model model) throws Exception {

        List<CodeInfoDto> jobNoticeWorkGbnCdList = jobNoticeService.workTypeInfo();
        List<CodeInfoDto> jobNoticeEducationGbnCdList = jobNoticeService.educationInfo();
        List<CodeInfoDto> jobNoticeCareerGbnCdList = jobNoticeService.careerInfo();
        List<CodeInfoDto> jobNoticeStackGbnCdList = jobNoticeService.stackInfo();

        model.addAttribute("jobNoticeWorkGbnCdList", jobNoticeWorkGbnCdList);
        model.addAttribute("jobNoticeEducationGbnCdList", jobNoticeEducationGbnCdList);
        model.addAttribute("jobNoticeCareerGbnCdList", jobNoticeCareerGbnCdList);
        model.addAttribute("jobNoticeStackGbnCdList", jobNoticeStackGbnCdList);

        return "job_notice/regist_job_notice_form";
    }

    //조회
    @GetMapping("/job-notice-detail/{jobNoticeNum}")
    public String jobNoticeDetail(@PathVariable("jobNoticeNum") int jobNoticeNum, Model model) throws Exception {
        JobNoticeResponseDto dto = jobNoticeService.getJobNoticeDetail(jobNoticeNum);
        System.out.println("frontController :" + dto);
//        dto.setVCnt(dto.getVCnt() + 1);

        //지원현황
        List<ApplyStatusResponseDto> applyStatusList = jobNoticeService.getApplyStatusList(jobNoticeNum);
        List<CodeInfoDto> applyGbnCdList = jobNoticeService.applyInfo();



        model.addAttribute("jobNotice", dto);
        model.addAttribute("applyGbnCdList", applyGbnCdList);
        model.addAttribute("applyStatusList", applyStatusList);
//        model.addAttribute("selectedStatus", selectedStatus);

        return "job_notice/job_notice_detail";
    }


}

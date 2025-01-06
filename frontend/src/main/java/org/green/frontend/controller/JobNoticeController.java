package org.green.frontend.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.JobNotice.ApplyStatusRequestDto;
import org.green.frontend.dto.JobNotice.ApplyStatusResponseDto;
import org.green.frontend.dto.JobNotice.JobNoticeResponseDto;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.dto.common.SessionDto;
import org.green.frontend.dto.resume.ResumeDto;
import org.green.frontend.global.common.ApiResponse;
import org.green.frontend.service.JobNotice.JobNoticeService;
import org.green.frontend.utils.WebClientUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2024-12-30 by 최윤서
 */
@Controller
@RequiredArgsConstructor
public class JobNoticeController {

    private final JobNoticeService jobNoticeService;
    private final WebClientUtil webClientUtil;

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
    public String jobNoticeDetail(@PathVariable("jobNoticeNum") int jobNoticeNum, Model model, HttpSession session) throws Exception {
        JobNoticeResponseDto dto = jobNoticeService.getJobNoticeDetail(jobNoticeNum);
        System.out.println("frontController :" + dto);

        //(준택) 로그인한 유저가 작성한 이력서 리스트 불러오기 위한 로직
        SessionDto user = (SessionDto) session.getAttribute("user");
        String instId = user.getId();
        ApiResponse<List> resumesInfo = webClientUtil.getApi("/resume/get-resumelist?instId=" + instId, List.class);
        List<ResumeDto> resumes = resumesInfo.getBody();
        model.addAttribute("resumes", resumes);
        System.out.println("frontController :" + resumes);
        //----------------------------------------준택끝---------------------

        //지원현황
        List<ApplyStatusResponseDto> applyStatusList = jobNoticeService.getApplyStatusList(jobNoticeNum);
        List<CodeInfoDto> applyGbnCdList = jobNoticeService.applyInfo();

        model.addAttribute("jobNotice", dto);
        model.addAttribute("applyGbnCdList", applyGbnCdList);
        model.addAttribute("applyStatusList", applyStatusList);
//        model.addAttribute("selectedStatus", selectedStatus);
        
        
        
        
        
        return "job_notice/job_notice_detail";
    }

    //공고 리스트 조회
    @GetMapping("/job-notice-list")
    public String jobNoticeList(Model model) throws Exception {
        //채용중
        List<JobNoticeResponseDto> list = jobNoticeService.getJobNoticeList("20");
        model.addAttribute("jobNoticeList", list);
        //임시저장
        List<JobNoticeResponseDto> tempNoticeList = jobNoticeService.getJobNoticeList("10");
        model.addAttribute("tempNoticeList", tempNoticeList);
        //마감공고
        List<JobNoticeResponseDto> endNoticeList = jobNoticeService.getJobNoticeList("30");
        model.addAttribute("endNoticeList", endNoticeList);
        return "job_notice/job_notice_list";
    }




}

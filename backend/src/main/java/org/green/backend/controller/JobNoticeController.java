package org.green.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.green.backend.dto.JobNotice.ApplyStatusRequestDto;
import org.green.backend.dto.JobNotice.ApplyStatusResponseDto;
import org.green.backend.dto.JobNotice.JobNoticeRequestDto;
import org.green.backend.dto.JobNotice.JobNoticeResponseDto;
import org.green.backend.service.JobNotice.JobNoticeImpl;
import org.green.backend.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created on 2024-12-27 by 최윤서
 */
//@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class JobNoticeController {

    @Autowired
    private JobNoticeImpl jobNoticeService;

    @Autowired
    private CookieUtil cookieUtil;

    // 리스트 조회
    @GetMapping("job-notice-list")
    public ResponseEntity<List<JobNoticeResponseDto>> getJobNoticeList(@RequestParam("jobNoticeStatus") String jobNoticeStatus, HttpServletRequest request){

        String token = request.getHeader("Authorization");
        List<JobNoticeResponseDto> list = jobNoticeService.getJobNoticeList(jobNoticeStatus, token);
        return ResponseEntity.ok(list);
    }

    //    조회
        @GetMapping("/job-notice")
        public ResponseEntity<JobNoticeResponseDto> getJobNotice(@RequestParam("jobNoticeNum") int jobNoticeNum,
                                                                 HttpServletRequest request) {

            String token = request.getHeader("Authorization");
//            System.out.println("ssssssssssssssssssssssssssssss");
            System.out.println(token);
            JobNoticeResponseDto dto = jobNoticeService.getJobNoticeDetails(jobNoticeNum, token);
            System.out.println("backController : "+dto.toString());
            return ResponseEntity.ok(dto);
        }

//    등록
    @PostMapping("/job-notice")
    public String createJobNotice(
            @ModelAttribute JobNoticeRequestDto JobNoticeRequestDto, HttpServletRequest request)
            throws IOException {
        String token = cookieUtil.getCookie(request, "Authorization");
        System.out.println("Received dto: " + JobNoticeRequestDto);
        jobNoticeService.createJobNotice(JobNoticeRequestDto, token);
        return "등록완료";
    }

    //지원현황 조회
    @GetMapping("/status-list")
    public ResponseEntity<List<ApplyStatusResponseDto>> getApplyList(@RequestParam("jobNoticeNum") int jobNoticeNum,  HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        List<ApplyStatusResponseDto> list = jobNoticeService.getApplyStatusList(jobNoticeNum, token);
        System.out.println("backController : "+list.toString());
        return ResponseEntity.ok(list);
    }

    //지원현황 업데이트
    @PutMapping("/apply-status")
    public String updateStatus(@RequestBody ApplyStatusRequestDto dto) throws IOException {

        System.out.println("jobNoticeNum: " + dto.getJobNoticeNum());
        System.out.println("resumeId: " + dto.getResumeId());
        System.out.println("applyGbnCd: " + dto.getApplyGbnCd());

        jobNoticeService.updateStatus(dto);
        return "success";
    }

    //공고 마감처리
    @PutMapping("/job-notice-status")
    public String updateNoticeStatus(@RequestParam("jobNoticeNum")int jobNoticeNum) throws  IOException{
        jobNoticeService.updateNoticeStatus(jobNoticeNum);
        return "success";
    }

}

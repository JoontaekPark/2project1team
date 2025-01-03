package org.green.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.green.backend.dto.JobNotice.ApplyStatusResponseDto;
import org.green.backend.dto.JobNotice.JobNoticeRequestDto;
import org.green.backend.dto.JobNotice.JobNoticeResponseDto;
import org.green.backend.service.JobNotice.JobNoticeImpl;
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

    //    조회
        @GetMapping("/job-notice")
        public ResponseEntity<JobNoticeResponseDto> getJobNotice(@RequestParam("jobNoticeNum") int jobNoticeNum) {
            JobNoticeResponseDto dto = jobNoticeService.getJobNoticeDetails(jobNoticeNum);
            System.out.println("backController : "+dto.toString());
            return ResponseEntity.ok(dto);
        }

//    등록
    @PostMapping("/job-notice")
    public String createJobNotice(
            @ModelAttribute JobNoticeRequestDto JobNoticeRequestDto)
            throws IOException {
        System.out.println("Received dto: " + JobNoticeRequestDto);
        jobNoticeService.createJobNotice(JobNoticeRequestDto);
        return "등록완료";
    }

    //지원현황
    @GetMapping("/status-list")
    public ResponseEntity<List<ApplyStatusResponseDto>> getApplyList(@RequestParam("jobNoticeNum") int jobNoticeNum) {
        List<ApplyStatusResponseDto> list = jobNoticeService.getApplyStatusList(jobNoticeNum);
        System.out.println("backController : "+list.toString());
        return ResponseEntity.ok(list);
    }
    
}

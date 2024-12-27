package org.green.backend.controller;

import org.green.backend.dto.JobNotice.JobNoticeResponseDto;
import org.green.backend.service.JobNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2024-12-27 by 최윤서
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class JobNoticeController {

    @Autowired
    private JobNoticeService jobNoticeService;

    @GetMapping("/job-notice")
    public JobNoticeResponseDto getJobNotice(@RequestParam("JobNoticeNum")int JobNoticeNum){
        JobNoticeResponseDto dto = jobNoticeService.getJobNoticeDetails(JobNoticeNum);
        return dto;
    }



}

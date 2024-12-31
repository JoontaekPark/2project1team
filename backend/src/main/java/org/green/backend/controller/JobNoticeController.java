package org.green.backend.controller;

import org.green.backend.dto.JobNotice.JobNoticeResponseDto;
import org.green.backend.service.JobNotice.JobNoticeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2024-12-27 by 최윤서
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class JobNoticeController {

    @Autowired
    private JobNoticeImpl jobNoticeService;

    @GetMapping("/job-notice")
    public JobNoticeResponseDto getJobNotice(@RequestParam("JobNoticeNum")int JobNoticeNum){
        JobNoticeResponseDto dto = jobNoticeService.getJobNoticeDetails(JobNoticeNum);
        return dto;
    }



}

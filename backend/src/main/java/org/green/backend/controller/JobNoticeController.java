package org.green.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.green.backend.dto.JobNotice.JobNoticeRequestDto;
import org.green.backend.dto.JobNotice.JobNoticeResponseDto;
import org.green.backend.service.JobNotice.JobNoticeImpl;
import org.green.backend.service.common.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private FileService fileService;

    @GetMapping("/job-notice")
    public JobNoticeResponseDto getJobNotice(@RequestParam("JobNoticeNum") int JobNoticeNum) {
        JobNoticeResponseDto dto = jobNoticeService.getJobNoticeDetails(JobNoticeNum);
        return dto;
    }

    @PostMapping("/job-notice")
    public String createJobNotice(
            @ModelAttribute JobNoticeRequestDto JobNoticeRequestDto)
            throws IOException {
        System.out.println("Received dto: " + JobNoticeRequestDto);
        jobNoticeService.createJobNotice(JobNoticeRequestDto);
        return "등록완료";
    }
    
}

package org.green.backend.controller;

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
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
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
    public JobNoticeRequestDto createJobNotice(@RequestBody JobNoticeRequestDto dto) throws IOException {
//        List<MultipartFile> jobNoticeImages = fileService.saveFile(파일, 구분코드, 관련id , 아이디);

        jobNoticeService.createJobNotice(dto);
        return dto;
    }


}

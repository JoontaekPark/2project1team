package org.green.backend.service.JobNotice;

import org.green.backend.dto.JobNotice.*;
import org.green.backend.repository.dao.JobNoticeDao;
import org.green.backend.service.common.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created on 2024-12-27 by 최윤서
 */
@Service
public class JobNoticeImpl implements JobNoticeService {

    @Autowired
    private JobNoticeDao jobNoticeDao;
    @Autowired
    private FileService fileService;

    //채용공고 조회
    public JobNoticeResponseDto getJobNoticeDetails(int jobNoticeNum) {
        List<String> StepList = jobNoticeDao.getStep(jobNoticeNum);
        List<String> WelfareList = jobNoticeDao.getWelfare(jobNoticeNum);
        List<String> StackList = jobNoticeDao.getStack(jobNoticeNum);

        JobNoticeResponseDto dto = jobNoticeDao.getJobNoticeDetails(jobNoticeNum);
        dto.setStepList(StepList);
        dto.setWelfareList(WelfareList);
        dto.setStackList(StackList);
        return dto;
    }

    //채용공고 등록
    @Override
    @Transactional
    public void createJobNotice(JobNoticeRequestDto dto) throws IOException {
        // 1. 채용 공고 정보 등록
        jobNoticeDao.registJobNotice(dto);

        int jobNoticeNum = dto.getJobNoticeNum();
        System.out.println("jobNoticeNum : "+jobNoticeNum);

        // 파일 등록
        if (!dto.getFiles().isEmpty() && dto.getFiles().size() > 0) {
            for (MultipartFile file : dto.getFiles()) {
                fileService.saveFile(file, "20", String.valueOf(jobNoticeNum), dto.getInstId());
            }
        }
        // 2. 기술 스택 등록
        jobNoticeDao.registStacks(jobNoticeNum, dto.getStackList());

        // 3. 절차 등록
        jobNoticeDao.registSteps(jobNoticeNum, dto.getStepList());

        // 4. 복리후생 등록
        jobNoticeDao.registWelfares(jobNoticeNum, dto.getWelfareList());
    }


    //지원현황 조회
    @Override
    public List<ApplyStatusResponseDto> getApplyStatusList(int jobNoticeNum) {
        List<ApplyStatusResponseDto> list = jobNoticeDao.getApplyStatusList(jobNoticeNum);
        // stack 문자열 리스트로 변환
        for (ApplyStatusResponseDto dto : list) {
            String stackNames = dto.getStackNames();
                List<String> stackCdList = Arrays.asList(stackNames.split(","));
                dto.setStackCdList(stackCdList);
        }

        //M/F 성별 변환
        for(ApplyStatusResponseDto dto : list){
            if(Objects.equals(dto.getGenderCd(), "M")){
                dto.setGenderCd("남");
            }else if(Objects.equals(dto.getGenderCd(), "F")){
                dto.setGenderCd("여");
            }
        }

        return list;
    }


}

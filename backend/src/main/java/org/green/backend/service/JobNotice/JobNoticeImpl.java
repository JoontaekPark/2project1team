package org.green.backend.service.JobNotice;

import org.green.backend.dto.JobNotice.*;
import org.green.backend.dto.common.FileDto;
import org.green.backend.repository.dao.JobNoticeDao;
import org.green.backend.service.common.FileService;
import org.green.backend.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
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

    @Autowired
    private JWTUtil jwtUtil;

    //채용공고 리스트 조회
    @Override
    public List<JobNoticeResponseDto> getJobNoticeList(String jobNoticeStatus, String token) {
        String instId = jwtUtil.getId(token);
        List<JobNoticeResponseDto> list = jobNoticeDao.getJobNoticeList(jobNoticeStatus, instId);
        System.out.println(jobNoticeStatus);
        return list;
    }

    //채용공고 조회
    public JobNoticeResponseDto getJobNoticeDetails(int jobNoticeNum, String token) {
        List<String> StepList = jobNoticeDao.getStep(jobNoticeNum);
        List<String> WelfareList = jobNoticeDao.getWelfare(jobNoticeNum);
        List<String> StackList = jobNoticeDao.getStack(jobNoticeNum);

        //사진파일 조회
        List<FileDto> fileList = fileService.findAllByFilesGbnCdAndFileRefId("20", String.valueOf(jobNoticeNum));
        System.out.println(fileList);

        String id = jwtUtil.getId(token);
        System.out.println("id " + id);
        JobNoticeResponseDto dto = jobNoticeDao.getJobNoticeDetails(jobNoticeNum, id);
        jobNoticeDao.increaseVcnt(jobNoticeNum);
        dto.setFileList(fileList);
        dto.setStepList(StepList);
        dto.setWelfareList(WelfareList);
        dto.setStackList(StackList);
        return dto;
    }

    //채용공고 등록
    @Override
    @Transactional
    public void createJobNotice(JobNoticeRequestDto dto, String token) throws IOException {

        String instId = "";

        if (token != null) {
            instId = jwtUtil.getId(token);
        }

        dto.setInstId(instId);

        // 1. 채용 공고 정보 등록
        jobNoticeDao.registJobNotice(dto);

        int jobNoticeNum = dto.getJobNoticeNum();
        System.out.println("jobNoticeNum : "+jobNoticeNum);


        // 파일 등록
        if (!dto.getFiles().isEmpty() && dto.getFiles().size() > 0) {
            for (MultipartFile file : dto.getFiles()) {
                fileService.saveFile(file, "20", String.valueOf(jobNoticeNum), instId);
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
    public List<ApplyStatusResponseDto> getApplyStatusList(int jobNoticeNum, String token) {
        List<ApplyStatusResponseDto> list = jobNoticeDao.getApplyStatusList(jobNoticeNum, token);

//        String id = jwtUtil.getId(token);

        // stack 문자열 리스트로 변환
//        for (ApplyStatusResponseDto dto : list) {
//            String stackNames = dto.getStackNames();
//                List<String> stackCdList = Arrays.asList(stackNames.split(","));
//                dto.setStackCdList(stackCdList);
//        }
        for (ApplyStatusResponseDto dto : list) {
            String stackNames = dto.getStackNames();
            if (stackNames != null && !stackNames.isEmpty()) {
                List<String> stackCdList = Arrays.asList(stackNames.split(","));
                dto.setStackCdList(stackCdList);
            } else {
                dto.setStackCdList(Collections.emptyList()); // stackNames가 null이거나 비어있을 경우 빈 리스트 설정
            }
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

    //지원현황 업데이트
    @Override
    public void updateStatus(ApplyStatusRequestDto dto) throws IOException {
        jobNoticeDao.updateStatus(dto);
    }

    @Override
    public void updateNoticeStatus(int jobNoticeNum) throws IOException {
        jobNoticeDao.updateNoticeStatus(jobNoticeNum);
        jobNoticeDao.updateNoticeApplyStatus(jobNoticeNum);
    }

}

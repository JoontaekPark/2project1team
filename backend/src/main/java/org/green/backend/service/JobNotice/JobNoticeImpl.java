package org.green.backend.service.JobNotice;

import org.green.backend.dto.JobNotice.JobNoticeRequestDto;
import org.green.backend.dto.JobNotice.JobNoticeResponseDto;
import org.green.backend.repository.dao.JobNoticeDao;
import org.green.backend.service.common.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created on 2024-12-27 by 최윤서
 */
@Service
public class JobNoticeImpl implements JobNoticeService {

    @Autowired
    private JobNoticeDao jobNoticeDao;
    private FileService fileService;

    //채용공고 조회
    public JobNoticeResponseDto getJobNoticeDetails(int jobNoticeNum) {
        return jobNoticeDao.getJobNoticeDetails(jobNoticeNum);
    }

    //채용공고 등록
    @Override
    @Transactional
    public void createJobNotice(JobNoticeRequestDto dto) throws IOException {
        // 1. 채용 공고 정보 등록
        jobNoticeDao.registJobNotice(dto);

        // 파일 등록
        if (dto.getJobNoticeImage() != null) {
            for (MultipartFile file : dto.getJobNoticeImage()) {
                String refId = String.valueOf(dto.getJobNoticeNum());
                fileService.saveFile(file, "20", refId, dto.getInstId());
            }
        }

        // 2. 기술 스택 등록
        jobNoticeDao.registStacks(dto.getJobNoticeNum(), dto.getStackList());

        // 3. 절차 등록
        jobNoticeDao.registSteps(dto.getJobNoticeNum(), dto.getStepList());

        // 4. 복리후생 등록
        jobNoticeDao.registWelfares(dto.getJobNoticeNum(), dto.getWelfareList());
    }


}

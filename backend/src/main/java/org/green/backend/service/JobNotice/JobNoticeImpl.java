package org.green.backend.service.JobNotice;

import org.green.backend.dto.JobNotice.JobNoticeResponseDto;
import org.green.backend.repository.dao.JobNoticeDao;
import org.green.backend.service.common.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2024-12-27 by 최윤서
 */
@Service
public class JobNoticeImpl implements JobNoticeService {

    @Autowired
    private JobNoticeDao jobNoticeDao;
    private FileService fileService;

    //채용공고 조회
    public JobNoticeResponseDto getJobNoticeDetails(int jobNoticeNum){
        return jobNoticeDao.getJobNoticeDetails(jobNoticeNum);
    }

    //채용공고 등록
    @Override
    public JobNoticeResponseDto registJobNotice(JobNoticeResponseDto dto) {

        return null;
//                jobNoticeDao.registJobNotice(JobNoticeResponseDto dto);
    }


}

package org.green.backend.repository.dao;


import org.apache.ibatis.annotations.Mapper;
import org.green.backend.dto.JobNotice.JobNoticeResponseDto;

/**
 * Created on 2024-12-27 by 최윤서
 */
@Mapper
public interface JobNoticeDao {

    //    채용공고 상세정보 조회
    public JobNoticeResponseDto getJobNoticeDetails(int jobNoticeNum);
}

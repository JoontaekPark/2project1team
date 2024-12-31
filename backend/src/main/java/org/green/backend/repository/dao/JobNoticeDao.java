package org.green.backend.repository.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.JobNotice.JobNoticeRequestDto;
import org.green.backend.dto.JobNotice.JobNoticeResponseDto;

import java.util.List;

/**
 * Created on 2024-12-27 by 최윤서
 */
@Mapper
public interface JobNoticeDao {

    //채용공고 상세정보 조회
    public JobNoticeResponseDto getJobNoticeDetails(int jobNoticeNum);

    // 등록
    public void registJobNotice(JobNoticeRequestDto dto);
    public void registStacks(@Param("jobNoticeNum") int jobNoticeNum, @Param("stackList") List<String> stackList);
    public void registWelfares(@Param("jobNoticeNum") int jobNoticeNum, @Param("welfareList") List<String> welfareList);
    public void registSteps(@Param("jobNoticeNum") int jobNoticeNum, @Param("stepList") List<String> stepList);

}

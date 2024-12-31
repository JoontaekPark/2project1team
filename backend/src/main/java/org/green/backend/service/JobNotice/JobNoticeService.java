package org.green.backend.service.JobNotice;

import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.JobNotice.JobNoticeRequestDto;
import org.green.backend.dto.JobNotice.JobNoticeResponseDto;

import java.io.IOException;
import java.util.List;

/**
 * Created on 2024-12-30 by 최윤서
 */
public interface JobNoticeService {

    //리스트


    //조회
    public JobNoticeResponseDto getJobNoticeDetails(int jobNoticeNum);

    //등록
    void createJobNotice(JobNoticeRequestDto dto) throws IOException;
//    void registStacks(@Param("jobNoticeNum") int jobNoticeNum, @Param("stackList") List<String> stackList);
//    void registWelfares(@Param("jobNoticeNum") int jobNoticeNum, @Param("welfareList") List<String> welfareList);
//    void registSteps(@Param("jobNoticeNum") int jobNoticeNum, @Param("stepList") List<String> stepList);


    //수정

    //삭제
}

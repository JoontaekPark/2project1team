package org.green.backend.repository.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.JobNotice.*;
import org.green.backend.dto.common.FileDto;

import java.util.List;

/**
 * Created on 2024-12-27 by 최윤서
 */
    @Mapper
    public interface JobNoticeDao {

        //채용공고 상세정보 조회
        public JobNoticeResponseDto getJobNoticeDetails(@Param("jobNoticeNum") int jobNoticeNum, @Param("Id") String Id);
        public List<String> getStep(int jobNoticeNum);
        public List<String> getWelfare(int jobNoticeNum);
        public List<String> getStack(int jobNoticeNum);


        //조회수 증가
        public void increaseVcnt(int jobNoticeNum);

        // 등록
        public void registJobNotice(JobNoticeRequestDto dto);
        public void registStacks(@Param("jobNoticeNum") int jobNoticeNum, @Param("stackList") List<String> stackList);
        public void registSteps(@Param("jobNoticeNum") int jobNoticeNum, @Param("stepList") List<String> stepList);
        public void registWelfares(@Param("jobNoticeNum") int jobNoticeNum, @Param("welfareList") List<String> welfareList);

        // 지원현황 조회
        public List<ApplyStatusResponseDto> getApplyStatusList(int jobNoticeNum);

        //지원 수정
        public void updateStatus(@Param("dto")ApplyStatusRequestDto dto);
    }

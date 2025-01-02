package org.green.backend.service.resume;

import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.common.UserDto;
import org.green.backend.dto.resume.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public interface ResumeService {

    public void insertResumeBase(int resumeId,ResumeDto resumeDto) throws IOException;

    public int getResumeId();

    public void insertResumeActive(int resumeId, List<ResumeActiveDto> resumeActiveDto);

    public void insertResumeCareer(int resumeId,List<ResumeCareerDto> resumeCareerDto);

    public void insertResumeCerts(int resumeId,List<ResumeCertsDto> resumeCertsDto);

    public void insertResumeEducation(int resumeId,List<ResumeEducationDto> resumeEducationDto);

    public void insertResumeIntroduce(int resumeId,List<ResumeIntroduceDto> resumeIntroduceDto);

    public void insertResumeLoyalty(int resumeId,List<ResumeLoyaltyDto> resumeLoyaltyDto);

    public void insertResumeMilitary(int resumeId,List<ResumeMilitaryDto> resumeMilitaryDto);

    public void insertResumePrtf(int resumeId,List<ResumePrtfDto> resumePrtfDto) throws IOException;

    public void insertResumeStack(int resumeId,List<ResumeStackDto> resumeStackDto);


}
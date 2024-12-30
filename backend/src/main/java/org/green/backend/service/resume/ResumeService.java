package org.green.backend.service.resume;

import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.resume.*;
import org.springframework.web.bind.annotation.RequestParam;

public interface ResumeService {

    public void insertResumeBase(@RequestParam("resume") ResumeDto resumeDto);

    public int getResumeId();

    public void insertResumeActive(int resumeId, ResumeActiveDto resumeActiveDto);

    public void insertResumeCareer(int resumeId,ResumeCareerDto resumeCareerDto);

    public void insertResumeCerts(int resumeId,ResumeCertsDto resumeCertsDto);

    public void insertResumeEducation(int resumeId,ResumeEducationDto resumeEducationDto);

    public void insertResumeIntroduce(int resumeId,ResumeIntroduceDto resumeIntroduceDto);

    public void insertResumeLoyalty(int resumeId,ResumeLoyaltyDto resumeLoyaltyDto);

    public void insertResumeMilitary(int resumeId,ResumeMilitaryDto resumeMilitaryDto);

    public void insertResumePrtf(int resumeId,ResumePrtfDto resumePrtfDto);

    public void insertResumeStack(int resumeId,ResumeStackDto resumeStackDto);


}

package org.green.backend.repository.dao.resume;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.resume.*;

/**
 * packageName    : org.green.backend.repository.dao.resume
 * fileName       : ResumeDao
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Mapper
public interface ResumeDao {
    public void insertResumeBase(@Param("resume") ResumeDto resumeDto);

    public int getResumeId();

    public void insertResumeActive(@Param("resumeId") int resumeId, @Param("resume") ResumeActiveDto resumeActiveDto);

    public void insertResumeCareer(@Param("resumeId") int resumeId, @Param("resume") ResumeCareerDto resumeCareerDto);

    public void insertResumeCerts(@Param("resumeId") int resumeId, @Param("resume") ResumeCertsDto resumeCertsDto);

    public void insertResumeEducation(@Param("resumeId") int resumeId, @Param("resume")ResumeEducationDto resumeEducationDto);

    public void insertResumeIntroduce(@Param("resumeId") int resumeId, @Param("resume") ResumeIntroduceDto resumeIntroduceDto);

    public void insertResumeLoyalty(@Param("resumeId") int resumeId, @Param("resume")  ResumeLoyaltyDto resumeLoyaltyDto);

    public void insertResumeMilitary(@Param("resumeId") int resumeId, @Param("resume")  ResumeMilitaryDto resumeMilitaryDto);

    public void insertResumePrtf(@Param("resumeId") int resumeId, @Param("resume")  ResumePrtfDto resumePrtfDto);

    public void insertResumeStack(@Param("resumeId") int resumeId, @Param("resume") ResumeStackDto resumeStackDto);

}


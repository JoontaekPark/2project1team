package org.green.backend.repository.dao.resume;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.resume.*;

import java.util.List;

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

    public void insertResumeActive(@Param("resumeId") int resumeId, @Param("resume") List<ResumeActiveDto> resumeActiveDto);

    public void insertResumeCareer(@Param("resumeId") int resumeId, @Param("resume") List<ResumeCareerDto> resumeCareerDto);

    public void insertResumeCerts(@Param("resumeId") int resumeId, @Param("resume") List<ResumeCertsDto> resumeCertsDto);

    public void insertResumeEducation(@Param("resumeId") int resumeId, @Param("resume") List<ResumeEducationDto> resumeEducationDto);

    public void insertResumeIntroduce(@Param("resumeId") int resumeId, @Param("resume") List<ResumeIntroduceDto> resumeIntroduceDto);

    public void insertResumeLoyalty(@Param("resumeId") int resumeId, @Param("resume")  List<ResumeLoyaltyDto> resumeLoyaltyDto);

    public void insertResumeMilitary(@Param("resumeId") int resumeId, @Param("resume")  List<ResumeMilitaryDto> resumeMilitaryDto);

    public void insertResumePrtf(@Param("resumeId") int resumeId, @Param("resume")  List<ResumePrtfDto> resumePrtfDto);

    public void insertResumeStack(@Param("resumeId") int resumeId, @Param("resume") List<ResumeStackDto> resumeStackDto);

}


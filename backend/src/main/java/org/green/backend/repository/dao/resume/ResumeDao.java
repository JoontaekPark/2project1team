package org.green.backend.repository.dao.resume;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.common.FileDto;
import org.green.backend.dto.common.UserDto;
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
    public void insertResumeBase(@Param("instId")String instId,@Param("resume") ResumeDto resumeDto);

    public int getResumeId();

    public int getResumePrtfNum();

    public void insertResumeActive(@Param("resumeId") int resumeId, @Param("resume") List<ResumeActiveDto> resumeActiveDto);

    public void insertResumeCareer(@Param("resumeId") int resumeId, @Param("resume") List<ResumeCareerDto> resumeCareerDto);

    public void insertResumeCerts(@Param("resumeId") int resumeId, @Param("resume") List<ResumeCertsDto> resumeCertsDto);

    public void insertResumeEducation(@Param("resumeId") int resumeId, @Param("resume") List<ResumeEducationDto> resumeEducationDto);

    public void insertResumeIntroduce(@Param("resumeId") int resumeId, @Param("resume") List<ResumeIntroduceDto> resumeIntroduceDto);

    public void insertResumeLoyalty(@Param("resumeId") int resumeId, @Param("resume")  List<ResumeLoyaltyDto> resumeLoyaltyDto);

    public void insertResumeMilitary(@Param("resumeId") int resumeId, @Param("resume")  List<ResumeMilitaryDto> resumeMilitaryDto);

    public void insertResumePrtf(@Param("resumeId") int resumeId, @Param("resume")  ResumePrtfDto resumePrtfDto);

    public void insertResumeStack(@Param("resumeId") int resumeId, @Param("resume") List<ResumeStackDto> resumeStackDto);

    public ResumeInfoAll2Dto getResumeDetail(int resumeId);

    //이력서 상세보기 데이터들고오기

    public ResumeDto getResumeDto(int resumeId);
    public List<ResumeActiveDto> getResumeActiveDto(int resumeId);
    public List<ResumeCareerDto> getResumeCareerDto(int resumeId);
    public List<ResumeCertsDto> getResumeCertsDto(int resumeId);
    public List<ResumeEducationDto> getResumeEducationDto(int resumeId);
    public List<ResumeIntroduceDto> getResumeIntroduceDto(int resumeId);
    public List<ResumeLoyaltyDto> getResumeLoyaltyDto(int resumeId);
    public List<ResumeMilitaryDto> getResumeMilitaryDto(int resumeId);
    public List<ResumePrtfDto> getResumePrtfDto(int resumeId);
    public List<ResumeStackDto> getResumeStackDto(int resumeId);

    //이력서 상세보기 파일들고오기
    public FileDto getResumeFilePhoto(int resumeId);

    //이력서 작성한 유저정보 들고오기
    public UserDto getResumeUser(String instId);



}


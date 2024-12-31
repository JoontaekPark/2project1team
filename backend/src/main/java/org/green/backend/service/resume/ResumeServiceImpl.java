package org.green.backend.service.resume;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.resume.*;
import org.green.backend.repository.dao.resume.ResumeDao;
import org.green.backend.service.common.FileService;
import org.green.backend.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {


    private final ResumeDao resumeDao;
    private final FileService fileService;
    private final FileUploadUtil fileUploadUtil;


    @Override
    public void insertResumeBase(ResumeDto resumeDto) {

        resumeDao.insertResumeBase(resumeDto);
    }

    @Override
    public int getResumeId() {
        int resumeId = resumeDao.getResumeId();
        return resumeId;
    }

    @Override
    public void insertResumeActive(int resumeId, List<ResumeActiveDto> resumeActiveDto) {
        resumeDao.insertResumeActive(resumeId, resumeActiveDto);

    }

    @Override
    public void insertResumeCareer(int resumeId, List<ResumeCareerDto> resumeCareerDto) {
        resumeDao.insertResumeCareer(resumeId, resumeCareerDto);
    }

    @Override
    public void insertResumeCerts(int resumeId, List<ResumeCertsDto> resumeCertsDto) {
        resumeDao.insertResumeCerts(resumeId, resumeCertsDto);
    }

    @Override
    public void insertResumeEducation(int resumeId, List<ResumeEducationDto> resumeEducationDto) {
        resumeDao.insertResumeEducation(resumeId, resumeEducationDto);
    }

    @Override
    public void insertResumeIntroduce(int resumeId, List<ResumeIntroduceDto> resumeIntroduceDto) {
        resumeDao.insertResumeIntroduce(resumeId, resumeIntroduceDto);
    }

    @Override
    public void insertResumeLoyalty(int resumeId, List<ResumeLoyaltyDto> resumeLoyaltyDto) {
        resumeDao.insertResumeLoyalty(resumeId, resumeLoyaltyDto);
    }

    @Override
    public void insertResumeMilitary(int resumeId, List<ResumeMilitaryDto> resumeMilitaryDto) {
        resumeDao.insertResumeMilitary(resumeId, resumeMilitaryDto);
    }

    @Override
    public void insertResumePrtf(int resumeId, List<ResumePrtfDto> resumePrtfDto) throws IOException {
        resumeDao.insertResumePrtf(resumeId, resumePrtfDto);

        // 리스트의 각 항목에 대해 반복문을 돌면서 파일을 저장하는 작업 수행

        String resumeNum = String.valueOf(resumeId);
        for (ResumePrtfDto prtfDto : resumePrtfDto) {
            if (prtfDto.getResumePrtfFile() != null) {
                // resumePrtfFile이 존재하는 경우에만 파일 저장
                fileService.saveFile(prtfDto.getResumePrtfFile(), "40",resumeNum, "임시 가라값 박준택임");
            }
        }
    }

    @Override
    public void insertResumeStack(int resumeId, List<ResumeStackDto> resumeStackDto) {
        resumeDao.insertResumeStack(resumeId, resumeStackDto);
    }


}

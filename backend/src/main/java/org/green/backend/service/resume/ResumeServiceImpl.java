package org.green.backend.service.resume;

import org.green.backend.dto.resume.*;
import org.green.backend.repository.dao.resume.ResumeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;


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
    public void insertResumeActive(int resumeId, ResumeActiveDto resumeActiveDto) {
        resumeDao.insertResumeActive(resumeId, resumeActiveDto);

    }

    @Override
    public void insertResumeCareer(int resumeId, ResumeCareerDto resumeCareerDto) {
        resumeDao.insertResumeCareer(resumeId, resumeCareerDto);
    }

    @Override
    public void insertResumeCerts(int resumeId, ResumeCertsDto resumeCertsDto) {
        resumeDao.insertResumeCerts(resumeId, resumeCertsDto);
    }

    @Override
    public void insertResumeEducation(int resumeId, ResumeEducationDto resumeEducationDto) {
        resumeDao.insertResumeEducation(resumeId, resumeEducationDto);
    }

    @Override
    public void insertResumeIntroduce(int resumeId, ResumeIntroduceDto resumeIntroduceDto) {
        resumeDao.insertResumeIntroduce(resumeId, resumeIntroduceDto);
    }

    @Override
    public void insertResumeLoyalty(int resumeId, ResumeLoyaltyDto resumeLoyaltyDto) {
        resumeDao.insertResumeLoyalty(resumeId, resumeLoyaltyDto);
    }

    @Override
    public void insertResumeMilitary(int resumeId, ResumeMilitaryDto resumeMilitaryDto) {
        resumeDao.insertResumeMilitary(resumeId, resumeMilitaryDto);
    }

    @Override
    public void insertResumePrtf(int resumeId, ResumePrtfDto resumePrtfDto) {
        resumeDao.insertResumePrtf(resumeId, resumePrtfDto);
    }

    @Override
    public void insertResumeStack(int resumeId, ResumeStackDto resumeStackDto) {
        resumeDao.insertResumeStack(resumeId, resumeStackDto);
    }


}

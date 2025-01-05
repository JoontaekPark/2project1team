package org.green.backend.service.resume;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.common.FileDto;
import org.green.backend.dto.common.UserDto;
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
    public void insertResumeBase(String instId, int resumeId, ResumeDto resumeDto) throws IOException {

        resumeDao.insertResumeBase(instId, resumeDto);
        String resumeNum = String.valueOf(resumeId);
        if (resumeDto.getResumeProfile() != null) {
            fileService.saveFile(resumeDto.getResumeProfile(), "30", resumeNum, "임시 가라값 박준택임");
        }
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
        //resumeDao.insertResumePrtf(resumeId, resumePrtfDto);

        // 리스트의 각 항목에 대해 반복문을 돌면서 파일을 저장하는 작업 수행

        String resumeNum = String.valueOf(resumeId);
        for (ResumePrtfDto prtfDto : resumePrtfDto) {

            resumeDao.insertResumePrtf(resumeId, prtfDto);

            int prtfNum = resumeDao.getResumePrtfNum();

            if (prtfDto.getResumePrtfFile() != null) {
                // resumePrtfFile이 존재하는 경우에만 파일 저장
                fileService.saveFile(prtfDto.getResumePrtfFile(), "40", String.valueOf(prtfNum), "임시 가라값 박준택임");
            }
        }
    }

    @Override
    public void insertResumeStack(int resumeId, List<ResumeStackDto> resumeStackDto) {
        resumeDao.insertResumeStack(resumeId, resumeStackDto);
    }


    @Override
    public ResumeInfoAll2Dto getResumeDetail(int resumeId) {

        ResumeInfoAll2Dto resumeInfo = resumeDao.getResumeDetail(resumeId);
        System.out.println("서비스 resumeInfo :" + resumeInfo);

        return resumeInfo;
    }

    @Override
    public ResumeInfoAll2Dto getResumeInfo(int resumeId) {
        ResumeDto resumeBasic = resumeDao.getResumeDto(resumeId);
        List<ResumeActiveDto> active = resumeDao.getResumeActiveDto(resumeId);
        List<ResumeCareerDto> career = resumeDao.getResumeCareerDto(resumeId);
        List<ResumeCertsDto> certs = resumeDao.getResumeCertsDto(resumeId);
        List<ResumeEducationDto> education = resumeDao.getResumeEducationDto(resumeId);
        List<ResumeIntroduceDto> introduce = resumeDao.getResumeIntroduceDto(resumeId);
        List<ResumeLoyaltyDto> loyalty = resumeDao.getResumeLoyaltyDto(resumeId);
        List<ResumeMilitaryDto> military = resumeDao.getResumeMilitaryDto(resumeId);
        List<ResumePrtfDto> prtf = resumeDao.getResumePrtfDto(resumeId);
        List<ResumeStackDto> stack = resumeDao.getResumeStackDto(resumeId);

        resumeBasic.setResumeFile(resumeDao.getResumeFilePhoto(resumeId));

        //---------------------------------------------------------
        ResumeInfoAll2Dto resumeInfo = new ResumeInfoAll2Dto();
        resumeInfo.setBasicInfo(resumeBasic);
        resumeInfo.setActives(active);
        resumeInfo.setCareers(career);
        resumeInfo.setCerts(certs);
        resumeInfo.setEducations(education);
        resumeInfo.setIntroduces(introduce);
        resumeInfo.setLoyalties(loyalty);
        resumeInfo.setMilitaries(military);
        resumeInfo.setPrtfs(prtf);
        resumeInfo.setStacks(stack);


        return resumeInfo;
    }

    @Override
    public UserDto getResumeUser(String instId) {

        UserDto user = resumeDao.getResumeUser(instId);

        return user;
    }

    @Override
    public List<ResumeDto> getResumeList(String instId) {
        List<ResumeDto> resumes = resumeDao.getResumeList(instId);
        return resumes;
    }

    @Override
    public void updateMainResume(int resumeId) {
        resumeDao.updateMainResume2();
        System.out.println("서비스에서 대표이력서 순서 구분@@");
        resumeDao.updateMainResume1(resumeId);
    }

    @Override
    public List<userApplyStatus> getApplyList(String instId) {
        List<userApplyStatus> applyStatusList = resumeDao.getApplyList(instId);
        return applyStatusList;
    }

    @Override
    public GetCntApplyDto getCntApply(String instId) {
        GetCntApplyDto cntApply = resumeDao.getCntApply(instId);
        return cntApply;
    }

//    @Override
//    public FileDto getResumeFilePhoto(int resumeId) {
//        FileDto resumePhoto = resumeDao.getResumeFilePhoto(resumeId);
//        return resumePhoto;
//    }
//
//    @Override
//    public List<FileDto> getResumeFilePrtf(int resumeId) {
//        List<FileDto> resumePrtf = resumeDao.getResumeFilePrtf(resumeId);
//        return resumePrtf;
//    }


    //-----------------------------


}

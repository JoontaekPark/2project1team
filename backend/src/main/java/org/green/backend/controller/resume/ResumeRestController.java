package org.green.backend.controller.resume;

import org.green.backend.dto.resume.*;
import org.green.backend.service.resume.ResumeService;
import org.green.frontend.dto.resume.ResumeInfoAllDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : org.green.backend.controller.resume
 * fileName       : ResumeRestController
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/resume")
public class ResumeRestController {



    @Autowired
    private ResumeService resumeService;

    @PostMapping("/regProc")
    public String regProc(@RequestBody ResumeInfoAllDto resumeDto){


        //이력서 기본 내용
        ResumeDto resume1 = new ResumeDto();
        resume1.setResumeTitle(resumeDto.getResumeTitle());
        resume1.setResumeArea(resumeDto.getResumeArea());
        resume1.setResumeMainYn(resumeDto.getResumeMainYn());
        resume1.setResumeMemo(resumeDto.getResumeMemo());
        resume1.setInstId(resumeDto.getInstId()); //여긴 나중에 시큐티티 사용자 아이디 넣어야댐
        resumeService.insertResumeBase(resume1);
        System.out.println("베이스 등록 완료");


        //현재 작성중인 이력서 id 불러오기
        int resumeId = resumeService.getResumeId();

        //이력서 활동내용
        ResumeActiveDto resume2 = new ResumeActiveDto();
        resume2.setResumeActiveDetail(resumeDto.getResumeActiveDetail());
        resume2.setResumeActiveStrDate(resumeDto.getResumeActiveStrDate());
        resume2.setResumeActiveEndDate(resumeDto.getResumeActiveEndDate());
        resumeService.insertResumeActive(resumeId,resume2);
        System.out.println("활동 등록 완료");




        //이력서 경력내용
        ResumeCareerDto resume3 = new ResumeCareerDto();
        resume3.setResumeCareerCompanyNm(resumeDto.getResumeCareerCompanyNm());
        resume3.setResumeCareerJoinDt(resumeDto.getResumeCareerJoinDt());
        resume3.setResumeCareerOutDt(resumeDto.getResumeCareerOutDt());
        resume3.setResumeCareerDmpt(resumeDto.getResumeCareerDmpt());
        resume3.setResumeCareerPstn(resumeDto.getResumeCareerPstn());
        resume3.setResumeCareerDuties(resumeDto.getResumeCareerDuties());
        resumeService.insertResumeCareer(resumeId,resume3);
        System.out.println("경력 등록 완료");

        //이력서 자격증 내용
        ResumeCertsDto resume4 = new ResumeCertsDto();
        resume4.setResumeCertsDt(resumeDto.getResumeCertsDt());
        resume4.setResumeCertsNm(resumeDto.getResumeCertsNm());
        resume4.setResumeCertsPlace(resumeDto.getResumeCertsPlace());
        resume4.setResumeCertsGbnCd(resumeDto.getResumeCertsGbnCd());
        resumeService.insertResumeCerts(resumeId,resume4);
        System.out.println("자격증 등록 완료");


        //이력서 학력내용
        ResumeEducationDto resume5 = new ResumeEducationDto();
        resume5.setResumeEducationGbnCd(resumeDto.getResumeEducationGbnCd());
        resume5.setResumeEducationSchoolNm(resumeDto.getResumeEducationSchoolNm());
        resume5.setResumeEducationMajor(resumeDto.getResumeEducationMajor());
        resume5.setResumeEducationScore(resumeDto.getResumeEducationScore());
        resume5.setResumeEducationIndt(resumeDto.getResumeEducationIndt());
        resume5.setResumeEducationOutdt(resumeDto.getResumeEducationOutdt());
        resume5.setResumeEducationTransferYn(resumeDto.getResumeEducationTransferYn());
        resume5.setResumeEducationResion(resumeDto.getResumeEducationResion());
        resumeService.insertResumeEducation(resumeId,resume5);
        System.out.println("학력 등록 완료");

        //이력서 자기소개 내용
        ResumeIntroduceDto resume6 = new ResumeIntroduceDto();
        resume6.setResumeIntroduceTitle(resumeDto.getResumeIntroduceTitle());
        resume6.setResumeIntroduceContent(resumeDto.getResumeIntroduceContent());
        resumeService.insertResumeIntroduce(resumeId,resume6);
        System.out.println("자기소개 등록 완료");


        //이력서 우대사항 내용
        ResumeLoyaltyDto resume7 = new ResumeLoyaltyDto();
        resume7.setResumeLoyaltyDetail(resumeDto.getResumeLoyaltyDetail());
        resumeService.insertResumeLoyalty(resumeId,resume7);
        System.out.println("우대사항 등록 완료");


        //이력서 병역 내용
        ResumeMilitaryDto resume8 = new ResumeMilitaryDto();
        resume8.setResumeMilitaryStrDate(resumeDto.getResumeMilitaryStrDate());
        resume8.setResumeMilitaryEndDate(resumeDto.getResumeMilitaryEndDate());
        resume8.setResumeMilitaryGbnCd(resumeDto.getResumeMilitaryGbnCd());
        resume8.setResumeMilitaryRankGbnCd(resumeDto.getResumeMilitaryRankGbnCd());
        resume8.setResumeMilitaryTypeGbnCd(resumeDto.getResumeMilitaryTypeGbnCd());
        resume8.setResumeMilitaryFinishGbnCd(resumeDto.getResumeMilitaryFinishGbnCd());
        resumeService.insertResumeMilitary(resumeId,resume8);
        System.out.println("병역사항 등록 완료");

        //이력서 포트폴리오 내용
        ResumePrtfDto resume9 = new ResumePrtfDto();
        resume9.setResumePrtfStrDate(resumeDto.getResumePrtfStrDate());
        resume9.setResumePrtfEndDate(resumeDto.getResumePrtfEndDate());
        resume9.setResumePrtfUrl(resumeDto.getResumePrtfUrl());
        resume9.setResumePrtfCnt(resumeDto.getResumePrtfCnt());
        resume9.setResumePrtfContent(resumeDto.getResumePrtfContent());
        resumeService.insertResumePrtf(resumeId,resume9);
        System.out.println("포폴 등록 완료");

        //이력서 기술스택 내용
        ResumeStackDto resume10 = new ResumeStackDto();
        resume10.setStackCd(resumeDto.getStackCd());
        resumeService.insertResumeStack(resumeId,resume10);
        System.out.println("스택 등록 완료");







        return "이력서 저장 해볼까 이제?";
    }


}

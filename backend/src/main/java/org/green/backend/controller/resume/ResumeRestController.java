package org.green.backend.controller.resume;

import jakarta.servlet.http.HttpServletRequest;
import org.green.backend.dto.common.UserDto;
import org.green.backend.dto.resume.*;
import org.green.backend.service.common.UserService;
import org.green.backend.service.resume.ResumeService;
import org.green.backend.dto.resume.ResumeInfoAllDto;
import org.green.backend.utils.CookieUtil;
import org.green.backend.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @Autowired
    private UserService userService;
    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private JWTUtil jwtUtil;
/*
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
    }*/


    @PostMapping("regProc2")
    public String regProc2(@ModelAttribute ResumeInfoAll2Dto resumeDto ,
                           HttpServletRequest request) throws Exception {




        int resumeId2 = resumeService.getResumeId();
        int resumeId = resumeId2+1;
        ResumeDto basicInfo = resumeDto.getBasicInfo();
        //이름,이메일,전화번호는 나중에 추가

        String token = cookieUtil.getCookie(request, "Authorization");
        String instId = jwtUtil.getId(token);

        if (basicInfo != null) {
            resumeService.insertResumeBase(instId, resumeId, basicInfo);
            System.out.println("기본 정보 사항 저장 완료 :" + basicInfo);
        }


         // 활동사항 출력
        List<ResumeActiveDto> actives = resumeDto.getActives();
        if (actives != null) {

            resumeService.insertResumeActive(resumeId,actives);
            for (int i = 0; i < actives.size(); i++) {
                System.out.println(i + "번째 활동사항: " + actives.get(i));
            }
        } else {
            System.out.println("학력사항 데이터가 없습니다.");
        }


    // 경력사항 출력
        List<ResumeCareerDto> careers = resumeDto.getCareers();
        if (careers != null) {
            resumeService.insertResumeCareer(resumeId,careers);
            for (int i = 0; i < careers.size(); i++) {
                System.out.println(i + "번째 경력사항: " + careers.get(i));
            }
        } else {
            System.out.println("경력사항 데이터가 없습니다.");
        }


//자격증 출력
        List<ResumeCertsDto> certs = resumeDto.getCerts();
        if (certs != null) {

            resumeService.insertResumeCerts(resumeId,certs);

            for (int i = 0; i < certs.size(); i++) {
                System.out.println(i + "번째 자격증: " + certs.get(i));
            }
        } else {
            System.out.println("자격증 데이터가 없습니다.");
        }


// 학력사항 출력
        List<ResumeEducationDto> educations = resumeDto.getEducations();
        if (educations != null) {

            resumeService.insertResumeEducation(resumeId,educations);
            for (int i = 0; i < educations.size(); i++) {
                System.out.println(i + "번째 학력사항: " + educations.get(i));
            }
        } else {
            System.out.println("학력사항 데이터가 없습니다.");
        }

// 자기소개 출력
        List<ResumeIntroduceDto> introduces = resumeDto.getIntroduces();
        if (introduces != null) {

            resumeService.insertResumeIntroduce(resumeId,introduces);
            for (int i = 0; i < introduces.size(); i++) {
                System.out.println(i + "번째 자기소개: " + introduces.get(i));
            }
        } else {
            System.out.println("자기소개 데이터가 없습니다.");
        }

// 우대사항 출력
        List<ResumeLoyaltyDto> loyalties = resumeDto.getLoyalties();
        if (loyalties != null) {

            resumeService.insertResumeLoyalty(resumeId,loyalties);
            for (int i = 0; i < loyalties.size(); i++) {
                System.out.println(i + "번째 우대사항: " + loyalties.get(i));
            }
        } else {
            System.out.println("우대사항 데이터가 없습니다.");
        }

// 병역사항 출력
        List<ResumeMilitaryDto> militaries = resumeDto.getMilitaries();
        if (militaries != null) {

            resumeService.insertResumeMilitary(resumeId,militaries);
            for (int i = 0; i < militaries.size(); i++) {
                System.out.println(i + "번째 병역사항: " + militaries.get(i));
            }
        } else {
            System.out.println("병역사항 데이터가 없습니다.");
        }

// 포트폴리오 출력
        List<ResumePrtfDto> prtfs = resumeDto.getPrtfs();
        if (prtfs != null) {

            resumeService.insertResumePrtf(resumeId,prtfs);
            for (int i = 0; i < prtfs.size(); i++) {
                System.out.println(i + "번째 포트폴리오: " + prtfs.get(i));
            }
        } else {
            System.out.println("포트폴리오 데이터가 없습니다.");
        }


// 기술스택 출력
        List<ResumeStackDto> stacks = resumeDto.getStacks();
        if (stacks != null) {
            resumeService.insertResumeStack(resumeId,stacks);
            for (int i = 0; i < stacks.size(); i++) {
                System.out.println(i + "번째 기술스택: " + stacks.get(i));
            }
        } else {
            System.out.println("기술스택 데이터가 없습니다.");
        }

        return "";
    }



    @GetMapping("getResumeDetail2")
    public ResumeInfoAll2Dto getResumeDetail2(@RequestParam int resumeId) {

        ResumeInfoAll2Dto resumeInfo = resumeService.getResumeInfo(resumeId);

        return resumeInfo;
    }









    @GetMapping("getLoginUser")
    public UserDto getLoginUser(HttpServletRequest request) throws Exception{
        String token = request.getHeader("Authorization");
        return userService.userInfo(token);
    }

    @GetMapping("getResumeUser")
    public UserDto getResumeUser(HttpServletRequest request,String instId) throws Exception{
        UserDto user = resumeService.getResumeUser(instId);
        return user;
    }


    @GetMapping("getResumeList")
    public List<ResumeDto> getResumeList(HttpServletRequest request,@RequestParam String instId) throws Exception{
        List<ResumeDto> resumes = resumeService.getResumeList(instId);
        return resumes;
    }




    @GetMapping("getCodeInfo")
    public String getCodeInfo() {

        return "";
    }


}

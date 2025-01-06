package org.green.backend.controller.resume;

import jakarta.servlet.http.HttpServletRequest;
import org.green.backend.dto.common.UserDto;
import org.green.backend.dto.resume.*;
import org.green.backend.repository.dao.resume.ResumeDao;
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
import java.util.Map;

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

    @Autowired
    private ResumeDao resumeDao;


    @PostMapping("reg-proc2")
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



    @GetMapping("getresume-detail")
    public ResumeInfoAll2Dto getResumeDetail(@RequestParam int resumeId) {
        ResumeInfoAll2Dto resumeInfo = resumeService.getResumeDetail(resumeId);
        System.out.println("Backend RestResumeController info : " + resumeInfo);
        return resumeInfo;
    }

    @GetMapping("getresume-detail2")
    public ResumeInfoAll2Dto getResumeDetail2(@RequestParam int resumeId) {
        ResumeInfoAll2Dto resumeInfo = resumeService.getResumeInfo(resumeId);
        return resumeInfo;
    }


    @GetMapping("get-loginuser")
    public UserDto getLoginUser(HttpServletRequest request) throws Exception{
        String token = request.getHeader("Authorization");
        return userService.userInfo(token);
    }

    @GetMapping("get-resumeuser")
    public UserDto getResumeUser(HttpServletRequest request,String instId) throws Exception{
        UserDto user = resumeService.getResumeUser(instId);
        return user;
    }



    @GetMapping("/get-resumelist")
    public List<ResumeDto> getResumeList(HttpServletRequest request,@RequestParam String instId) throws Exception{
        List<ResumeDto> resumes = resumeService.getResumeList(instId);
        return resumes;
    }

    @GetMapping("update-mainresume")
    public void updateMainResume(@RequestParam int resumeId) {
        System.out.println(resumeId);
        resumeService.updateMainResume(resumeId);
    }

    @GetMapping("get-applystatus")
    public List<userApplyStatus> getApplyStatus(@RequestParam String instId) throws Exception {

        List<userApplyStatus> applyStatusList = resumeService.getApplyList(instId);
        return applyStatusList;
    }

    @GetMapping("get-cntapply")
    public GetCntApplyDto getCntApply(@RequestParam String instId) throws Exception {

        GetCntApplyDto cntApply = resumeService.getCntApply(instId);
        return cntApply;
    }

    @DeleteMapping("delete-resume/{resumeId}")
    public String deleteResume(@PathVariable int resumeId) {

        resumeDao.deleteResume(resumeId);
        return "이력서가 삭제되었습니다.";
    }

    @PostMapping("save-memo")
    public String saveResume(@RequestBody Map<String,String> param) {
        System.out.println(param);


        int resumeId = Integer.parseInt(param.get("resumeId"));
        String memo = String.valueOf(param.get("memo"));

        resumeDao.insertMemo(memo,resumeId);

        return "";
    }





















    @GetMapping("getCodeInfo")
    public String getCodeInfo() {

        return "";
    }




}


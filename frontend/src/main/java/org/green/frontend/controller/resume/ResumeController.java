package org.green.frontend.controller.resume;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.dto.common.SessionDto;
import org.green.frontend.dto.common.UserDto;
import org.green.frontend.dto.resume.*;
import org.green.frontend.global.common.ApiResponse;
import org.green.frontend.service.JobNotice.JobNoticeService;
import org.green.frontend.service.resumeService.ResumeService;
import org.green.frontend.utils.WebClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

/**
 * packageName    : org.green.frontend.controller.resume
 * fileName       : ResumeController
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */


@Controller
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {


    private final ResumeService resumeService;
    private final JobNoticeService jobNoticeService;
    private final WebClientUtil webClientUtil;


    @RequestMapping("/main")
    public String main() {

        return "/resume/resumeMain";
    }

    @RequestMapping("/resumeRegForm")
    public String resumeRegForm() {

        return "/resume/resumeRegForm";
    }


    @RequestMapping("/resumeTest")
    public String resumeTest() {
        return "/resume/resumeTestPage";
    }


    @RequestMapping("/resumeForm2")
    public String resumeForm2(Model model) throws Exception {

        UserDto user = resumeService.getUser();
        model.addAttribute("user", user);


        //자격증 합격여부(필기,실기,필기실기)
        List<CodeInfoDto> certResult = resumeService.certResult();
        model.addAttribute("certResults", certResult);
        System.out.println(certResult);

        //학력구분코드(고졸,대졸)
        List<CodeInfoDto> educationLevel = resumeService.educationLevel();
        model.addAttribute("educationLevels", educationLevel);


        //기술스택(백엔드,프론트엔드)
        List<CodeInfoDto> stacks = resumeService.stacks();
        model.addAttribute("stacks", stacks);
        System.out.println(stacks);

        //병역사항
        List<CodeInfoDto> militaryStatus = resumeService.resumeMilitaryGbnCd();
        model.addAttribute("militaryStatus", militaryStatus);
        System.out.println(militaryStatus);

        List<CodeInfoDto> militaryRank = resumeService.resumeMilitaryRankGbnCd();
        model.addAttribute("militaryRank", militaryRank);

        List<CodeInfoDto> militaryType = resumeService.resumeMilitaryTypeGbnCd();
        model.addAttribute("militaryType", militaryType);

        List<CodeInfoDto> militaryFinish = resumeService.resumeMilitaryFinishGbnCd();
        model.addAttribute("militaryFinish", militaryFinish);

        //윤서씨꺼 기술스택 훔쳐옴
        List<CodeInfoDto> jobNoticeStackGbnCdList = jobNoticeService.stackInfo();
        model.addAttribute("jobNoticeStackGbnCdList", jobNoticeStackGbnCdList);


        return "/resume/resumeRegForm2";
    }

    @RequestMapping("/resumeDetail/{resumeId}")
    public String resumeDetail(@PathVariable("resumeId") int resumeId, Model model) throws Exception {


        ApiResponse<ResumeInfoAll2Dto> response = webClientUtil.getApi("/resume/getResumeDetail2?resumeId=" + resumeId, ResumeInfoAll2Dto.class);
        ResumeInfoAll2Dto resumeInfo = response.getBody();
        String instId = resumeInfo.getBasicInfo().getInstId();
        ApiResponse<UserDto> userResponse = webClientUtil.getApi("/resume/getResumeUser?instId=" + instId, UserDto.class);
        UserDto user = userResponse.getBody();
        model.addAttribute("user", user);
        model.addAttribute("resume", resumeInfo);
        return "/resume/resumeDetail";
    }

    @GetMapping("/resumeList")
    public String resumeList(Model model, HttpSession session) throws Exception {


        SessionDto user = (SessionDto) session.getAttribute("user");
        String instId = user.getId();
        ApiResponse<UserDto> userResponse = webClientUtil.getApi("/resume/getLoginUser?instId=" + instId, UserDto.class);

        UserDto loginUser = userResponse.getBody();


        ApiResponse<List> resumesInfo = webClientUtil.getApi("/resume/getResumeList?instId=" + instId, List.class);

        List<ResumeDto> resumes = resumesInfo.getBody();

        for (int i = 0; i < resumes.size(); i++) {
            System.out.println(i + "번째 이력서 정보 : " + resumes.get(i));
        }


        model.addAttribute("resumes", resumes);


        return "/resume/resumeList";
    }

    @RequestMapping("/applyStatusList")
        public String applyStatus (HttpSession session,Model model)throws Exception {

        SessionDto user = (SessionDto) session.getAttribute("user");
        String instId = user.getId();
        ApiResponse<UserDto> userResponse = webClientUtil.getApi("/resume/getLoginUser?instId=" + instId, UserDto.class);
        UserDto loginUser = userResponse.getBody();

        ApiResponse<List> applyStatusList1 = webClientUtil.getApi("/resume/getApplyStatus?instId=" + instId, List.class);
        List<userApplyStatus> applyStatusList = applyStatusList1.getBody();
        model.addAttribute("applyStatusList", applyStatusList);

        ApiResponse<GetCntApplyDto> cntApplyStatus = webClientUtil.getApi("/resume/getCntApply?instId=" + instId, GetCntApplyDto.class);
        GetCntApplyDto cntApply = cntApplyStatus.getBody();
        model.addAttribute("cntApply", cntApply);
            return "/resume/applyStatusList";
        }

    }



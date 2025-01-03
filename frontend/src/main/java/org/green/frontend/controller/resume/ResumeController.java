package org.green.frontend.controller.resume;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.dto.common.UserDto;
import org.green.frontend.dto.resume.ResumeDto;
import org.green.frontend.dto.resume.ResumeInfoAll2Dto;
import org.green.frontend.dto.resume.ResumeInfoAllDto;
import org.green.frontend.global.common.ApiResponse;
import org.green.frontend.service.JobNotice.JobNoticeService;
import org.green.frontend.service.resumeService.ResumeService;
import org.green.frontend.utils.WebClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 *packageName    : org.green.frontend.controller.resume
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

 @RequestMapping("/resumeDetail")
 public String resumeDetail(Model model) throws Exception {


  ApiResponse<ResumeInfoAll2Dto> response = webClientUtil.getApi("/resume/getResumeDetail2?resumeId=50", ResumeInfoAll2Dto.class);
  ResumeInfoAll2Dto resumeInfo = response.getBody();
  String instId = resumeInfo.getBasicInfo().getInstId();
  ApiResponse<UserDto> userResponse = webClientUtil.getApi("/resume/getResumeUser?instId="+instId, UserDto.class);
  UserDto user = userResponse.getBody();
  model.addAttribute("user", user);
  model.addAttribute("resume", resumeInfo);
  return "/resume/resumeDetail";
 }

 @GetMapping("/resumeList")
 public String resumeList(){
  return "/resume/resumeList";
 }





}

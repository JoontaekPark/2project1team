package org.green.frontend.controller.resume;

import org.green.frontend.dto.resume.ResumeDto;
import org.green.frontend.dto.resume.ResumeInfoAllDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
 public class ResumeController {



  @RequestMapping("/main")
  public String main() {

  return "/resume/resumeMain";
 }

 @RequestMapping("/resumeRegForm")
 public String resumeRegForm() {

   return "/resume/resumeRegForm";
 }

 @RequestMapping("/resumeRegProc")
 public String resumeRegProc(ResumeInfoAllDto resumeDto) {

  System.out.println(resumeDto);

   return "main";
 }

 @RequestMapping("/resumeTest")
 public String resumeTest() {
   return "/resume/resumeTestPage";
 }




}

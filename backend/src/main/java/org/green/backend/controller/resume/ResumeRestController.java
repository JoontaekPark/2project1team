package org.green.backend.controller.resume;

import org.green.backend.dto.resume.ResumeDto;
import org.green.frontend.dto.resume.ResumeInfoAllDto;
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



    @PostMapping("/regProc")
    public String regProc(@RequestBody ResumeInfoAllDto resumeDto){

        ResumeDto resume1 = new ResumeDto();
        resume1.setResumeTitle(resumeDto.getResumeTitle());
        resume1.setResumeArea(resumeDto.getResumeArea());
        resume1.setResumeMainYn(resumeDto.getResumeMainYn());
        resume1.setResumeMemo(resumeDto.getResumeMemo());
        resume1.setInstId(resumeDto.getInstId());


        return "이력서 저장 해볼까 이제?";
    }


}

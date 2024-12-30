package org.green.backend.controller.common;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.common.CodeInfoDto;
import org.green.backend.service.common.CodeInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 패키지명        : org.green.backend.controller.common
 * 파일명          : CodeinfoController
 * 작성자          : 김상준
 * 일자            : 2024-12-27
 * 내용            : 공통코드 컨트롤러
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-27        김상준            최초 생성
 */

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CodeInfoController {

    private final CodeInfoService codeInfoService;

    @GetMapping("/code-Infos")
    public List<CodeInfoDto> codeInfos(String upCd) {
        return codeInfoService.getCodeInfos(upCd);
    }

    @GetMapping("/code-Info")
    public CodeInfoDto codeInfos(String cd, String upCd) {
        return codeInfoService.getCodeInfo(cd, upCd);
    }

    @GetMapping("/code-infos/subcode")
    public List<CodeInfoDto> codeInfosBySubCode(String upCd) {
        return codeInfoService.getCodeInfosBySubCode(upCd);
    }

}

package org.green.frontend.service.common;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.global.common.ApiResponse;
import org.green.frontend.utils.WebClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 패키지명        : org.green.frontend.service.common
 * 파일명          : CodeInfoServiceImpl
 * 작성자          : 김상준
 * 일자            : 2025-01-01
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-01        김상준            최초 생성
 */

@Service
@RequiredArgsConstructor
public class CodeInfoServiceImpl implements CodeInfoService {

    private final WebClientUtil webClientUtil;

    @Override
    public List<CodeInfoDto> getCodeInfos(String upCd) throws Exception {
        ApiResponse<List> response = webClientUtil.getApi("/api/v1/code-Infos?upCd=" + upCd, List.class);
        return response.getBody();
    }
}

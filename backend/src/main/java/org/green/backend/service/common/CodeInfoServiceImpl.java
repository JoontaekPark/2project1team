package org.green.backend.service.common;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.common.CodeInfoDto;
import org.green.backend.repository.dao.common.CodeInfoDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 패키지명        : org.green.backend.service.common
 * 파일명          : CodeinfoServiceImpl
 * 작성자          : 김상준
 * 일자            : 2024-12-27
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-27        김상준            최초 생성
 */

@Service
@RequiredArgsConstructor
public class CodeInfoServiceImpl implements CodeInfoService {

    private final CodeInfoDao codeInfoDao;

    @Override
    public List<CodeInfoDto> getCodeInfos(String upCd) {
        return codeInfoDao.getCodeInfos(upCd);
    }

    @Override
    public CodeInfoDto getCodeInfo(String cd, String upCd) {
        return codeInfoDao.getCodeInfo(cd, upCd);
    }
}

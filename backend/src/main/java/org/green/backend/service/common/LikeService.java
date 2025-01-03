package org.green.backend.service.common;

import org.green.backend.dto.common.LikeDto;

/**
 * 패키지명        : org.green.backend.service.common
 * 파일명          : LikeService
 * 작성자          : 김상준
 * 일자            : 2025-01-03
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-03        김상준            최초 생성
 */


public interface LikeService {

    public void like(String token, LikeDto like);

}

package org.green.backend.dto.common;

import lombok.Data;

import java.time.LocalDate;

/**
 * 패키지명        : org.green.backend.dto.common
 * 파일명          : LikeDto
 * 작성자          : 김상준
 * 일자            : 2025-01-03
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-03        김상준            최초 생성
 */

@Data
public class LikeDto {

    // 대상 아이디(key)
    private String target;
    // 10: 관심기업, 20: 관심 구직자, 30: 채용공고 스크랩
    private String likeGbnCd;
    // 작성자 ID
    private String instId;
    // 작성일시
    private LocalDate instDt;

    // 현재상태: true = 삭제, false = 추가
    private boolean likedStatus;

}

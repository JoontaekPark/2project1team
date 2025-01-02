package org.green.frontend.dto.company;

import lombok.Data;

/**
 * 패키지명        : org.green.backend.dto.company
 * 파일명          : ResponseJobNotice
 * 작성자          : 김상준
 * 일자            : 2025-01-02
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-02        김상준            최초 생성
 */

@Data
public class ResponseJobNoticeDto {

    // 공고 정보
    private String jobNoticeNum;          // 공고 번호
    private String jobNoticeTitle;        // 공고 제목
    private String jobNoticeArea;         // 공고 지역
    private String jobNoticeCareerGbnCd;  // 경력 구분 코드
    private String jobNoticeCareerGbnNm;  // 경력 구분 이름

    // 공고 마감일 계산
    private int leftDt;                 // 남은 일수 (오늘 기준 마감일까지)

    // 파일 정보
    private String fileNewName;           // 파일 저장 이름
    private String fileOldName;           // 파일 원본 이름
    private String fileExt;               // 파일 확장자
    private String fileUrl;               // 파일 URL

}

package org.green.frontend.dto.common;

import lombok.Data;

import java.time.LocalDate;

/**
 * 패키지명        : org.green.backend.dto.common
 * 파일명          : FileDto
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            : 공통 파일 dto
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

@Data
public class FileDto {

    // 파일번호
    private Integer fileNo;
    // 파일구분(EX) question_no)
    private String fileGbnCd;
    // 파일 영향받는 아이디(EX) 1)
    private String fileRefId;
    // 파일 관리명(저장하는 파일명 시간으로 들어감 밀리세컨드까지)
    private String fileNewName;
    // 파일명(불러올 파일명)
    private String fileOldName;
    // 파일 확장자
    private String fileExt;
    // 크기 MB
    private Long fileSize;
    // 파일 위치
    private String fileUrl;
    // 작성자 ID
    private String instId;
    // 작성일시
    private LocalDate instDt;

}

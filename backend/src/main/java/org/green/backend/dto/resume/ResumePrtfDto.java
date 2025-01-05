package org.green.backend.dto.resume;

import lombok.Data;
import org.green.backend.dto.common.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

/**
 * packageName    : org.green.frontend.dto.resume
 * fileName       : ResumePrtfDto
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Data
public class ResumePrtfDto {
    // 이력서 포트폴리오 번호
    private Long resumePrtfNum;
    // 이력서 번호
    private Long resumeId;
    // 작업시작기간
    private LocalDate resumePrtfStrDate;
    // 작업종료기간
    private LocalDate resumePrtfEndDate;
    // url
    private String resumePrtfUrl;
    // 작업인원
    private Integer resumePrtfCnt;
    // 작업내용
    private String resumePrtfContent;

    //포트폴리오 파일 저장용(등록하기)
    private MultipartFile resumePrtfFile;

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

}

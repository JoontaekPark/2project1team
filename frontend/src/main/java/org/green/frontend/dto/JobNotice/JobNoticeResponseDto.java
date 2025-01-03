package org.green.frontend.dto.JobNotice;

import lombok.Data;
import org.green.frontend.dto.common.CodeInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created on 2025-01-01 by 최윤서
 */
@Data
public class JobNoticeResponseDto {

    private int jobNoticeNum;                 // 채용공고 번호
    private String companyName;                      // 기업 이름
    private String jobNoticeTitle;            // 채용공고 제목
    private String jobNoticeArea;             // 근무지역
    private String jobNoticeWorktime;         // 근무일
    private String workName;        // 근무형태
    private String careerName;      // 경력구분
    private String educationName;   // 학력구분
    private int jobNoticePay;                 // 급여
    private String jobNoticeDetail;           // 모집부분 및 상세내용
    private String jobNoticeWorkJogun;        // 근무조건
    private String jobNoticeWay;              // 접수기간 및 방법
    private String jobNoticeCaution;          // 유의사항
    private String jobNoticeStrDate;            // 공고 시작 기간
    private String jobNoticeEndDate;            // 공고 종료 기간
    private int vCnt;                         // 조회수
    private String instId;                    // 작성자 ID
    private LocalDateTime instDt;                      // 작성일시

    private List<String> stackList;   // 기술스택 리스트
    private List<String> stepList;     // 절차 리스트
    private List<String> welfareList; // 복리후생 리스트
//    private List<MultipartFile> files;

}

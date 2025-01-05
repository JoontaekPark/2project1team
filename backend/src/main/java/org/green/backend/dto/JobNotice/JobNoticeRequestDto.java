package org.green.backend.dto.JobNotice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created on 2024-12-30 by 최윤서
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobNoticeRequestDto {
    private int jobNoticeNum;                 // 채용공고 번호
    private String jobNoticeTitle;            // 채용공고 제목
    private String jobNoticeArea;             // 근무지역
    private String jobNoticeWorktime;         // 근무일
    private String jobNoticeWorkGbnCd;        // 근무형태
    private String jobNoticeCareerGbnCd;      // 경력구분
    private String jobNoticeEducationGbnCd;   // 학력구분
    private int jobNoticePay;                 // 급여
    private String jobNoticeDetail;           // 모집부분 및 상세내용
    private String jobNoticeWorkJogun;        // 근무조건
    private String jobNoticeWay;              // 접수기간 및 방법
    private String jobNoticeCaution;          // 유의사항
    private String jobNoticeStrDate;            // 공고 시작 기간
    private String jobNoticeEndDate;            // 공고 종료 기간
    private String instId;                    // 작성자 ID
    private String instDt;                      // 작성일시
    private String jobNoticeStatus;

    private List<String> stackList;   // 기술스택 리스트
    private List<String> stepList;     // 절차 리스트
    private List<String> welfareList    ; // 복리후생 리스트
    List<MultipartFile> files;
}

package org.green.frontend.dto.resume;

import lombok.Data;

import java.time.LocalDate;

/**
 * packageName    : org.green.frontend.dto.resume
 * fileName       : ResumeInfoAllDto
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Data
public class ResumeInfoAllDto {

 private Long resumeId;
 // 이력서 제목
 private String resumeTitle;
 // 희망근무 지역(작성)
 private String resumeArea;
 // Y: 맞음, N: 아님
 private String resumeMainYn;
 // 메모
 private String resumeMemo;
 // 작성자 ID
 private String instId;
 // 작성일시
 private LocalDate instDt;
 //--------------------------------------------------------
 private Long resumeActiveNum;
 // 활동 상세 내용
 private String resumeActiveDetail;
 // 활동 시작일
 private LocalDate resumeActiveStrDate;
 // 활동 종료일
 private LocalDate resumeActiveEndDate;
 //--------------------------------------------------------
 // 이력서 경력 번호
 private Long resumeCareerNum;
 // 회사명
 private String resumeCareerCompanyNm;
 // 입사연월
 private LocalDate resumeCareerJoinDt;
 // 퇴사연월
 private LocalDate resumeCareerOutDt;
 // 부서명
 private String resumeCareerDmpt;
 // 직책
 private String resumeCareerPstn;
 // 담당업무
 private String resumeCareerDuties;

 //-----------------------------------------------
// 이력서 자격증 번호
 private Long resumeCertsNum;
 // 자격증 취득일
 private LocalDate resumeCertsDt;
 // 자격증 명
 private String resumeCertsNm;
 // 자격증 발급기관
 private String resumeCertsPlace;
 // 합격여부(P: 필기, S:실기)
 private String resumeCertsGbnCd;

 //-----------------------------------------------
// 이력서 학력 번호
 private Long resumeEducationNum;

 // 학력구분(H: 고등학교, U: 대학, S: 석사, D: 박사, J: 전문대)
 private String resumeEducationGbnCd;
 // 학교이름
 private String resumeEducationSchoolNm;
 // 전공
 private String resumeEducationMajor;
 // 성적 (필수아님)
 private Float resumeEducationScore;
 // 입학날짜
 private LocalDate resumeEducationIndt;
 // 졸업날짜
 private LocalDate resumeEducationOutdt;
 // 편입여부(Y, N)
 private String resumeEducationTransferYn;
 // 지역
 private String resumeEducationResion;


 //---------------------------------------------------
 // 이력서 자기소개 번호
 private Long resumeIntroduceNum;
 // 자기소개 제목
 private String resumeIntroduceTitle;
 // 자기소개 내용
 private String resumeIntroduceContent;

 //--------------------------------------------------------

 // 이력서 우대사항 번호
 private Long resumeLoyaltyNum;
 // 이력서 번호
 private String resumeLoyaltyDetail;
 //----------------------------------------------------

 // 이력서 병역사항 번호
 private Long resumeMilitaryNum;
 // 시작일자
 private LocalDate resumeMilitaryStrDate;
 // 종료일자
 private LocalDate resumeMilitaryEndDate;
 // 병역구분(군필, 미필, 면제, 복무중)
 private String resumeMilitaryGbnCd;
 // 계급(이병 ~ 대장)
 private String resumeMilitaryRankGbnCd;
 // 군별(육, 해, 공 기타등등)
 private String resumeMilitaryTypeGbnCd;
 // 전역구분(의가사, 만기, 불명예 등등)
 private String resumeMilitaryFinishGbnCd;

 //----------------------------------------------------

 // 이력서 포트폴리오 번호
 private Long resumePrtfNum;
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

 //---------------------------------------
 // 이력서 기술스택 번호
 private Long resumeStackNum;
 // 기술스택
 private String stackCd;








}

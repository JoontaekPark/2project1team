package org.green.backend.dto.company;

import lombok.Data;

import java.util.List;

/**
 * 패키지명        : org.green.backend.dto.company
 * 파일명          : ResponseCompanyDto
 * 작성자          : 김상준
 * 일자            : 2025-01-02
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-02        김상준            최초 생성
 */

@Data
public class ResponseCompanyDto {

    // User 정보
    private String id;
    private String name;
    private String birth;
    private String email;
    private String phone;
    private String zipCd;
    private String addr;
    private String addrDetail;

    // Company 정보
    private String ceoName;
    private String homepage;
    private String companyTypeCd;
    private String companyTypeNm; // CodeInfo에서 가져온 값
    private String companyIndustry;
    private String companyDetail;

    // File 정보
    private String fileNewName;
    private String fileOldName;
    private String fileExt;
    private String fileUrl;

    // Like 정보
    private int likeCnt;
    private int likeYn;

    // star 정보
    private Float star;

    // 연혁
    private List<HistoryDto> histories;
    // 채용공고
    private List<ResponseJobNoticeDto> jobNotices;



}

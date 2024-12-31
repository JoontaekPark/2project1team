package org.green.backend.dto.common;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

/**
 * 패키지명        : org.green.backend.dto.common
 * 파일명          : UserDto
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            : 사용자 정보 (기업정보) + 파일
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

@Data
public class UserDto {

    // 아이디
    private String id;
    // 비밀번호
    private String pw;
    // 구직자: 이름, 회사: 회사명
    private String name;
    // 구직자: 생년월일, 회사: 설립일
    private LocalDate birth;
    // 성별
    private String genderCd;
    // 우편번호
    private Integer zipCd;
    // 주소
    private String addr;
    // 전화번호
    private String phone;
    // 이메일
    private String email;
    // 상세주소
    private String addrDetail;
    // 사용여부
    private String useYn;
    // 사용자구분 (10: 구직자, 20:기업, 30:관리자)
    private String userGbnCd;


    // 대표자명
    private String ceoName;
    // 사업자등록번호 (-)없이
    private Integer businessNum;
    // 홈페이지
    private String homepage;
    // 기업형태 (중소, 중견, 대)
    private String companyGbnCd;
    // 업종 (텍스트)
    private String companyIndustry;

    // 저장시 담음
    private MultipartFile profile;

    // 수정 및 조회시 담음
    private FileDto fileDto;
}

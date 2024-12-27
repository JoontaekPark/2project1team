package org.green.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName : org.green.backend.dto
 * author : BoardDto
 * date : 2024-12-27
 * description :
 * ==========================================
 * DATE             AUTHOR         NOTE
 * ------------------------------------------
 * 2024-12-27          GGG        최초생성
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private String boardTarget;    // 문의 대상
    private String boardTitle;     // 제목
    private String boardContent;   // 내용
    private String boardGbnCd;     // 게시판 구분 (10: 1:1문의)
    private String boardStatusCd;  // 상태 코드 (10: 미확인)
    private String instId;         // 작성자 ID
}

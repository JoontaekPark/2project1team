package org.green.backend.dto.board;

import lombok.Data;

/**
 * packageName : org.green.backend.dto.board
 * author : BoardListDto
 * date : 2024-12-31
 * description :
 * ==========================================
 * DATE             AUTHOR         NOTE
 * ------------------------------------------
 * 2024-12-31          GGG        최초생성
 */
@Data
public class BoardListDto {
    private Long boardNum;       // 문의 번호
    private String targetName;   // 기업명 (구직자용)
    private String userName;     // 구직자명 (기업용)
    private String boardTitle;   // 제목
    private String boardStatusCd;
    private String boardGbnCd;
    private String instDt;       // 등록 날짜
}

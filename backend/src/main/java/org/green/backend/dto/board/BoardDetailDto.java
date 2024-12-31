package org.green.backend.dto.board;

import lombok.Data;

import java.util.List;

/**
 * packageName : org.green.backend.dto.board
 * author : BoardDetailDto
 * date : 2024-12-31
 * description :
 * ==========================================
 * DATE             AUTHOR         NOTE
 * ------------------------------------------
 * 2024-12-31          GGG        최초생성
 */
@Data
public class BoardDetailDto {
    private Long boardNum;         // 문의 번호
    private String boardTitle;     // 제목
    private String boardContent;   // 내용
    private String instId;         // 작성자 ID
    private String instDt;         // 작성 날짜
    private List<CommentDto> replies; // 답글 리스트
}

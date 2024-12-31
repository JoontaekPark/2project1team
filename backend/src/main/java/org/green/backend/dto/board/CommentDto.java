package org.green.backend.dto.board;

import lombok.Data;

/**
 * packageName : org.green.backend.dto.board
 * author : ReplyDto
 * date : 2024-12-31
 * description :
 * ==========================================
 * DATE             AUTHOR         NOTE
 * ------------------------------------------
 * 2024-12-31          GGG        최초생성
 */
@Data
public class CommentDto {
    private Long commentNum;          // 답글 ID
    private Long boardNum;         // 문의 번호
    private String commentContent;        // 답글 내용
    private String instId;         // 작성자 ID
    private String instDt;         // 작성 날짜
}

package org.green.backend.dto.board;

import lombok.Data;

/**
 * packageName : org.green.backend.dto.board
 * author : UpdateStatusRequestDto
 * date : 2025-01-02
 * description :
 * ==========================================
 * DATE             AUTHOR         NOTE
 * ------------------------------------------
 * 2025-01-02          GGG        최초생성
 */
@Data
public class UpdateStatusRequestDto {
    private Long boardNum;
    private String boardStatusCd;
}

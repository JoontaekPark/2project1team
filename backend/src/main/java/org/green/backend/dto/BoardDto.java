package org.green.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String boardTarget;    // 문의 대상
    @NotBlank(message = "제목은 비어 있을 수 없습니다.")
    private String boardTitle;     // 제목
    @NotBlank(message = "내용은 비어 있을 수 없습니다.")
    private String boardContent;   // 내용
    @NotBlank
    private String boardGbnCd;     // 게시판 구분 (10: 1:1문의)
    @NotBlank
    private String boardStatusCd;  // 상태 코드 (10: 미확인)
    @NotBlank
    private String instId;         // 작성자 ID
}

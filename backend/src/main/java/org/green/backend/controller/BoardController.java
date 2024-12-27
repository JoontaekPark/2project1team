package org.green.backend.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.green.backend.dto.BoardDto;
import org.green.backend.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName : org.green.backend.controller
 * author : BoardController
 * date : 2024-12-27
 * description :
 * ==========================================
 * DATE             AUTHOR         NOTE
 * ------------------------------------------
 * 2024-12-27          GGG        최초생성
 */

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //1:1문의 등록
    @PostMapping("/regist")
    public ResponseEntity<String> registerBoard(
            @RequestBody BoardDto boardDto
    ) {

        boardService.registerBoard(boardDto);

        return ResponseEntity.ok("문의 등록 완료");
    }
}

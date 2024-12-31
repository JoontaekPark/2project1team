package org.green.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.green.backend.dto.board.BoardDetailDto;
import org.green.backend.dto.board.BoardDto;
import org.green.backend.dto.board.BoardListDto;
import org.green.backend.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String registerBoard(
            @Valid @RequestBody BoardDto boardDto,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/board/boardForm";
        }
        System.out.println("boardDto 잘 들어왔니?" + boardDto);
        boardService.registerBoard(boardDto);

        return "redirect:/board/list";
    }

    //1:1문의 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<List<BoardListDto>> getBoardList(
            @RequestParam("userId") String userId,
            @RequestParam("userGbnId") String userGbnId
    ) {
        System.out.println("userGbnId: " + userGbnId + ", userId: " + userId);
        List<BoardListDto> boardList = boardService.getBoardList(userId, userGbnId);
        System.out.println("boardList: " + boardList);
        return ResponseEntity.ok(boardList);
    }

    //1:1문의 상세 페이지
    @GetMapping("/detail")
    public ResponseEntity<BoardDetailDto> getBoardDetail(
            @RequestParam Long boardNum
    ) {
        BoardDetailDto detail = boardService.getBoardDetail(boardNum);
        if (detail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detail);
    }
}

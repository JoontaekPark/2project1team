package org.green.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.green.backend.dto.board.*;
import org.green.backend.service.BoardServiceImpl;
import org.green.backend.utils.JWTUtil;
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
@CrossOrigin(origins = "http://localhost:4000")
public class BoardController {

    private final BoardServiceImpl boardService;
    private final JWTUtil jwtUtil;

    //1:1문의 등록
    @PostMapping("/regist")
    public String registerBoard(
            @RequestBody BoardDto boardDto,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/board/boardForm";
        }
        System.out.println("boardDto 잘 들어왔니?" + boardDto);
        // JWT에서 사용자 ID 추출
        String token = request.getHeader("Authorization");
        String userId = jwtUtil.getId(token); // JWT에서 사용자 ID 추출

        // 작성자 ID를 DTO에 설정
        boardDto.setInstId(userId);

        boardService.registerBoard(boardDto);

        return "redirect:/board/list";
    }

    //1:1문의 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<List<BoardListDto>> getBoardList(
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization");

        String userId = jwtUtil.getId(token); // JWT에서 사용자 ID 추출
        String userGbnId = jwtUtil.getUserGbnCd(token); // JWT에서 사용자 구분 코드 추출

        List<BoardListDto> boardList = boardService.getBoardList(userId, userGbnId);
        System.out.println("boardList: " + boardList);
        return ResponseEntity.ok(boardList);
    }

    //1:1문의 상세 페이지
    @GetMapping("/detail")
    public ResponseEntity<BoardDetailDto> getBoardDetail(
            @RequestParam Long boardNum
    ) {
        System.out.println("detail: " + boardNum);
        System.out.println("dhofdksfdsklfjsdkfdfdsjkl");
        BoardDetailDto detail = boardService.getBoardDetail(boardNum);
        if (detail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detail);
    }
    //1:1 문의 댓글 등록
    @PostMapping("/comment")
    public ResponseEntity<String> addReply(
            @RequestBody CommentDto commentDto
            ) {

        boardService.addReply(commentDto);

        return ResponseEntity.ok("댓글 등록 완료");
    }
    //1:1 문의 상태관리
    @PutMapping("/update-status")
    public ResponseEntity<String> updateStatus(
            @RequestBody UpdateStatusRequestDto updateStatusRequestDto
            ) {
        boardService.updateBoardStatus(updateStatusRequestDto);

        return ResponseEntity.ok("상태 변경 완료");
    }
}

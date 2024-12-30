package org.green.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.green.backend.dto.BoardDto;
import org.green.backend.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
}

package org.green.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName : org.green.frontend.controller
 * author : BoardController
 * date : 2024-12-30
 * description :
 * ==========================================
 * DATE             AUTHOR         NOTE
 * ------------------------------------------
 * 2024-12-30          GGG        최초생성
 */
@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/board-form")
    public String boardFormRoot() {
        return "/board/board_form";
    }

    @GetMapping("/list")
    public String getBoardList() {
        return "/board/list";
    }

    @GetMapping("/board-detail")
    public String getBoardDetail() {
        return "/board/board_detail";
    }
}

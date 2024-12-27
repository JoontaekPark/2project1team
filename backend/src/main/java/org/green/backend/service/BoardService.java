package org.green.backend.service;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.BoardDto;
import org.green.backend.repository.dao.BoardDao;
import org.springframework.stereotype.Service;

/**
 * packageName : org.green.backend.service
 * author : BoardService
 * date : 2024-12-27
 * description :
 * ==========================================
 * DATE             AUTHOR         NOTE
 * ------------------------------------------
 * 2024-12-27          GGG        최초생성
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDao boardDao;

    public void registerBoard(BoardDto boardDto) {
        boardDao.insertBoard(boardDto);
    }
}

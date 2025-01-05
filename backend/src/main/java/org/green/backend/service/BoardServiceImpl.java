package org.green.backend.service;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.board.*;
import org.green.backend.repository.dao.BoardDao;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class BoardServiceImpl {

    private final BoardDao boardDao;

    public void registerBoard(BoardDto boardDto) {
        System.out.println("ServiceBoardDto" + boardDto);
        boardDao.insertBoard(boardDto);
    }
    public List<BoardListDto> getBoardList(String userId, String userGbnId ) {
        System.out.println("service" + userId + userGbnId);
        if("10".equals(userGbnId)) { //구직자
            return boardDao.getUserBoardList(userId);
        } else if("20".equals(userGbnId)) {
            return boardDao.getCompanyBoardList(userId);
        } else {
            throw new IllegalArgumentException("Invalid userGbnId");
        }
    }
    //1:1 문의 상세페이지
    public BoardDetailDto getBoardDetail(Long boardNum) {
        BoardDetailDto detail = boardDao.getBoardDetail(boardNum);
        if (detail != null) {
            List<CommentDto> replies = boardDao.getRepliesByBoardNum(boardNum);
            detail.setReplies(replies);
        }
        System.out.println(detail);

        return detail;
    }
    //1:1문의 댓글 등록
    public void addReply(CommentDto commentDto) {
        System.out.println("댓글 등록 service" + commentDto);
        boardDao.insertComment(commentDto);
    }
    //1:1문의 상태관리
    public void updateBoardStatus(UpdateStatusRequestDto updateStatusRequestDto) {
        boardDao.updateBoardStatus(updateStatusRequestDto);
    }
}

package org.green.backend.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.board.BoardDetailDto;
import org.green.backend.dto.board.BoardDto;
import org.green.backend.dto.board.BoardListDto;
import org.green.backend.dto.board.CommentDto;

import java.util.List;

/**
    * packageName : org.green.backend.repository.dao
    * author : BoardDao
    * date : 2024-12-27
    * description : 
    * ==========================================
    * DATE             AUTHOR         NOTE
    * ------------------------------------------
    * 2024-12-27          GGG        최초생성
    */

@Mapper
public interface BoardDao {
    void insertBoard(@Param("boardDto") BoardDto boardDto);
    //구직자용 조회
    List<BoardListDto> getUserBoardList(@Param("userId") String userId);
    //기업용 조회
    List<BoardListDto> getCompanyBoardList(@Param("userId") String userId);
    //1:1 문의 상세리스트
    BoardDetailDto getBoardDetail(@Param("boardNum") Long boardNum);
    //1:1 문의 댓글
    List<CommentDto>getRepliesByBoardNum(@Param("boardNum") Long boardNum);
}

package org.green.backend.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.BoardDto;

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
}

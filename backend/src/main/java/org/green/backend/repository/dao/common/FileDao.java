package org.green.backend.repository.dao.common;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.common.FileDto;

import java.util.List;

/**
 * 패키지명        : org.green.backend.repository.dao.common
 * 파일명          : FileDao
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            : 공통 파일 dao
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

@Mapper
public interface FileDao {

    void save(@Param("file") FileDto file);

    List<FileDto> findAllFileByFileGbnCdAndFileRefId(String fileGbnCd, String fileRefId);

    FileDto findOneByFileGbnCdAndFileRefId(String fileGbnCd, String fileRefId);

    List<FileDto> findAllByFilesGbnCdAndFileRefId(String fileGbnCd, String fileRefId);

    void deleteAllFileByFileGbnCdAndFileRefId(String fileGbnCd, String fileRefId);

}

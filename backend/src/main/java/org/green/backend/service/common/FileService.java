package org.green.backend.service.common;

import org.green.backend.dto.common.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 패키지명        : org.green.backend.service.common
 * 파일명          : FileService
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            : 공통 파일 서비스
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

public interface FileService {

    void saveFile(MultipartFile file,
                  String fileGbnCd,
                  String fileRefId,
                  String userId) throws IOException;

    void deleteFile(String fileNo);

    void deleteAllFiles(String fileGbnCd, String fileRefId);

    FileDto getFile(String fileGbnCd, String fileRefId);

    List<FileDto> findAllByFilesGbnCdAndFileRefId(String fileGbnCd, String fileRefId);
}

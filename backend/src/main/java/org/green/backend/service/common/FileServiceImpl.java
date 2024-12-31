package org.green.backend.service.common;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.green.backend.dto.common.FileDto;
import org.green.backend.repository.dao.common.FileDao;
import org.green.backend.utils.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 패키지명        : org.green.backend.service.common
 * 파일명          : FileServiceImpl
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            : 공통 파일저장 서비스 구현체
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileDao fileDao;
    private final FileUploadUtil fileUploadUtil;

    @Override
    public void saveFile(MultipartFile file,
                         String fileGbnCd,
                         String fileRefId,
                         String userId) throws IOException {

        FileDto fileDto = fileUploadUtil.saveFile(file, fileGbnCd, fileRefId, userId);

        try {
            fileDao.save(fileDto);
        }catch (Exception e) {
            fileUploadUtil.deleteFile(fileDto.getFileUrl() + "/" + fileDto.getFileNewName());
            throw e;
        }
    }

    @Override
    public void deleteFile(String fileNo) {

    }

    @Override
    public void deleteAllFiles(String fileGbnCd, String fileRefId) {

        List<FileDto> fileList = fileDao.findAllFileByFileGbnCdAndFileRefId(fileGbnCd, fileRefId);

        for (FileDto fileDto : fileList) {
            fileUploadUtil.deleteFile(fileDto.getFileUrl() + "/" + fileDto.getFileNewName());
        }

        fileDao.deleteAllFileByFileGbnCdAndFileRefId(fileGbnCd, fileRefId);
    }

    @Override
    public FileDto getFile(String fileGbnCd, String fileRefId) {
        return fileDao.findOneByFileGbnCdAndFileRefId(fileGbnCd, fileRefId);
    }

}

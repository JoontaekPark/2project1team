package org.green.backend.utils;

/**
 * 패키지명        : org.green.backend.utils
 * 파일명          : FileUploadUtill
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

import jakarta.annotation.PostConstruct;
import org.green.backend.dto.common.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class FileUploadUtil {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        // 업로드 디렉토리 생성
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException("업로드 디렉토리를 생성할 수 없습니다.", e);
            }
        }
    }

    /**
     * 파일 저장
     *
     * @param file 업로드할 파일
     * @param fileGbnCd 파일구분코드
     * @param fileRefId 파일 영향 받는 키
     * @param userId 작성자
     * @return 저장된 파일 경로
     * @throws IOException 파일 저장 중 오류
     */

    public FileDto saveFile(MultipartFile file,
                            String fileGbnCd,
                            String fileRefId,
                            String userId) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("빈 파일은 저장할 수 없습니다.");
        }

        // 파일 이름 생성 (UUID 사용)
        String originalFilename = file.getOriginalFilename();
        String fileExt = getFileExtension(originalFilename);
        Long fileSize = file.getSize();
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

        // 파일 저장 경로 설정
        Path targetPath = Paths.get(uploadDir, newFileName);

        // 파일 저장
        Files.copy(file.getInputStream(), targetPath);

        FileDto fileDto = new FileDto();
        fileDto.setFileGbnCd(fileGbnCd);
        fileDto.setFileRefId(fileRefId);
        fileDto.setFileNewName(newFileName);
        fileDto.setFileOldName(originalFilename);
        fileDto.setFileExt(fileExt);
        fileDto.setFileSize(fileSize);
        fileDto.setFileUrl(targetPath.toString());
        fileDto.setInstId(userId);

        return fileDto;
    }

    /**
     * 파일 확장자 추출
     *
     * @param fileName 파일 이름
     * @return 파일 확장자 (예: .jpg)
     */

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 파일 삭제
     *
     * @param filePath 삭제할 파일 경로
     * @return 삭제 성공 여부
     */

    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.delete();
    }
}

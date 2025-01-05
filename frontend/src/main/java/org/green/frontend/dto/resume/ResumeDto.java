package org.green.frontend.dto.resume;

import lombok.Data;
import org.green.frontend.dto.common.FileDto;

import java.time.LocalDate;

/**
 * packageName    : org.green.frontend.dto
 * fileName       : ResumeDto
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Data
public class ResumeDto {
        // 이력서 번호
        private Long resumeId;
        // 이력서 제목
        private String resumeTitle;
        // 희망근무 지역(작성)
        private String resumeArea;
        // Y: 맞음, N: 아님
        private String resumeMainYn;
        // 메모
        private String resumeMemo;
        // 작성자 ID
        private String instId;
        // 작성일시
        private LocalDate instDt;

        //이력서 증명사진
        private FileDto resumeFile;
    }


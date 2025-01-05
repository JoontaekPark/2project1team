package org.green.backend.dto.headhunting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobNoticeDto {
    private Long jobNoticeNum; // 채용공고 번호
    private String jobNoticeTitle; // 채용공고 제목
    private String companyName; // 회사명
    private String jobNoticeArea;
}

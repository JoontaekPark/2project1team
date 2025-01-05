package org.green.backend.dto.headhunting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFiltersDto {
    private List<String> stackFilters; // 선택된 기술 스택
    private List<String> careerFilters; // 선택된 경력
}

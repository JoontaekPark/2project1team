package org.green.backend.dto.headhunting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitialFiltersDto {
    private List<CodeInfoDto2> stackFilters;
    private List<CodeInfoDto2> careerFilters;
}

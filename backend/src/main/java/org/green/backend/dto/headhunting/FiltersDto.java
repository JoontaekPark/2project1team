package org.green.backend.dto.headhunting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltersDto {
    private List<StackDto> stacks;
    private List<CareerDto> careers;
}

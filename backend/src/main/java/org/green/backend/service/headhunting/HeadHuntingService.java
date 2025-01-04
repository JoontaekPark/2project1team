package org.green.backend.service.headhunting;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.headhunting.CareerDto;
import org.green.backend.dto.headhunting.FiltersDto;
import org.green.backend.dto.headhunting.StackDto;
import org.green.backend.repository.dao.headhunting.HeadHuntingDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeadHuntingService {
    private final HeadHuntingDao headHuntingDao;

    public FiltersDto getFilters() {
        List<StackDto> stackFilters = headHuntingDao.getStackFilters();
        List<CareerDto> careerFilters = headHuntingDao.getCareerFilters();
        return new FiltersDto(stackFilters, careerFilters);
    }
}

package org.green.backend.service.headhunting;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.headhunting.InitialFiltersDto;
import org.green.backend.dto.headhunting.CodeInfoDto2;
import org.green.backend.repository.dao.headhunting.HeadHuntingDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeadHuntingServiceImpl {
    private final HeadHuntingDao headHuntingDao;

    public InitialFiltersDto getInitialFilters() {
        List<CodeInfoDto2> stackFilters = headHuntingDao.findFiltersByCategory("stack_cd");
        List<CodeInfoDto2> careerFilters = headHuntingDao.findFiltersByCategory("job_notice_career_gbn_cd");
        System.out.println("기술스택 조회"+stackFilters + "경력 조회" + careerFilters);
        InitialFiltersDto filtersDto = new InitialFiltersDto();
        filtersDto.setStackFilters(stackFilters);
        filtersDto.setCareerFilters(careerFilters);

        return filtersDto;
    }
}

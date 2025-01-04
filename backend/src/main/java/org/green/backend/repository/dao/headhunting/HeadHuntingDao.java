package org.green.backend.repository.dao.headhunting;

import org.apache.ibatis.annotations.Mapper;
import org.green.backend.dto.headhunting.CareerDto;
import org.green.backend.dto.headhunting.StackDto;

import java.util.List;

@Mapper
public interface HeadHuntingDao {
    List<StackDto> getStackFilters();  // 기술스택 필터
    List<CareerDto> getCareerFilters();  // 경력 필터
}

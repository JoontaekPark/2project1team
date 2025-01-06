package org.green.backend.repository.dao.headhunting;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.headhunting.CodeInfoDto2;
import org.green.backend.dto.headhunting.JobNoticeDto;
import org.green.backend.dto.headhunting.SearchFiltersDto;

import java.util.List;

@Mapper
public interface HeadHuntingDao {
    List<CodeInfoDto2> findFiltersByCategory(@Param("category") String category);
    List<JobNoticeDto> search(SearchFiltersDto filters);
}

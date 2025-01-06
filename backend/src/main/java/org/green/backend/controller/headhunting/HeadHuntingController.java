package org.green.backend.controller.headhunting;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.headhunting.InitialFiltersDto;
import org.green.backend.dto.headhunting.JobNoticeDto;
import org.green.backend.dto.headhunting.SearchFiltersDto;
import org.green.backend.service.headhunting.HeadHuntingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/head-hunting")
@RequiredArgsConstructor
public class HeadHuntingController {
    private final HeadHuntingServiceImpl headHuntingService;

    @GetMapping("/filters")
    public ResponseEntity<InitialFiltersDto> getFilters() {
        InitialFiltersDto filters = headHuntingService.getInitialFilters();
        return ResponseEntity.ok(filters);
    }

    @PostMapping("/search")
    public ResponseEntity<List<JobNoticeDto>> search(@RequestBody SearchFiltersDto filters) {
        System.out.println("받은 필터 데이터: " + filters); // 요청 데이터 확인
        try {
            List<JobNoticeDto> results = headHuntingService.search(filters);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            e.printStackTrace(); // 예외 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

package org.green.backend.controller.headhunting;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.headhunting.InitialFiltersDto;
import org.green.backend.service.headhunting.HeadHuntingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/headhunting")
@RequiredArgsConstructor
public class HeadHuntingController {
    private final HeadHuntingService headHuntingService;

    @GetMapping("/filters")
    public ResponseEntity<InitialFiltersDto> getFilters() {
        InitialFiltersDto filters = headHuntingService.getInitialFilters();
        return ResponseEntity.ok(filters);
    }
}

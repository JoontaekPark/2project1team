package org.green.backend.controller.headhunting;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.headhunting.FiltersDto;
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
    public ResponseEntity<FiltersDto> getFilters() {
        FiltersDto filters = headHuntingService.getFilters();
        return ResponseEntity.ok(filters);
    }
}

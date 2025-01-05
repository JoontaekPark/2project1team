package org.green.frontend.controller.head_hunting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/head-hunting")
public class HeadHuntingController {
    @GetMapping
    public String headHuntingRoot() {
        return "/head_hunting/filters";
    }
}

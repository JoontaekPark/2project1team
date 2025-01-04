package org.green.frontend.controller.headhunting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/headhunting")
public class HeadHuntingController {
    @GetMapping
    public String headHuntingRoot() {
        return "/headhunting/filters";
    }
}

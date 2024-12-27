package org.green.frontend.controller;

import lombok.RequiredArgsConstructor;
import org.green.frontend.service.TestService;
import org.green.frontend.service.TestServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/")
    public String index() {

        System.out.println(testService.test());
        System.out.println("zzzzzzzzzzzzzzzzzz");
        return "main";
    }

}

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

    @GetMapping("/test1")
    public String test1() {
        return "example_no_aside";
    }

    @GetMapping("/test2")
    public String test2() {
        return "example_with_aside";
    }


}

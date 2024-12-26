package org.green.backend.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("/")
    public String test() {
        return "1";
    }

    @GetMapping("/api/v1/test")
    public List<String> apiTest() throws Exception {
        throw new Exception("테스트에러");
//
//        List<String> list = new ArrayList<String>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        return list;
    }


}

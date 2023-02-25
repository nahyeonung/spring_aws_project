package com.study.studyspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; //templates밑에 hello라는 이름을 가진 파일을 찾는다. static밑에 파일은 못 읽는다.
    }

    @GetMapping("test2")
    public String test2(Model model){
        return "test2";
    }
}

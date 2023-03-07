package com.study.studyspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //밑에처럼 "/"로 매핑된게 없으면 static에 가서 index.html을 가져오는데 "/"이 매핑되어 있으면 이게 우선순위다.
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/backhome")
    public String backhome() { return "home";}

    @GetMapping("/main")
    public String main() { return "main";}
}

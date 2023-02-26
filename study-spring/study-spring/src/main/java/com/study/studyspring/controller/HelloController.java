package com.study.studyspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; //templates밑에 hello라는 이름을 가진 파일을 찾는다. static밑에 파일은 못 읽는다.
    }

    @GetMapping("test2")
    public String test2(Model model) {
        return "test2";
    }

    @GetMapping("getname")
    public String getname(@RequestParam("myname") String name, Model model){
        model.addAttribute("data", name);
        return "getname";
    }

    @GetMapping("makeAPI")
    @ResponseBody
    //public 리턴타입 메소드명 순의 형식이여서 강의에서는 Hello를 넣었지만 나는 Object를 넣어봤다.
    public Object makeAPI(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        // getter setter를 사용하는 것이 자바 빈 규약이다.
        // name변수가 private으로 선언 되었기 때문에 밖에서 건드리지 못하는데 이때 getter setter로 접근할 수 있다.
        public String getName(){
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

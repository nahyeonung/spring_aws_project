package com.study.studyspring.controller;

import com.study.studyspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    //new로 생성하지 않고 DI로 쓰는 이유
    //MemberController말고 다른 controller에서도 MemberService를 가져다 쓸 쑤 있는데,
    //그럴 때 마다 new로 객체를 생성하면 여러 개의 객체가 생성되고 그렇게 쓸 필요가 없기 때문이다.
    //밑에 DI코딩이 Spring container에 객체를 딱 하나 등록하고 그걸 쓸 수 있도록 하는 방법이다.
    private final MemberService memberService;

    //Autowired로 Spring이 Spring container에 있는 memberservice로 자동으로 연결을 시켜준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}

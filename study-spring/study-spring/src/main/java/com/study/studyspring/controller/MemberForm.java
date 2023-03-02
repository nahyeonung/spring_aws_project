package com.study.studyspring.controller;

//폼 데이터: HTML 요소인 form 태그에 담긴 데이터
//컨트롤러는 폼 데이터를 객체에 담아 받는다. 이때, 폼 데이터를 받는 객체를 DTO(Data Transfer Object)라고 한다.
//밑에 MemberForm이 그 DTO의 역할을 위해 만들어진 것이다.
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}

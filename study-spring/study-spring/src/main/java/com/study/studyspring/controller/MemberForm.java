package com.study.studyspring.controller;

//폼 데이터: HTML 요소인 form 태그에 담긴 데이터
//컨트롤러는 폼 데이터를 객체에 담아 받는다. 이때, 폼 데이터를 받는 객체를 DTO(Data Transfer Object)라고 한다.
//밑에 MemberForm이 그 DTO의 역할을 위해 만들어진 것이다.
public class MemberForm {
    private String name;
    //view에서 넘어온 key와 value쌍이 setter를 통해서 자동으로 필드값에 set해준다. 그래서 setter이름을 필드 값과 맞춰서 만들어야
    //자동으로 setter함수가 돌 수 있다!!
    private String login_id;

    private String pwd;

    private String filepath;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLoginId() {return login_id;}

    public void setLogin_id(String login_id) { this.login_id = login_id;}

    public String getPwd() {return pwd;}

    public void setPwd(String pwd) { this.pwd= pwd;}

    public String getFilepath() {return filepath;}

    public void setFilepath(String filepath){this.filepath = filepath;}
}

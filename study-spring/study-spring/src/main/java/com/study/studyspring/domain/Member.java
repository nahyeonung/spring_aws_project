package com.study.studyspring.domain;

public class Member {

    private Long id;
    private String name;
    private String login_id;
    private String pwd;

    private String filepath;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLoginId() { return login_id; }

    public void setLoginId(String login_id) { this.login_id = login_id;}

    public String getPwd() {return pwd;}

    public void setPwd(String pwd){ this.pwd = pwd;}

    public String getFilepath() {return filepath;}

    public void setFilepath( String filepath) {this.filepath = filepath;}
}

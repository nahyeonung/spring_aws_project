package com.study.studyspring.service;

import com.study.studyspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    
    @BeforeEach
    public void beforeEach(){
        //함수 시작하기 전에 여기서 객체를 만들고 위에서 설정함
        memberRepository = new MemoryMemberRepository();
        //DI방식
        memberService = new MemberService(memberRepository);
    }
    @Test
    void join() {
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
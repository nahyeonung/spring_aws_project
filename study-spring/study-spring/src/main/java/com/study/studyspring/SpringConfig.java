package com.study.studyspring;

import com.study.studyspring.repository.MemberRepository;
import com.study.studyspring.repository.MemoryMemberRepository;
import com.study.studyspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}

package com.study.studyspring.service;

import com.study.studyspring.domain.Member;
import com.study.studyspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //Spring이 MemberService를 Service로 인식해서 Spring Container로 넣어줄려고 Service를 씀
public class MemberService {
    private final MemberRepository memberRepository;

    //DI방식
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Long join(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("중복된 이름입니다.");
                });
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}

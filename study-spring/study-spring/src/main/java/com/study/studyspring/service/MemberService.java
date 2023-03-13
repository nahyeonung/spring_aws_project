package com.study.studyspring.service;

import com.study.studyspring.domain.Member;
import com.study.studyspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Service //Spring이 MemberService를 Service로 인식해서 Spring Container로 넣어줄려고 Service를 씀
public class MemberService {
    private final MemberRepository memberRepository;

    //DI방식
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void imgUpdate(String name, MultipartFile file) throws Exception{
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";
        UUID uuid = UUID.randomUUID();
        String filename = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, filename);
        file.transferTo(saveFile);
        memberRepository.imgSave(name, filename);
    }
    public Long join(Member member) {
        Optional<Member> result = memberRepository.findByName((member.getName()));
        if(result.isPresent()){
            return (long) -1;
        }
        else{
            memberRepository.save(member);
            return member.getId();
        }
//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//                    System.out.println("스파클"+m);
//                    throw new IllegalStateException("중복된 이름입니다.");
//                });
//        memberRepository.save(member);
//        return member.getId();
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<String> findOne(String memberId, String memberPwd){
        return memberRepository.findById(memberId, memberPwd);
    }


}

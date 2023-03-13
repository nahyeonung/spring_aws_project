package com.study.studyspring.repository;

import com.study.studyspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<String> findById(String id, String pwd);
    Optional<Member> findByName(String name);
    List<Member> findAll();
    void imgSave(String name, String filePath);
}

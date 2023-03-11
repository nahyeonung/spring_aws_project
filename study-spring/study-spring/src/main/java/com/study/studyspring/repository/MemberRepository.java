package com.study.studyspring.repository;

import com.study.studyspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(String id, String pwd);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}

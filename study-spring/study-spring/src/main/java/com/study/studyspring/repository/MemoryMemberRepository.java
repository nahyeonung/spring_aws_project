package com.study.studyspring.repository;

import com.study.studyspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository //Service와 같은 이유
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long seq = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++seq);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(String id, String pwd) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(memeber -> memeber.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clear(){
        store.clear();
    }
}

package com.study.studyspring.repository;

import com.study.studyspring.domain.Member;

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
    public Optional<String> findById(String id, String pwd) {
        return Optional.ofNullable(String.valueOf(store.get(id)));
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

    @Override
    public void imgSave(String name, String filePath) {

    }

    @Override
    public void contentSave(String title, String content, String date, String name) {

    }

    @Override
    public Optional<String> findFile(String name) {
        return null;
    }

    public void clear(){
        store.clear();
    }
}

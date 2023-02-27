package com.study.studyspring.respository;

import com.study.studyspring.domain.Member;
import com.study.studyspring.repository.MemberRepository;
import com.study.studyspring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //AfterEach: 함수가 하나 끝날 때 마다 돌아와서 지정된 함수를 실행시켜준다.
    //밑의 test함수들을 한번에 실행히시키면 repository가 지저분해지니까 계속 정리시켜주면서 함수를 돌릴 수 있다.
    @AfterEach
    public void afterEach(){
        repository.clear();
    }

    
    @Test
    public void save() {
        Member member = new Member(); //SHIFT + F6하면 설정된 같은 이름들을 한 번에 바꿀 수 있음
        member.setName("test"); //객체에 setName으로 이름을 저장
        
        repository.save(member);
        
        //findById를 통해 DB의 값과 객체에 저장된 값이 같은지 확인함. 같으면 저장 시 DB에 제대로 등록 된 것임
        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
        
    }

    @Test
    public void SearchName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member1);

        Member result = repository.findByName("spring1").get();

        System.out.println(member1.getName());
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}

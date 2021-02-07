package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //기능이한번끝나면 호출이되는것
    //하나의테스트가 끝날때마다 저장소들을 깔끔하게 지워져야한다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member= new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " +(result==member));
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result= repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findALl(){
        Member member1= new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result=repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}

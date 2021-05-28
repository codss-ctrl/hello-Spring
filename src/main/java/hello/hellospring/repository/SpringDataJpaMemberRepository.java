package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//JpaRepository를 이용해 스프링 빈에 자동으로 등록
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    //JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}

package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository; //spring자체 라이브러리

import java.util.Optional;

//SpringDataJpa 인터페이스가 JpaRepository 인터페이스를 받고 있으면 구현체도 자동으로 만들어주고 상속받은 애들 자동으로 스프링 빈에 등록까지 해줌
//JpaRepository 라이브러리 안에 findById, findByName 등등 미리 다 정의되어 있음. 기본적인 CRUD 기능들은 다 제공됨
//스프링 데이터 JPA가 SpringDataJpaMemberRepository 를 스프링 빈으로 자동 등록해준다
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //MemberRepository 자동으로 스프링 빈에 등록

    @Override
    Optional<Member> findByName(String name); //findByName 자동으로 구현됨
}

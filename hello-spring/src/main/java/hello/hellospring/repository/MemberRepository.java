package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //null값 그대로 반환x, Optional로 감싸서 반환하는 것 요즘 선호(java 8)
    Optional<Member> findByName(String name);
    List<Member> findAll();
}

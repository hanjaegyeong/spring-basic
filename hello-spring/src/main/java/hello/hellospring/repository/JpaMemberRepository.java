package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

//자바의 표준 인터페이스 JPA. 구현은 여러 업체들이 함(대표적 구현체는 hibernate)
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) { //jpa를 쓰려면 entity manager를 주입받아야 함
        this.em = em;
    }

    //저장, 조회, 삭제, 업데이트는 sql 짤 필요 없이 자동으로 해줌(기본키(pk)기반인 애들)
    @Override
    public Member save(Member member) {
        em.persist(member); //얘가 setid까지 다 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    //기본키(pk)기반이 아닌 find같은 경우엔 jpql 작성해줘야 함(쿼리문)
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //객체 m 자체를 select, Member와 m사이에 as생략된 것
                .getResultList();
    }
}

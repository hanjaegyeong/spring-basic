package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

//자바의 표준 인터페이스 JPA. 구현은 여러 업체들이 함(대표적 구현체는 hibernate)
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //엔티티 관리해주는 매니저. 내부에 영속성 컨텍스트(Persistence Context) 갖고 있음

    public JpaMemberRepository(EntityManager em) { //jpa를 쓰려면 entity manager를 주입받아야 함
        this.em = em;
    }

    //저장, 조회(기본키(pk)id기반일 때), 삭제, 업데이트는 sql 짤 필요 없이 자동으로 해줌
    @Override
    public Member save(Member member) {
        em.persist(member); //얘가 setid까지 다 해준 뒤 em에 save해줌
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
        //Member(테이블) as m으로 하고, 테이블.name = setParameter된 name인 애들 찾아서 List형으로 반환
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

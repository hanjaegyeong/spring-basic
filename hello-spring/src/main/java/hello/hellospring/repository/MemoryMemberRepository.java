package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//해당 애노테이션으로 스프링이 해당 레포지토리 객체를 스프링 컨테이너에 삽입
@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();  //Map에 id(key)-member(value) 쌍 삽입: id로 member 검색할 수 ㅣㅇㅆ도록
    private static long sequence = 0L; //0: int값, L: Long형, id값에 들어가는 인자

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {  //해당 id 데이터 유무 탐색
        return Optional.ofNullable(store.get(id)); //원래 store.get(id)만 하면 되는데 null반환 가능성이 있으니 Optional로 감싸야함
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 루프 돌면서 하나 찾아지면 바로 반환(findAny) 하나도 없으면 optional null 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))  //람다 사용
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    } //전체 member list형으로 리턴

    public void clearStore() {
        store.clear();
    } //test에서 afterEach에서 사용될 저장소 clear 함수
}

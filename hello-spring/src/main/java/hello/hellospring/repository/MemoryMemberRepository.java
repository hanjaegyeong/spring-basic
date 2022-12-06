package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
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
    }

    public void clearStore() {
        store.clear();
    }
}

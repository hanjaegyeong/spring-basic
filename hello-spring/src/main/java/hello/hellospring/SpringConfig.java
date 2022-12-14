package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//멤버레포지토리 인터페이스의 구현체를 메모리->실제 DB로 바꾼것
//그럼에도 기존코드는 수정 없음. 스프링에서 제공하는 configuration파일만 수정: 객체지향의 "다형성" 이용해 "개방폐쇄 원칙" 지켜진 것
@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) { //intellij 세팅에 따라 빨간 줄 생길 수 있지만 동작에 문제 없는 거라 넘어가도 됨
        this.em = em;
    }

    @Bean //빈 생성해줌
    public MemberService memberService() {
        return new MemberService(memberRepository());  //이전에 autowired 했던 것과 유사
    }
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); //의존성 주입 이용하면 기존코드 변경없이 설정만으로 구현클래스 변경가능
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}

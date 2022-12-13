package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//멤버레포지토리 인터페이스의 구현체를 메모리->실제 DB로 바꾼것
//그럼에도 기존코드는 수정 없음. 스프링에서 제공하는 configuration파일만 수정: 객체지향의 "다형성" 이용해 "개방폐쇄 원칙" 지켜진 것
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    //스프링이 데이터베이스와 연결할 수 있는 데이터소스도 자동으로 생성해줘서 주입해줌
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean //빈 생성해줌
    public MemberService memberService() {
        return new MemberService(memberRepository());  //이전에 autowired 했던 것과 유사
    }
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource); //의존성 주입 이용하면 기존코드 변경없이 설정만으로 구현클래스 변경가능
    }
}

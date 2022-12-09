package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//컨트롤러 애노테이션 있으면 스프링이 자동으로 스프링 컨테이너에 객체 삽입해줌
@Controller
public class MemberController {
    private final MemberService memberService;

    //생성자에 Autowired라고 되어있으면 스프링 컨테이너에 있는 멤버서비스(매개변수)를 가져와 자동연결시켜줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}

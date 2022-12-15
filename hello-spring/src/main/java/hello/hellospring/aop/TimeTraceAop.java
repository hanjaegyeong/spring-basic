package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


//AOP: 프록시(가짜 스프링 빈)과 진짜 스프링 빈을 왔다갔다 하게 해주는 기술. 공통로직을 프록시에 설정해두고 joinPoint로 중간에 진짜 빈을 호출해서 사용
//아래는 프록시 내부 코드라고 보기
@Aspect
public class TimeTraceAop {
    
    //Around는 Aop 적용 범위를 지정해주는 애노테이션. 현재는 hellospring 패키지 내 모든 시스템에 적용됨
    //특정 타겟 제외하는 것은 순환참조 문제 없애기 위해. -> 전체범위에 적용되면 config파일에서 자기 자신인 TimeTraceAop를 생성하는 코드도 aop처리되므로 순환참조 오류
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            return joinPoint.proceed(); //joinPoing.proceed()가 호출되면 진짜 스프링 빈이 호출됨.
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}

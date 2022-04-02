package yoon.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import yoon.aop.order.aop.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello",String.class);
    }

    @Test
    void printMethod(){
        //execution(* ..package..Class..)
        //public java.lang.String yoon.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod={}",helloMethod);
    }

    @Test
    void withinMatch(){
        pointcut.setExpression("within(yoon.aop.order.aop.member.MemberServiceImpl)");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinStar(){
        pointcut.setExpression("within(yoon.aop.order.aop.member.*Service*)");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //서브 패키지 까지 매칭
    @Test
    void withinSubPackage(){

        pointcut.setExpression("within(yoon.aop..*)");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //타켓의 타입에만 직접 적용, 인터페이스를 선정하면 안됨 <=execution과의 차이
    @Test
    void withinSuperTypeFalse(){
        pointcut.setExpression("within(yoon.aop.order.aop.member.MemberService)");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }

    //execution은 타입기반, 인터페이스 선정가능
    @Test
    void executionSuperTypeFalse(){
        pointcut.setExpression("execution(* yoon.aop.order.aop.member.MemberService.*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }


}

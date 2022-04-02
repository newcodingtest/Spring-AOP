package yoon.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import yoon.aop.order.aop.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ExecutionTest {

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
   // @DisplayName("가장 정확한 포인트컷")
    void exactMatch(){
        //public java.lang.String yoon.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        pointcut.setExpression("execution(public String yoon.aop.order.aop.member.MemberServiceImpl.hello(String))");
       //AssertJ꺼 사용
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    //@DisplayName("가장많이 생략한 포인트컷")
    void allMatch(){
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //이름으로 매치
    @Test
    void nameMatch(){
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //패턴 매치
    @Test
    void nameMatchStar1(){
        pointcut.setExpression("execution(* hel*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //패턴 매치
    @Test
    void nameMatchStar2(){
        pointcut.setExpression("execution(* *el*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //실패 케이스
    @Test
    void nameMatchFalse(){
        pointcut.setExpression("execution(* nono(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }
    
    //패키지 관련 포인트컷
    @Test
    void packageExactMatch1(){

        pointcut.setExpression("execution(* yoon.aop.order.aop.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch2(){

        pointcut.setExpression("execution(* yoon.aop.order.aop.member.*.*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //실패 케이스
    @Test
    void packageExactFalse(){

        pointcut.setExpression("execution(* yoon.aop.*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }

    //서브 패키지 까지 매칭
    @Test
    void packageMatchSubPackage1(){

        pointcut.setExpression("execution(* yoon.aop..*.*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //타입 매치
    @Test
    void typeExactMatch(){

        pointcut.setExpression("execution(* yoon.aop.order.aop.member.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //슈퍼 타입 매치
    @Test
    void typeMatchSuperType(){

        pointcut.setExpression("execution(* yoon.aop.order.aop.member.MemberService.*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //자식 타입에만 선언되어있는 메소드를 슈퍼타입으로 테스트가 가능한가? => 불가능
    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(* yoon.aop.order.aop.member.MemberService.*(..))");

        Method internalMethod =  MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod,MemberServiceImpl.class)).isFalse();
    }

    //String 타입 파라미터 매칭
    @Test
    void argsMatch(){
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //파라미터 없는  매칭
    @Test
    void argsMatchNoArgs(){
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }

    //정확히 하나의 파라미터 허용 , 모든 타입 매칭
    @Test
    void argsMatchStar(){
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //파라미터 개수 무관 모든 파라미터 , 모든 타입 매칭
    @Test
    void argsMatchAll(){
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //String 타입으로 시작, 파라미터 개수 무관 , 모든 타입 매칭
    @Test
    void argsMatchComplex(){
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

}

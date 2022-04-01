package yoon.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

    /*
    //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
    @Around("yoon.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{

        try {
            //@Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e){
            //@AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        }finally {
            //@After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

     */

    @Before("yoon.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{

        log.info("[before 시작] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "yoon.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) throws Throwable{

        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    @AfterReturning(value = "yoon.aop.order.aop.Pointcuts.allOrder()", returning = "result")
    public void doReturn2(JoinPoint joinPoint, String result) throws Throwable{

        log.info("[return2] {} return2={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "yoon.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doTrowing(JoinPoint joinPoint, Exception ex) throws Throwable{

        log.info("[ex] {} message={}", ex);
    }

    @After(value = "yoon.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) throws Throwable{
        //@Before
        log.info("[after] {}", joinPoint.getSignature());
    }
}

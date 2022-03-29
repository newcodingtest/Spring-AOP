package yoon.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV1 {

    @Around("execution(* yoon.aop.order..*(..))") // 포인트컷
    public Object doLog(ProceedingJoinPoint joinPoint)throws Throwable{ // 어드바이스
        log.info("[log] {}", joinPoint.getSignature()); // 호출되는 메서드의 모든 정보들을 확인
        return joinPoint.proceed();
    }



}

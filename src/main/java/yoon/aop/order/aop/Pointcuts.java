package yoon.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

//공용 포인트컷으로 빼두어 참조하여 사용하게끔---> AspectV4Pointcuts 에서 사용중
public class Pointcuts {

    //선언된 패키지와 하위 패키지
    @Pointcut("execution(* yoon.aop.order..*(..))")
    public void allOrder(){} //pointcut signature

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    //allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}

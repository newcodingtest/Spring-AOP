package yoon.aop.order.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    //수정자 주입을 통한 순환참조로 aop 내부 메소드 호출시 실제 객체가 아닌 프록시 객체를 통하여 메서드 호출하여 
    //aop 적용
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1){
        log.info("callServiceV1 setter={}", callServiceV1.getClass());
         this.callServiceV1 = callServiceV1;
    }


    //Aop의 한계 대안 방안
    public void external(){
        log.info("call external");
        callServiceV1.internal(); //외부 메서드 호출
    }

    public void internal(){
        log.info("call internal");
    }
}

package yoon.aop.order.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV0 { 

    //Aop의 한계, 내부 메서드를 호출하면 aop가 적용이 안됨
    public void external(){
        log.info("call external");
        internal(); //내부 메서드 호출(this.internal())
    }

    public void internal(){
        log.info("call internal");
    }
}

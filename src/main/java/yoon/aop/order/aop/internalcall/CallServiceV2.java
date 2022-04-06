package yoon.aop.order.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//프록시의 한계 대응방안: 지연조회
public class CallServiceV2 {

    // private final ApplicationContext applicationContext;
    // ObjectProvider는 객체를 스프링 컨테이너에서 조회하는 것을 스프링 빈 생성 시점이 아니라
    // 실제 객체를 사용하는 시점으로 지연할 수 있다.
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider){
        this.callServiceProvider = callServiceProvider;
    }

    public void external(){
        log.info("call external");
        CallServiceV2 callServiceV2 = callServiceProvider.getObject();
        callServiceV2.internal(); //외부 메서드 호출
    }

    public void internal(){
        log.info("call internal");
    }
}

package yoon.aop.order.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
//프록시의 한계 대응방안: 구조변경
public class CallServiceV3 {

    private final InternalService internalService;

    public void external(){
        log.info("call external");
        internalService.internal();
    }


}

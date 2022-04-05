package yoon.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import yoon.aop.order.aop.internalcall.CallServiceV0;
import yoon.aop.order.aop.internalcall.aop.CallLogAspect;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
public class CallServiceV0Test {

    @Autowired
    CallServiceV0 serviceV0;

    @Test
    void external(){
        serviceV0.external();
    }

    @Test
    void internal(){
        serviceV0.internal();
    }
}

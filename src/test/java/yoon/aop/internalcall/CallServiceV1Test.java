package yoon.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import yoon.aop.order.aop.internalcall.CallServiceV0;
import yoon.aop.order.aop.internalcall.CallServiceV1;
import yoon.aop.order.aop.internalcall.aop.CallLogAspect;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
public class CallServiceV1Test {

    @Autowired
    CallServiceV1 callServiceV1;

    @Test
    void external(){
        callServiceV1.external();
    }


}

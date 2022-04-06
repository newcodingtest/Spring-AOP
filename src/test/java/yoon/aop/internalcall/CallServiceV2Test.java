package yoon.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import yoon.aop.order.aop.internalcall.CallServiceV1;
import yoon.aop.order.aop.internalcall.CallServiceV2;
import yoon.aop.order.aop.internalcall.aop.CallLogAspect;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
public class CallServiceV2Test {

    @Autowired
    CallServiceV2 callServiceV2;

    @Test
    void external(){
        callServiceV2.external();
    }


}

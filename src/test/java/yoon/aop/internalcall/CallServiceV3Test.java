package yoon.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import yoon.aop.order.aop.internalcall.CallServiceV2;
import yoon.aop.order.aop.internalcall.CallServiceV3;
import yoon.aop.order.aop.internalcall.aop.CallLogAspect;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
public class CallServiceV3Test {

    @Autowired
    CallServiceV3 callServiceV3;

    @Test
    void external(){
        callServiceV3.external();
    }


}

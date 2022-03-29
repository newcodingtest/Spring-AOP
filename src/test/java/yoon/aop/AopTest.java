package yoon.aop;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import yoon.aop.order.OrderRepository;
import yoon.aop.order.OrderService;
import yoon.aop.order.aop.AspectV1;

@Slf4j
@SpringBootTest
@Import(AspectV1.class)
public class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void appInfo(){
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success(){
        orderService.orderItem("itemA");
    }


    @Test
    void exception(){
        Assertions.assertThatThrownBy(() ->  orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }



}

package yoon.aop.order.aop.member;

import org.springframework.stereotype.Component;
import yoon.aop.order.aop.member.annotation.ClassAop;
import yoon.aop.order.aop.member.annotation.MethodAop;

import java.lang.reflect.Member;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param){
        return "ok";
    }
}

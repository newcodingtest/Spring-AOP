package yoon.aop.order.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) //실제 실행할때까지 해당 어노테이션이 살아있는것-->실행시 동적으로 어노테이션 읽기 가능
public @interface ClassAop {
}

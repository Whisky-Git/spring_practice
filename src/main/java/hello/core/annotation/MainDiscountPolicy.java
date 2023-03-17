package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;
/*
* 해당 어노테이션은 필드, 메서드, 파라미터, 타입, 어노테이션 타입 에 전부 사용 가능하다.
* 런타임 시에 동작한다.
* 상속이 된다.
* @Qualifier 동작을 한다.
* */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}

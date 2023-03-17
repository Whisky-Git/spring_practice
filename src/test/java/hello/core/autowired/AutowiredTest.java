package hello.core.autowired;


import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    }

/*
* ----- 결과 --------
* setNoBean2 = null
* setNoBean3 = Optional.empty
* @Nullable, Optional 은 스프링 전반에 걸쳐서 지원된다. 생성자 자동 주입에서 특정 필드에만 사용해도 된다.
*
* */
    static class TestConfig {

        //Autowired 빨간색 밑줄이 그어져 있는 경우, 옵션을 설정해서 꺼줘야함.

        //호출이 안된다.
        @Autowired(required = false)
        public void setBean1(Member member) {
            System.out.println("setBean1 = " + member);
        }

        //null 호출
        @Autowired
        public void setBean2(@Nullable Member member) {
            System.out.println("setBean2 = " + member);
        }

        //Optional.empty 호출
        @Autowired
        public void setBean3(Optional<Member> member) {
            System.out.println("setBean3 = " + member);
        }
    }
}
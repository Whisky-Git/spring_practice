package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

    @Test
    @DisplayName("싱글톤 서비스 테스트")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        statefulService1.order("userA", 10000); //사용자 A 10000원 주문
        statefulService2.order("userB", 20000); //사용자 B 10000원 주문

        System.out.println("statefulService1.getPrice() = " + statefulService1.getPrice()); //20000원
        System.out.println("statefulService2.getPrice() = " + statefulService2.getPrice()); //20000원
        // 유지되면 사용자 A가 10000원을 주문했더라도 상태가 공유되기 때문에 B가 주문한 20000원 값이 들어가게 되는 것을 확인할 수 있다.
        assertThat(statefulService1.getPrice()).isEqualTo(20000);
        //A,B 둘다 20000원 이라 true

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //스프링에 등록된 모든 빈 이름을 조회한다.
        for(String beanDefinitionName : beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName);           //빈 이름으로 빈 객체(인스턴스)를 조회한다.
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        //스프링이 내부에서 사용하는 빈은 제외하고, 내가 등록한 빈만 출력하는 테스트
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();  //bean list 선언

        for (String beanDefinitionName : beanDefinitionNames) { //foreach 로 bean 정의된 값 담아놓음
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //내부에서 사용하는 빈은 getRole()로 구분할 수 있다.
            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name=" + beanDefinitionName + " object=" + bean);
            }
        }
    }

}

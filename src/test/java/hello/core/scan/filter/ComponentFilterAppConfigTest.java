package hello.core.scan.filter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();
        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class));
    }
    @Configuration
    @ComponentScan(
            //includeFilters 에 @MyIncludeComponent 추가하여 BeanA가 스프링 빈에 등록된다.
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            //excludeFilters 에 @MyExcludeComponent 추가하여 BeanB가 스프링 빈에 등록된다.
            excludeFilters =
                    {
                            // FilterType.ANNOTATION : 기본값, 어노테이션을 인식해서 동작한다.
                            @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
                            // FilterType.ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해서 동작한다.
//                            @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
                    }
    )
    static class ComponentFilterAppConfig {
    }
}
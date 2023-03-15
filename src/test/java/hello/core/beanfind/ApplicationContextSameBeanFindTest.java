package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    /*
    * 스프링 빈 조회 - 상속 관계
    * 부모 타입으로 조회하면, 자식 타입도 함께 조회.
    * therefore 모든 자바 객체의 최고 부모 Object 타입으로 조회하면, 모든 스프링 Bean 을 조회. */
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate(){
        // No qualifying bean of type 'hello.core.member.MemberRepository' available: expected single matching bean but found 2: memberRepository1,memberRepository2
        // 같은 타입이 둘 이상.
        // MemberRepository bean = ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () ->
                ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 빈 이름으로 지정하면 된다.")
    void findBeanByName(){
        //타입으로 조회 시 같은 타입의 스프링 bean 이 둘 이상이면 오류 발생.
        //이 때는 빈 이름을 지정하자.
        //ac.getBeanOfType() 을 사용하면 해당 타입의 모든 빈을 조회할 수 있다.
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);

        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

        //keySet() : Map 에서 iterator 인터페이스를 이용하기 위해 사용되는 메서드.

        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }

        assertThat(beansOfType.size()).isEqualTo(2);
        // >>> 결과
        // 2개로 일치함.
        // key = memberRepository1value = hello.core.member.MemoryMemberRepository
        // key = memberRepository2value = hello.core.member.MemoryMemberRepository
    }


    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }


}

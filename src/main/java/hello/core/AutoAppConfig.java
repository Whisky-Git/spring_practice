package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;
/*
* 기존의 AppConfig 와 다르게 @Bean 등록한 클래스가 없다.
* ComponentScan 을 사용하려면 어노테이션을 설정 정보에 붙여주면 된다.
*
* - 컴포넌트 스캔 사용 시 @Configuration 이 붙은 설정 정보도 자동으로 등록된다.
* - AppConfig, TestConfig 등 앞서 만들어둔 설정 정보도 함께 등록되고 실행된다.
* - excludeFilters 를 이용해서 설정 정보는 컴포넌트 스캔 대상에서 제외한다.
* */

@Configuration
@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}

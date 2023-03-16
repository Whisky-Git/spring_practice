package hello.core;

import hello.core.discount.*;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    //리팩터링 - 중복 제거 및 역할에 따른 구현이 보이도록 한다.
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    //memberRepository() 중복 제거 , MemoryMemberRepository 를 다른 구현체로 변경할 떄 한 부분만 변경하면 된다.
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }
}

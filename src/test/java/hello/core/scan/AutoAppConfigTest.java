package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {


    // @Autowired 는 타입 매칭을 시도 -> 여러 빈 존재 -> 필드 이름, 파라미터 이름으로 빈 추가 매칭
    // 필드명 매칭은 먼저 타입 매칭 시도 후 그 결과에 여러 빈이 있을 때 추가로 동작하는 기능이다.
    /*
    * 매칭 정리
    * 1. 타입 매칭
    * 2. 타입 매칭의 결과가 2개 이상일 때 필드 명, 파라미터 명으로 빈 이름을 매칭한다.
    * */
    @Autowired
    private DiscountPolicy ratediscountPolicy; //필드 명이 rateDiscountPolicy 이므로 정상 주입된다.


    @Test
    @DisplayName("자동 config 빈 등록 확인")
    void basicScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}

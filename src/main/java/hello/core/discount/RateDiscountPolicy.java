package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10; //10% 할인
    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP){
            //10퍼 할인
            return price * discountPercent / 100;
        }
        else {
            return 0;
        }

    }
}

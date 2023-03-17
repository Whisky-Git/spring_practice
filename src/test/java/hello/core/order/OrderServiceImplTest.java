package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.Order;
import hello.core.discount.OrderServiceImpl;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    @Test
    void createOrder(){

        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "catsbi", Grade.VIP));

//        여기까지만 작성했으면 NPE(nullpointerException) 발생 -> DiscountPolicy , MemberRepository 의존 주입을 하지 않았기 때문이다.
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.createOrder(1L, "itemA", 10000);

//        생성자 주입을 사용하면 주입 데이터를 누락했을 때 컴파일 오류가 발생한다.
/*        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.setMemberRepository(memberRepository);
        orderService.setDiscountPolicy(new FixDiscountPolicy());

        Order order = orderService.createOrder(1L,"itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);*/
    }
}

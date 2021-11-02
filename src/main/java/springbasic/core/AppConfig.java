package springbasic.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springbasic.core.discount.DiscountPolicy;
import springbasic.core.discount.FixDiscountPolicy;
import springbasic.core.discount.RateDiscountPolicy;
import springbasic.core.member.MemberRepository;
import springbasic.core.member.MemberService;
import springbasic.core.member.MemberServiceImpl;
import springbasic.core.member.MemoryMemberRepository;
import springbasic.core.order.OrderService;
import springbasic.core.order.OrderServiceImpl;

@Configuration // 스프링으로 사용하기 위한 설정
public class AppConfig {
    /*
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository()); // 생성자 주입
        // 반환값이 인터페이스이면 하위 클래스도 반환 가능
    }
     */
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService"); // ConfigurationSingletonTest 테스트용
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
        // 반환값이 인터페이스이면 하위 클래스도 반환 가능
    }

    // AppConfig에서도 각 역할의 분리
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberService"); // ConfigurationSingletonTest 테스트용
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.memberService"); // ConfigurationSingletonTest 테스트용
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}

package springbasic.core.autowired;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbasic.core.AutoAppConfig;
import springbasic.core.discount.DiscountPolicy;
import springbasic.core.member.Grade;
import springbasic.core.member.Member;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {
    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;
        /*
            AnnotationConfigApplicationContext에 DiscountService.class만 넣을 시 의존성주입에 아무것도 값이 안들어간다.
            AutoAppConfig.class, DiscountService.class를 같이 넣으면 컨테이너에 등록이 될 것이고
            AutoAppConfig의 컴포넌트 스캔으로 구현체 RateDiscountPolicy와 FixDiscountPolicy의 컴포넌트도 확인되어 빈으로 등록되어 있다.
            @Autowired로 인해 빈이름과 빈객체가 Map에, 빈객체가 List에 주입된다.
        */
        @Autowired // 위의 AutoAppConfig.class를 넣음으로써 Map으로 모든 DiscountPolicy 를 주입받는다. 이때 fixDiscountPolicy, rateDiscountPolicy 가 주입된다
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);
            return discountPolicy.discount(member, price);
        }
    }
}

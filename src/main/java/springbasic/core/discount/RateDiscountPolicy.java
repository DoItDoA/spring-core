package springbasic.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springbasic.core.annotation.MainDiscountPolicy;
import springbasic.core.member.Grade;
import springbasic.core.member.Member;

@Component
//@Qualifier("mainDiscountPolicy") // 같은 타입의 빈(FixDiscountPolicy)과 구분하기위해 사용
//@Primary // 같은 타입의 빈중 우선순위를 높여 찾게 한다 Qualifier와 구분하여 쓰지만 같이 쓸 때 Qualifier가 우선순위 더 높다
@MainDiscountPolicy // 만든 애노테이션으로 @Qualifier 역할함
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}

package springbasic.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import springbasic.core.member.Grade;
import springbasic.core.member.Member;

@Component
@Qualifier("fixDiscountPolicy") // 같은 타입의 빈(RateDiscountPolicy)과 구분하기위해 사용
public class FixDiscountPolicy implements DiscountPolicy{
    
    private int discountFixAmount = 1000; // 1000원 할인
    
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}

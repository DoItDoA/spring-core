package springbasic.core.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import springbasic.core.annotation.MainDiscountPolicy;
import springbasic.core.discount.DiscountPolicy;
import springbasic.core.discount.FixDiscountPolicy;
import springbasic.core.discount.RateDiscountPolicy;
import springbasic.core.member.Member;
import springbasic.core.member.MemberRepository;
import springbasic.core.member.MemoryMemberRepository;

@Component
//@RequiredArgsConstructor // 롬복의 기능으로써 생성자주입을 안써도 생성자주입 역할을 해준다
public class OrderServiceImpl implements OrderService {

    // DIP 와 OCP 원칙에 따르려면 추상체(인터페이스)에만 의존해야한다
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 추상체(DiscountPolicy)와 구현체(FixDiscountPolicy) 둘 다 의존 (DIP 위반)
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 추상체(DiscountPolicy)와 구현체(RateDiscountPolicy) 둘 다 의존 (DIP 위반)

    private final DiscountPolicy discountPolicy; // 추상체에만 의존
    private final MemberRepository memberRepository; // final은 생성자 주입시 사용해야한다

    /* 필드주입
    @Autowired private DiscountPolicy discountPolicy; // 추상체에만 의존
    @Autowired private MemberRepository memberRepository;
    */
    
    /* 수정자주입(setter주입)
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    //  생성자 주입 @Autowired는 생성자가 1개일 시 생략 가능하다
    public OrderServiceImpl(
            /*@Qualifier("mainDiscountPolicy")*/ @MainDiscountPolicy DiscountPolicy discountPolicy /*@Qualifier 빈 찾기*/
            , MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    } // 생성자 주입에 의해 추상제에만 의존하고 구현체는 외부(AppConfig)에 맡긴다

    /* 일반 메서드 주입 
    @Autowired // 아무데나 @Autowired 붙일 수 있다
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

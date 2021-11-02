package springbasic.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbasic.core.member.Grade;
import springbasic.core.member.Member;
import springbasic.core.member.MemberService;
import springbasic.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
       /*  기존 자바 방식
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        // MemberService memberService = new MemberServiceImpl(); // 구현체와 추상체 의존
       */

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // @Configuration 붙은 클래스 부르기
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // 첫번째 인자는 Bean의 메소드 이름, 두번째는 반환 타입

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember = " + findMember);
    }
}

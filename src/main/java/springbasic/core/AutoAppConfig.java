package springbasic.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import springbasic.core.member.Member;
import springbasic.core.member.MemberRepository;
import springbasic.core.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
        // basePackages = "springbasic.core.member", // member 패키지만 스캔을 한다. 선택적으로 사용. 영역을 안정할 시 spring파일 전체 스캔한다
        // basePackageClasses = AutoAppConfig.class, // 이 클래스 대상만 스캔
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))

public class AutoAppConfig {

    /*
    // 자동빈과 수동빈 충돌 비교
    @Bean(name = "memoryMemberRepository") // 수동빈 등록
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    */
}

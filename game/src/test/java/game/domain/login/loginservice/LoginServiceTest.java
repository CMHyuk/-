package game.domain.login.loginservice;

import game.domain.login.member.Member;
import game.domain.login.member.MemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoginServiceTest {

    MemberRepository memberRepository = new MemberRepository();
    LoginService loginService = new LoginService(memberRepository);

    @Test
    void login() {
        //given
        Member member = new Member("member1", "멤버", "asdf1234!", "990825", "01012345678");

        //when
        memberRepository.save(member);
        Member member1 = loginService.login("member1", "asdf1234!");

        //then
        assertThat(member.getLoginId()).isEqualTo(member1.getLoginId());
        assertThat(member.getPassword()).isEqualTo(member1.getPassword());
    }

}
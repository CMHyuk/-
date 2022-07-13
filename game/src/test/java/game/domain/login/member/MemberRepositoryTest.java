package game.domain.login.member;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    MemberRepository memberRepository = new MemberRepository();

    @Test
    void save() {
        //given
        Member member1 = new Member("member1", "멤버", "asdf1234!", "990825", "01012345678");

        //when
        memberRepository.save(member1);
        Member member2 = memberRepository.findById(member1.getId());

        //then
        assertThat(member1).isEqualTo(member2);
    }

    @Test
    void edit() {
        Member member = new Member("member1", "멤버", "asdf1234!", "990825", "01012345678");
        memberRepository.save(member);
        memberRepository.edit(member, "4321fdsa!");

        assertThat(member.getPassword()).isEqualTo("4321fdsa!");
    }

    @Test
    void remove() {
        //given
        Member member = new Member("member1", "멤버", "asdf1234!", "990825", "01012345678");

        //when
        memberRepository.save(member);
        memberRepository.remove(member.getId());
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(members).isEmpty();
    }

    @Test
    void findByLoginId() {
        //given
        Member member = new Member("member", "멤버", "asdf1234!", "990825", "01012345678");

        //when
        memberRepository.save(member);
        Optional<Member> loginId = memberRepository.findByLoginId(member.getLoginId());

        //then
        assertThat(loginId).contains(member);
    }
}
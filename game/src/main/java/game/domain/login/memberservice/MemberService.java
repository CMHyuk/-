package game.domain.login.memberservice;

import game.domain.login.member.Member;
import game.domain.login.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(Member member) {
        memberRepository.save(member);
    }

    public void editPassword(Member member, String password) {
        memberRepository.edit(member, password);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void remove(Long id) {
        memberRepository.remove(id);
    }

}

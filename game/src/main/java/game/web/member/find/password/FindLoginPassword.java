package game.web.member.find.password;

import game.domain.login.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class FindLoginPassword {

    private static final Map<String, String> findPassword = new HashMap<>();

    public Member saveFindPassword(Member member) {
        log.info("member={}", member);
        findPassword.put(member.getLoginId(), member.getPassword());
        return member;
    }

    public String findByPassword(String loginId) {
        return findPassword.get(loginId);
    }

    public void editPassword(Member member, String password) {
        findPassword.get(member.getLoginId());
        findPassword.replace(member.getLoginId(), password);
    }
}

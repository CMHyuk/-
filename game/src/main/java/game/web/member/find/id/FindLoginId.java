package game.web.member.find.id;

import game.domain.login.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class FindLoginId {

    private static final Map<String, String> findId = new HashMap<>();

    public Member saveFindId(Member member) {
        findId.put(member.getName()+member.getBirth(), member.getLoginId());
        log.info("findId={}", findId);
        return member;
    }

    public String findByPassword(String name, String birth) {
        return findId.get(name+birth);
    }
}

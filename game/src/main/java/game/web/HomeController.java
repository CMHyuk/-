package game.web;

import game.domain.login.member.Member;
import game.domain.login.member.MemberRepository;
import game.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String homeLogin(@Login Member member, Model model) {

        if(member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }


}

package game.web.member;

import game.domain.login.member.Member;
import game.domain.login.member.MemberRepository;
import game.web.argumentresolver.Login;
import game.web.member.remove.InputText;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRemoveController {

    private final MemberRepository memberRepository;

    @GetMapping("/remove")
    public String removeForm(@ModelAttribute InputText inputText) {
        return "members/removeForm";
    }

    @PostMapping("/remove")
    public String remove(@Validated @ModelAttribute InputText inputText, BindingResult bindingResult,
                         @Login Member loginMember, HttpServletRequest request) {

        log.info("loginMember={}", loginMember);

        if (loginMember == null) {
            return "home";
        }

        String input = inputText.getInput().replace(" ", "");
        log.info("input={}", input);

        if(bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "members/removeForm";
        }

        if(!input.equals("회원삭제")) {
            bindingResult.reject("removeFail", "회원 삭제를 입력하세요.");
            return "members/removeForm";
        }

        Member removeMember = memberRepository.findById(loginMember.getId());
        memberRepository.remove(removeMember.getId());
        log.info("members={}", memberRepository.findAll());

        HttpSession session = request.getSession(false);
        session.invalidate();

        return "home";
    }
}

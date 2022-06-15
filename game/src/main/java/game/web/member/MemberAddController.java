package game.web.member;

import game.domain.login.member.Member;
import game.domain.login.member.MemberRepository;
import game.web.member.find.id.FindLoginId;
import game.web.member.find.password.FindLoginPassword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberAddController {

    private final MemberRepository memberRepository;
    private final FindLoginId findLoginId;
    private final FindLoginPassword findLoginPassword;


    @GetMapping("/add")
    public String addForm(@ModelAttribute Member member) {
        return "members/addForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "members/addForm";
        }

        Optional<Member> loginId = memberRepository.findByLoginId(member.getLoginId());

        if(!loginId.isEmpty()) {
            bindingResult.reject("saveFail", "아이디가 존재합니다.");
            return "members/addForm";
        }

        memberRepository.save(member);
        findLoginId.saveFindId(member);
        findLoginPassword.saveFindPassword(member);

        log.info("member={}", member);
        return "redirect:/";
    }

}

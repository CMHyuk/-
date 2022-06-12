package game.web.member;

import game.domain.login.member.Member;
import game.domain.login.member.MemberRepository;
import game.domain.login.member.edit.Password;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberEditController {

    private final MemberRepository memberRepository;

    @GetMapping("/edit")
    public String editForm(@ModelAttribute Password password) {
        return "members/editForm";
    }

    @PostMapping("/edit")
    public String edit(@Validated @ModelAttribute Password password, BindingResult bindingResult,
                       @SessionAttribute(name = "loginMember", required = false) Member loginMember,
                       HttpServletRequest request) {

        if(loginMember == null) {
            return "home";
        }

        String nowPassword = password.getNowPassword();
        String newPassword = password.getNewPassword();
        String checkNewPassword = password.getCheckNewPassword();

        log.info("nowPassword={}", nowPassword);
        log.info("changePassword={}", newPassword);

        if(bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "members/editForm";
        }

        if(!loginMember.getPassword().equals(nowPassword)) {
            log.info("errors={}", bindingResult);
            bindingResult.addError(new FieldError("password", "nowPassword", "현재 비밀번호와 다릅니다."));
            return "members/editForm";
        }

        if(nowPassword.equals(newPassword)) {
            log.info("errors={}", bindingResult);
            bindingResult.addError(new FieldError("password", "newPassword", "현재 비밀번호와 같습니다."));
            return "members/editForm";
        }

        if(!newPassword.equals(checkNewPassword)) {
            log.info("errors={}", bindingResult);
            bindingResult.addError(new FieldError("password", "checkNewPassword", "새 비밀번호와 다릅니다."));
            return "members/editForm";
        }

        memberRepository.edit(loginMember, newPassword);
        log.info("editPassword={}", loginMember.getPassword());

        HttpSession session = request.getSession(false);
        session.invalidate();

        return "home";
    }
}

package game.web.member;

import game.domain.login.member.Member;
import game.domain.login.member.MemberRepository;
import game.domain.login.member.edit.Password;
import game.web.member.find.password.FindLoginPassword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberEditController {

    private final MemberRepository memberRepository;
    private final FindLoginPassword findLoginPassword;

    @GetMapping("/editHome")
    public String editHome(Model model) {
        List<Member> memberId = memberRepository.findAll();
        model.addAttribute("memberId", memberId);
        return "members/editHome";
    }

    @GetMapping("/edit")
    public String editForm(@ModelAttribute Password password) {
        return "members/editPasswordForm";
    }

    @PostMapping("/edit")
    public String edit(@Validated @ModelAttribute Password password, BindingResult bindingResult,
                       @SessionAttribute(required = false) Member loginMember,
                       HttpServletRequest request) {

        if(loginMember == null) {
            return "home";
        }

        String nowPassword = password.getNowPassword();
        String newPassword = password.getNewPassword();
        String checkNewPassword = password.getCheckNewPassword();

        log.info("현재 비밀번호={}", nowPassword);
        log.info("새로운 비밀번호={}", newPassword);
        log.info("새로운 비밀번호 확인={}", checkNewPassword);

        if(bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "members/editPasswordForm";
        }

        //입력한 현재 비밀번호와 저장된 비밀번호가 동일한지 체크
        if(!loginMember.getPassword().equals(nowPassword)) {
            log.info("errors={}", bindingResult);
            bindingResult.addError(new FieldError("password", "nowPassword", "현재 비밀번호와 다릅니다."));
            return "members/editPasswordForm";
        }

        //현재 비밀번호와 새로운 비밀번호 동일성 체크
        if(nowPassword.equals(newPassword)) {
            log.info("errors={}", bindingResult);
            bindingResult.addError(new FieldError("password", "newPassword", "현재 비밀번호와 같습니다."));
            return "members/editPasswordForm";
        }

        //새로운 비밀번호 확인 체크
        if(!newPassword.equals(checkNewPassword)) {
            log.info("errors={}", bindingResult);
            bindingResult.addError(new FieldError("password", "checkNewPassword", "새 비밀번호와 다릅니다."));
            return "members/editPasswordForm";
        }

        memberRepository.edit(loginMember, newPassword);
        findLoginPassword.editPassword(loginMember, newPassword);

        log.info("editPassword={}", loginMember.getPassword());

        HttpSession session = request.getSession(false);
        session.invalidate();

        return "home";
    }
}

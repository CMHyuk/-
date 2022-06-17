package game.web.member;

import game.web.member.find.id.FindId;
import game.web.member.find.id.FindLoginId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberFindIdController {

    private final FindLoginId findLoginId;

    @GetMapping("findId")
    public String findIdForm(@ModelAttribute FindId findId) {
        return "members/findId/findIdForm";
    }

    @PostMapping("findId")
    public String findId(@Validated @ModelAttribute FindId findId, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "members/findId/findIdForm";
        }

        String name = findId.getInputName();
        String birth = findId.getInputBirth();

        String findById = findLoginId.findByPassword(name, birth);
        log.info("findById={}", findById);

        if(findById == null) {
            bindingResult.reject("findFail", "입력 정보에 맞는 아이디가 존재하지 않습니다.");
            return "members/findId/findIdForm";
        }

        model.addAttribute("id", findById);

        return "members/findId/findId";
    }
}

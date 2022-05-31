package game.web.radomNumber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import game.domain.numbergame.number.InputNumber;
import game.domain.numbergame.number.RandomNumber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RandomNumberController {

    private final RandomNumber randomNumber;
    //시도 횟수
    int cnt = 1;

    @GetMapping("/number-game")
    public String numberGame(@ModelAttribute InputNumber inputNumber, Model model) {
        int rn = randomNumber.setNum();

        log.info("숫자 맞추기 게임 사이트 접속");
        log.info("난수 생성 ={}", rn);

        return "game/number";
    }

    @PostMapping("/number-game")
    public String confirmationNumber(@Validated @ModelAttribute InputNumber inputNumber, BindingResult bindingResult, Model model) {
        int rn = randomNumber.getNum();
        log.info("rn={}", rn);

        //검증 실패시 다시 입력 창
        if(bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult);
            return "game/number";
        }

        log.info("number={}", inputNumber.getInput());
        log.info("rn={}", rn);
        log.info("cnt={}", cnt);

        model.addAttribute("cnt", cnt);

        if(inputNumber.getInput() > rn) {
            cnt++;
            bindingResult.addError(new FieldError("inputNumber", "input", "틀렸습니다 다운!"));
        } else if(inputNumber.getInput() < rn) {
            cnt++;
            bindingResult.addError(new FieldError("inputNumber", "input", "틀렸습니다 업!"));
        }

        if(inputNumber.getInput() == rn) {
            //시도 횟수 초기화
            cnt = 1;
            return "game/correctResult";
        }
        return "game/number";
    }
}

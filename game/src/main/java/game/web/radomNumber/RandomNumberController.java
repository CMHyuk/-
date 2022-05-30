package game.web.radomNumber;

import lombok.extern.slf4j.Slf4j;
import game.domain.numbergame.number.InputNumber;
import game.domain.numbergame.number.RandomNumber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class RandomNumberController {

    private final RandomNumber randomNumber = new RandomNumber();
    int rn = randomNumber.setNum();
    int cnt = 1;

    @GetMapping("/number-game")
    public String numberGame(@ModelAttribute InputNumber inputNumber) {

        log.info("숫자 맞추기 게임 사이트 접속");
        log.info("난수 생성 ={}", rn);
        return "game/number";
    }

    @PostMapping("/number-game")
    public String confirmationNumber(@Validated @ModelAttribute InputNumber inputNumber, BindingResult bindingResult, Model model) {

        //검증 실패시 다시 입력 창
        if(bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult);
            return "game/number";
        }

        log.info("number={}", inputNumber.getInput());
        log.info("rn={}", rn);
        log.info("cnt={}", cnt);

        model.addAttribute("cnt", cnt);

        if(inputNumber.getInput() == rn) {
            return "game/correctResult";
        } else if(inputNumber.getInput() > rn) {
            cnt++;
            return "game/bigWrong";
        } else {
            cnt++;
            return "game/lessWrong";
        }
    }
}
